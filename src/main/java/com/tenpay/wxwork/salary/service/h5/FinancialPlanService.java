package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.dao.FinancialPlanDAO;
import com.tenpay.wxwork.salary.dao.FinancialPlanExecDAO;
import com.tenpay.wxwork.salary.dto.h5.*;
import com.tenpay.wxwork.salary.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : wg
 * @description :
 * @date : 2018/8/19
 */
@Service
public class FinancialPlanService {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private FinancialPlanDAO dao;
    @Autowired
    private FinancialPlanExecDAO execDAO;

    public void createPlan(String corpid ,String userid,FinancialPlanParam request){
        dao.insertNewPlan(corpid,userid,request.getTran_amt(),request.getTran_prod()
                ,request.getTran_cycle(),request.getTran_date(),"1");
    }


    public void updatePlan(UpdateFinancialReqParam reqParam) {

        //将理财计划执行表中该理财计划删除
        execDAO.deletePlanExec(reqParam.getFinancial_plan_id());
        //修改理财计划
        dao.updatePlan(reqParam.getFinancial_plan_id(), reqParam.getTran_amt(),
                reqParam.getTran_prod(), reqParam.getTran_cycle(), reqParam.getTran_date());

    }
}
