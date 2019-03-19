package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.h5.SalaryCountReqParam;
import com.tenpay.wxwork.salary.service.h5.QuerySalaryDetailService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuerySalaryDetailController {
    private static final Logger logger = LoggerFactory.getLogger(QuerySalaryDetailController.class);

    @Autowired
    QuerySalaryDetailService querySalaryDetailService;

    @RequestMapping(value = "/h5/query_salary_count",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object querySalaryCount(@CookieValue("ssid") String ssid){
        logger.debug("query salary count");

        return querySalaryDetailService.querySalaryCount(ssid);
    }

    @RequestMapping(value = "/h5/query_salary_detail",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object querySalaryDetail(@CookieValue("ssid") String ssid, @RequestBody SalaryCountReqParam reqParam){
        logger.debug("query salary detail");

        return querySalaryDetailService.querySalaryDetail(ssid,reqParam.getMonth());
    }

    //TODO UPDATE 以下为适应企业过渡版本
    @RequestMapping(value = "/h5/query_salary_count_no",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object querySalaryCountNo(@CookieValue("ssid") String ssid){
        logger.debug("query salary count no");

        return querySalaryDetailService.querySalaryCountNo(ssid);
    }
    //TODO UPDATE 以下为适应企业过渡版本
    @RequestMapping(value = "/h5/query_salary_detail_no",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object querySalaryDetailNo(@CookieValue("ssid") String ssid, @RequestBody SalaryCountReqParam reqParam){
        logger.debug("query salary detail no");

        return querySalaryDetailService.querySalaryDetailNo(ssid,reqParam.getMonth());
    }
    //TODO UPDATE 以下为适应企业过渡版本
    @RequestMapping(value = "/h5/update_salary_detail_state",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object updateSalaryDetailState(@CookieValue("ssid") String ssid, @RequestBody SalaryCountReqParam reqParam){
        logger.debug("update salary detail state");
        querySalaryDetailService.updateSalaryDetailState(ssid,reqParam.getMonth());
        return new FrontEndResponse();
    }

    //适配版
    @RequestMapping(value = "/h5/query_user_salary_detail",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object queryUserSalaryDetail(@CookieValue("ssid") String ssid, @RequestBody SalaryCountReqParam reqParam){
        logger.debug("query user salary detail ");

        return querySalaryDetailService.queryUserSalaryDetail(ssid,reqParam.getMonth(),reqParam.getBatch_no());
    }

    //适配版
    @RequestMapping(value = "/h5/query_user_salary_count",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object queryUserSalaryCount(@CookieValue("ssid") String ssid){
        logger.debug("query salary count");

        return querySalaryDetailService.queryUserSalaryCount(ssid);
    }
    //适配版
    @RequestMapping(value = "/h5/update_user_salary_detail_state",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object updateUserSalaryDetailState(@CookieValue("ssid") String ssid, @RequestBody SalaryCountReqParam reqParam){
        logger.debug("update user salary detail state");
        querySalaryDetailService.updateUserSalaryDetailState(ssid,reqParam.getMonth(),reqParam.getBatch_no());
        return new FrontEndResponse();
    }

}
