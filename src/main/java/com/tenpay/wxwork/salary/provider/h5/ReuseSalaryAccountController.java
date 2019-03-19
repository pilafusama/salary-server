package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.dto.h5.ReuseSalaryAccountReqParam;
import com.tenpay.wxwork.salary.model.CardBin;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.presvr.sender.bean.ReuseSalaryAccountRes;
import com.tenpay.wxwork.salary.dto.h5.ReuseSalaryAccountResponse;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.h5.ReuseSalaryAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReuseSalaryAccountController {

    private static final Logger logger = LoggerFactory.getLogger(ReuseSalaryAccountController.class);

    @Autowired
    private ReuseSalaryAccountService reuseSalaryAccountService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/h5/reuse_salary_account",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object reuseSalaryAccount(@CookieValue("ssid") String ssid,
                                     @RequestBody ReuseSalaryAccountReqParam request)
    {

        logger.debug("open salary account");
        //1、验证登录态、验证前置状态
        //2、调用银行接口“维护平台绑定信息”
        //3、待银行返回成功后，写入db，开户成功
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }
        ReuseSalaryAccountRes res = reuseSalaryAccountService.reuseSalaryAccount(ssid,request);

        /*//获取一类户银行名称
        String rltv_accno = res.getRltv_accno();//一类户卡号
        FinGateClient finGateClient = new FinGateClient();
        CardBin cardBin = finGateClient.queryCardBin(rltv_accno);
        String bankName = cardBin.getBankSname();
        NationalBankNumber nb = new NationalBankNumber(bankName);
        String chName = nb.queryChName(bankName);
        //获取二类户银行名称
        String ebnk_sign_accno = request.getEbnk_sign_accno();//二类户卡号
        CardBin cardBin2 = finGateClient.queryCardBin(ebnk_sign_accno);
        String bankName2 = cardBin2.getBankSname();
        NationalBankNumber nb2 = new NationalBankNumber(bankName2);
        String chName2 = nb2.queryChName(bankName2);*/

        return new ReuseSalaryAccountResponse(res,request.getEbnk_sign_accno(),session.getBankChNameOne(),session.getBankChNameTwo());
    }
}
