package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.client.FinGateClient;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.h5.SmsCodeSendReqPamam;
import com.tenpay.wxwork.salary.model.SmsCode;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.common.utils.SendSmsCodeChange;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.SendSmsCodeReqParam;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.SessionService;

@Service
public class SendSmsCodeService {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private BankProxyRequestService bankProxyRequestService;
    @Autowired
    SalaryAccountDAO salaryAccountDAO;
    @Autowired
    private KeyCacheService keyCacheService;

    public BankProxyRelayResponseMsg sendSmsCode(String ssid, SendSmsCodeReqParam reqParam){
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        SalaryAccount salaryAccount = salaryAccountDAO.queryByUserId(session.getCorpId(),session.getUserId());
//        reqParam.setBankType(salaryAccount.getSalary_card_bank_type());
        reqParam.setBankType(session.getBankType());
        reqParam.setCrdt_no(AesUtil.decrypt(salaryAccount.getCre_id(),keyCacheService.getAccountSecret()));
        reqParam.setSigndata(SendSmsCodeChange.changeCode(reqParam.getScene(),reqParam.getSigndata()));
        BankProxyRelayResponseMsg res = bankProxyRequestService.sendSmsCode(reqParam);
        //开户复用时发送短信更新状态
        if(Constant.OPEN_ACCOUNT.equals(reqParam.getScene())){
        	salaryAccountDAO.updateFstate(SalaryAccount.State.OPEN_SMS_SENT.toInt(), session.getCorpId(), session.getUserId(),SalaryAccount.State.BIND_CARD_RECOGNIZED.toInt());
        }
        if(Constant.REUSE_ACCOUNT.equals(reqParam.getScene())){
        	salaryAccountDAO.updateFstate(SalaryAccount.State.REUSE_SMS_SENT.toInt(), session.getCorpId(), session.getUserId(),SalaryAccount.State.CRE_RECOGNIZED.toInt());
        }
        return res;
    }

    public void sendSmsCodeTX(String ssid, SmsCodeSendReqPamam request) {
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        FinGateClient finGateClient = new FinGateClient();
        SmsCode smsCode = finGateClient.smsCodeSend(request.getMobileNumber(), session.getCorpId() + ":" + session.getUserId());
        session.setFpgSmsGid(smsCode.getFpgSmsGid());
        sessionService.setSession(session);
    }

    public FrontEndResponse smsCodeVerifyTX(String ssid, SmsCodeSendReqPamam request) {
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        FinGateClient finGateClient = new FinGateClient();
        FrontEndResponse frontEndResponse = finGateClient.smsCodeVerify(request.getMobileNumber(), request.getVerifyCode(), session.getFpgSmsGid(), session.getCorpId() + ":" + session.getUserId());
        if("0".equals(frontEndResponse.getRetcode())){
            SalaryAccount salaryAccount = salaryAccountDAO.queryByUserId(session.getCorpId(), session.getUserId());
            salaryAccountDAO.updateFstate(SalaryAccount.State.ACCOUNT_OPENED.toInt(),session.getCorpId(),session.getUserId(),salaryAccount.getState());
        }
        return frontEndResponse;
    }
}
