package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.h5.FinancialPlanParam;
import com.tenpay.wxwork.salary.dto.h5.UpdateFinancialReqParam;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.h5.FinancialPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class FinancialPlanController
{
    @Autowired
    private SessionService sessionService;
    @Autowired
    private FinancialPlanService service;


    private static final Logger logger = LoggerFactory.getLogger(FinancialPlanController.class);

    @PostMapping(value = "/h5/financial_plan",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object createPlan(@CookieValue("ssid") String ssid,@RequestBody FinancialPlanParam financialPlanParam)
    {
        logger.debug("financial_plan");

        //1、验证登录态、验证前置状态
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        String userid = sessionInfo.getUserId();
        String corpid = sessionInfo.getCorpId();
        service.createPlan(corpid,userid,financialPlanParam);
       return new FrontEndResponse();
    }


    //修改工资理财计划
    @PostMapping(value = "/h5/update_financial_plan",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object updatePlan(@CookieValue("ssid") String ssid, @RequestBody UpdateFinancialReqParam reqParam)
    {
        logger.debug("update_financial_plan");
        //1、验证登录态、验证前置状态
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }

        service.updatePlan(reqParam);
        return new FrontEndResponse();
    }

}