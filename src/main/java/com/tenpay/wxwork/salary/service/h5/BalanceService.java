package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.dao.FinancialPlanDAO;
import com.tenpay.wxwork.salary.dao.FinancialPlanExecDAO;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.*;
import com.tenpay.wxwork.salary.model.FinancialPlan;
import com.tenpay.wxwork.salary.model.FinancialPlanExec;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.presvr.sender.bean.BalanceRes;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;


@Service
public class BalanceService {

    @Autowired
    private BankProxyRequestService bankProxyRequestService;

    @Autowired
    private SalaryAccountDAO accDao;

    @Autowired
    private FinancialPlanDAO planDao;

    @Autowired
    private FinancialPlanExecDAO execDao;


//    @Async
    public BalanceRes  getBanlance(SalaryAccount fsalaryCardInfo, BalanceReqParam request, String bankType) {
        fsalaryCardInfo.setSalary_card_bank_type(bankType);
        return bankProxyRequestService.balance(fsalaryCardInfo,request);
    }

    //获取薪资理财计划
//    @Async
    public List<FinancialPlan> getFinancialPlan(String corpId, String userId) {
        List<FinancialPlan> planList = planDao.queryPlans(corpId, userId);
        //查找计划最近执行的时间
        for(int i = 0; i < planList.size(); i ++){
            List<FinancialPlanExec> execList = execDao.queryExecPlans(planList.get(i).getFfinancial_plan_id());
            if(execList != null && execList.size() > 0){
                planList.get(i).setExecTime(execList.get(0).getFactual_tran_time());
            }else{
                planList.get(i).setExecTime("");
            }
        }

        return planList;

    }

    public SalaryAccount getCardInfo(String corpId, String userId){
        return accDao.getFsalaryCardInfo(corpId, userId);
    }

}

