package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.OpenSalaryAccountResponse;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BindCardService {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SalaryAccountDAO salaryAccountDAO;

    public OpenSalaryAccountResponse bankVerify(String ssid, String rltv_accno){
        //1、参数判断，前置条件检查
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        //调用腾讯内部接口进行银行卡四要素核验 TODO

        //更新用户状态
        //salaryAccountDAO.updateFstate(SalaryAccount.State.ACCOUNT_OPENED.toInt(),sessionInfo.getCorpId(),sessionInfo.getUserId(),SalaryAccount.State.BIND_CARD_RECOGNIZED.toInt());
        return new OpenSalaryAccountResponse("0","OK","1",rltv_accno ,sessionInfo.getBankChNameOne());
    }
}
