package com.tenpay.wxwork.salary.service.admin;

import javax.annotation.Resource;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.BankConfKey;
import com.tenpay.wxwork.salary.common.utils.DateUtils;
import com.tenpay.wxwork.salary.dao.MchInfoDAO;
import com.tenpay.wxwork.salary.dao.QRcodeConfigDao;
import com.tenpay.wxwork.salary.dao.SalaryBankConfDAO;
import com.tenpay.wxwork.salary.dto.admin.QRcodeConfigParam;
import com.tenpay.wxwork.salary.model.MchInfo;
import com.tenpay.wxwork.salary.model.RegiserInfo;
import com.tenpay.wxwork.salary.model.SalaryBankConfInfo;
import com.tenpay.wxwork.salary.service.CkvPlusClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.common.utils.ExceptionUtils;
import com.tenpay.wxwork.salary.service.CkvPlusClientFactory;
import com.tenpay.wxwork.salary.service.wxauth.WxHttpClient;
import com.tenpay.wxwork.wxworklib.WxworkConfig;
import com.tenpay.wxwork.wxworklib.client.WxworkHttpClient;
import com.tenpay.wxwork.wxworklib.service.WxAccessTokenService;

import java.io.IOException;

@Service
public class RegistService {
	
	@Autowired
	private WxHttpClient wxHttpClient;
	
	@Autowired
	private WxworkHttpClient wxworkHttpClient;
	
	@Resource
    private Gson gson;
	
	@Resource
	private WxworkConfig wxworkConfig;
	
	@Resource
	private CkvPlusClientFactory ckvPlusClientFactory;
	
	@Resource
	private WxAccessTokenService wxAccessTokenService;

	@Autowired
	private QRcodeConfigDao qRcodeConfigDao;

	@Autowired
	private MchInfoDAO mchInfoDAO;

	@Autowired
	private SalaryBankConfDAO salaryBankConfDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(RegistService.class);
	
	private static final String REGIST_URL_PREFIX = "https://open.work.weixin.qq.com/3rdservice/wework/register?register_code=";
	
	public String getInstallUrl(int id) {
		//根据银行名获取mch_id
		QRcodeConfigParam qRcodeConfigParam = qRcodeConfigDao.queryQRcodeById(id);
		SalaryBankConfInfo salaryBankConfInfo = salaryBankConfDAO.querySalaryCorpConfInfo(qRcodeConfigParam.getBank_sname(), BankConfKey.MCH_ID, BankConfKey.AUDITED_STATE);
		MchInfo mchInfo = mchInfoDAO.queryMchInfo(salaryBankConfInfo.getValue());
		if(mchInfo == null){
			throw new BizException(BizError.MCH_INFO_NOT_EXIST);
		}

		// 获取服务商token
		String providerAccessToken = wxAccessTokenService.getProviderAccessToken();
		
		// 调用“获取注册码”API
		JsonObject reqData = new JsonObject();
		reqData.addProperty("template_id", wxworkConfig.templateId);
		reqData.addProperty("state", Integer.toString(id));	
        logger.debug("req data:" + gson.toJson(reqData));

		String api = "/service/get_register_code?provider_access_token=" + providerAccessToken;
        JsonObject result = wxworkHttpClient.invoke(api, reqData);
        
        
//		JsonObject result = gson.fromJson(wxHttpClient.getRegisterCode(providerAccessToken, gson.toJson(reqData)),JsonObject.class);
		
		if(result.has("errcode") ) {
            ExceptionUtils.checkResult(result.get("errcode").getAsInt(), result.get("errmsg").getAsString());
        }		
        String register_code = result.get("register_code").getAsString();
        
        // 将注册码保存到ckv
		RegiserInfo regiserInfo = new RegiserInfo();
		regiserInfo.setMchId(mchInfo.getFmchId());
		regiserInfo.setCreateTime(DateUtils.getTimestamp());
		CkvPlusClient CkvPlusClient = ckvPlusClientFactory.getClient();
		CkvPlusClient.set(Constant.CORP_PROMOTION + register_code, gson.toJson(regiserInfo));
		try {
			CkvPlusClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return REGIST_URL_PREFIX + register_code;
	}

}
