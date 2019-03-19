package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.config.NationalBankNumber;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.OpenSalaryAccountResponse;
import com.tenpay.wxwork.salary.dto.h5.UserSalaryCardResponse;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserSalaryCardService {
    @Autowired
    private SalaryAccountDAO salaryAccountDAO;
    @Autowired
    private SessionService sessionService;

    public UserSalaryCardResponse getUserSalaryCard(String ssid){
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        SalaryAccount salaryAccount = salaryAccountDAO.queryByUserId(session.getCorpId(), session.getUserId());
        String sname = salaryAccount.getBind_card_bank_sname();
        String chname = "";
        if(sname != null && !"".equals(sname)){
            NationalBankNumber nationalBankNumber = new NationalBankNumber(sname);
            chname = nationalBankNumber.queryChName(sname) ;
        }

        return  new UserSalaryCardResponse(AesUtil.decryptAccount(salaryAccount.getBind_card_number()), sname, chname,AesUtil.decryptAccount(salaryAccount.getCre_name()));
    }
}
