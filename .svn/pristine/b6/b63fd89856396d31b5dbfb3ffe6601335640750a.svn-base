package com.tenpay.wxwork.salary.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.common.utils.CorpConfKey;
import com.tenpay.wxwork.salary.dao.QRcodeConfigDao;
import com.tenpay.wxwork.salary.dao.SalaryCorpConfDAO;
import com.tenpay.wxwork.salary.dto.admin.QRcodeConfigParam;
import com.tenpay.wxwork.salary.model.SalaryCorpConfInfo;
import com.tenpay.wxwork.wxworklib.service.AbstractRegisterCorpService;
import com.tenpay.wxwork.wxworklib.utils.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 企业完成注册后写入企业配置银行信息
 */
@Service
public class WxworkRegisterCorpService extends AbstractRegisterCorpService{
    private final static Logger LOGGER = LoggerFactory.getLogger(WxworkRegisterCorpService.class);

    @Resource
    private CkvPlusClientFactory ckvPlusClientFactory;

    @Autowired
    private SalaryCorpConfDAO salaryCorpConfDAO;

    @Autowired
    private QRcodeConfigDao qRcodeConfigDao;

    @Resource
    private Gson gson;

    @Override
    public void createCorpConfig(Document document) {
        String registerCode = XmlUtils.findNodeContent(document, "/xml/RegisterCode");
        if(registerCode.isEmpty()){
            throw new BizException(BizError.NO_REGISTER_CODE);
        }
        String corpid = XmlUtils.findNodeContent(document, "/xml/AuthCorpId");
//        JsonObject jsonObject = gson.fromJson(ckvPlusClientFactory.getClient().get(Constant.CORP_PROMOTION + corpid),JsonObject.class);
//        String promotion_qrcode_id = jsonObject.get("promotion_qrcode_id").getAsString();
        CkvPlusClient client = ckvPlusClientFactory.getClient();
        String promotion_qrcode_id = client.get(Constant.CORP_PROMOTION + registerCode);
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        QRcodeConfigParam qRcodeConfigParam = qRcodeConfigDao.queryQRcodeById(Integer.parseInt(promotion_qrcode_id));
        String bank_sname = qRcodeConfigParam.getBank_sname();
        LOGGER.info("create corp config with corpid: {}, bank_sname: {}", corpid, bank_sname);

        SalaryCorpConfInfo salaryCorpConfInfo = salaryCorpConfDAO.querySalaryCorpConfInfo(corpid, CorpConfKey.CORP_TO_BANK, CorpConfKey.AUDITED_STATE);
        if(null == salaryCorpConfInfo){
            salaryCorpConfDAO.insertCorpConf(corpid, CorpConfKey.AUDITED_STATE, CorpConfKey.CONF, CorpConfKey.CORP_TO_BANK, bank_sname);
        }else {
            salaryCorpConfDAO.updateCorpConf(corpid, CorpConfKey.AUDITED_STATE, CorpConfKey.CONF, CorpConfKey.CORP_TO_BANK, bank_sname);
        }
    }
}
