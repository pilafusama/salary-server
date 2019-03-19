package com.tenpay.wxwork.salary.service.h5;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.*;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.presvr.sender.bean.CurrentDetailRes;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentDetailService {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BankProxyRequestService bankProxyRequestService;

    @Autowired
    private SalaryAccountDAO dao;

    public CurrentDetailRes getCurrentDetail(CurrentDetailReqParam reqParam, SalaryAccount fsalaryCardInfo, String bankType) {
        if(reqParam.getSelYear() != null && !"".equals(reqParam.getSelYear())){
            reqParam.setBeg_Enqr_Dt(reqParam.getSelYear()+"0101");
            reqParam.setCtOf_Enqr_Dt(reqParam.getSelYear()+"1231");
        }else{
            //截止日期为当前
            String ctOfEnqrDt = DateUtil.getYearDate(0);
            //开始日期为前3年
            String begEnqrDt = DateUtil.getYearDate(-3);
            reqParam.setBeg_Enqr_Dt(begEnqrDt);
            reqParam.setCtOf_Enqr_Dt(ctOfEnqrDt);
        }
        fsalaryCardInfo.setSalary_card_bank_type(bankType);
        //银行返回明细
        CurrentDetailRes currentDetailRes = bankProxyRequestService.currentDetail(reqParam, fsalaryCardInfo);
        return currentDetailRes;

    }
    public SalaryAccount getCardInfo(String corpId, String userId){
        return dao.getFsalaryCardInfo(corpId, userId);
    }

}



