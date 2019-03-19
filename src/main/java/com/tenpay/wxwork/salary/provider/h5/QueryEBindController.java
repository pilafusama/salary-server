package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.*;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.presvr.sender.bean.QueryEBindRes;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.h5.QueryEBindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class QueryEBindController
{
    @Autowired
    private SessionService sessionService;
    @Autowired
    private QueryEBindService queryEBindService;


    private static final Logger logger = LoggerFactory.getLogger(QueryEBindController.class);

    @PostMapping(value = "/h5/query_E_bind",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object queryEBind(@CookieValue("ssid") String ssid)
    {
        logger.debug("query E bind");
        //logger.debug(fetchAmountReqParam.getCstAccNo());

        //1、验证登录态、验证前置状态
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }


        String userid = sessionInfo.getUserId();
        String corpid = sessionInfo.getCorpId();
        String bankType = sessionInfo.getBankType();

        QueryEBindRes queryEBindRes  = queryEBindService.queryE(corpid,userid, bankType);

        return new QueryEBindResponse(queryEBindRes.getEbnk_Sign_Accno(),queryEBindRes.getCoPlf_ID(),queryEBindRes.getPltfrm_Nm(),
                queryEBindRes.getHdl_InsID(),queryEBindRes.getSign_Dt(),queryEBindRes.getMblPh_No(),queryEBindRes.getMbsh_No(),
                queryEBindRes.getCrdt_TpCd(),queryEBindRes.getCrdt_No(),queryEBindRes.getCst_Nm(),queryEBindRes.getRltv_AccNo(),
                queryEBindRes.getRsrv_TpCd(),queryEBindRes.getDpBkBkCD(),queryEBindRes.getLv1_InsID(),queryEBindRes.getLvl2_InsID());

    }



}