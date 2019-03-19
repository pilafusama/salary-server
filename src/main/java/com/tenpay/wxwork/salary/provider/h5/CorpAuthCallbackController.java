package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.common.utils.BankConfKey;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.dao.QRcodeConfigDao;
import com.tenpay.wxwork.salary.dao.SalaryBankConfDAO;
import com.tenpay.wxwork.salary.dto.admin.QRcodeConfigParam;
import com.tenpay.wxwork.salary.model.SalaryBankConfInfo;
import com.tenpay.wxwork.salary.provider.admin.RegistController;
import com.tenpay.wxwork.wxworklib.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CorpAuthCallbackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistController.class);

    @Resource
    private AuthService authService;

    @Autowired
    private QRcodeConfigDao qRcodeConfigDao;

    @Autowired
    private SalaryBankConfDAO salaryBankConfDAO;
    /**
     * 响应企业通过点击链接安装应用，非通过应用市场方式（指令通知）
     * https://work.weixin.qq.com/api/doc#10974 见服务商网站部分
     *
     */
    @RequestMapping(value = "/h5/corp_auth_callback", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String doCorpAuth(@RequestParam("auth_code") String authCode,
                             @RequestParam("expires_in") String expiresIn,
                             String state, HttpServletRequest request) {
        LOGGER.info("request:{},{}", request.getMethod(), request.getRequestURI());
        LOGGER.info("query string:{}", request.getQueryString());
        String qrcode = state.split(Constant.PRE_STATE)[1];
        QRcodeConfigParam qRcodeConfigParam = qRcodeConfigDao.queryQRcodeById(Integer.parseInt(qrcode));
        SalaryBankConfInfo salaryBankConfInfo = salaryBankConfDAO.querySalaryCorpConfInfo(qRcodeConfigParam.getBank_sname(), BankConfKey.MCH_ID, BankConfKey.AUDITED_STATE);
        String mchId = salaryBankConfInfo.getValue();
        String suiteId = ConfigUtils.getSuiteId();
		authService.createAuth(suiteId, authCode, mchId);
        return "<html><body><div>Thank you for using our app!</div></body></html>";
    }
}
