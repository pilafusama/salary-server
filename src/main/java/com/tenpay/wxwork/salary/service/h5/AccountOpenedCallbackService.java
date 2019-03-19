package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.AccountOpenedCallbackParam;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountOpenedCallbackService {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    public void accountOpendedCallback(String ssid, AccountOpenedCallbackParam accountOpenedCallbackParam){
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }

        //TODO 检验银行签名

        //调用银行接口，确认四要素信息正确

        //解密,，获取银行卡信息
        //开户信息计入 db
//        salaryAccountDAO.updateOpenedCallbackSalaryAccount(sessionInfo.getUserId(), sessionInfo.getCorpId(),
//                accountOpenedCallbackParam.getMobile_number(), SalaryAccount.State.ACCOUNT_OPENED.toInt(),SalaryAccount.OpeningType.CREATE.name(),
//                accountOpenedCallbackParam.getCategory2_card_number(),"",
//                accountOpenedCallbackParam.getId_card_name(),accountOpenedCallbackParam.getId_card_number(),
//                SalaryAccount.CreType.ID_CARD.name(),accountOpenedCallbackParam.getCategory1_card_number(),"","","");
    }
}
