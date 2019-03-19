package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.dto.h5.*;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.presvr.sender.bean.FetchAmountRes;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.h5.EnjambentFetchService;
import com.tenpay.wxwork.salary.service.h5.FetchAmountService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CookieValue;

import java.text.DecimalFormat;


@RestController
public class FetchAmountController
{
    @Autowired
    private SessionService sessionService;
    @Autowired
    private FetchAmountService fetchAmountService;
    @Autowired
    private EnjambentFetchService enjambentFetchService;


    private static final Logger logger = LoggerFactory.getLogger(FetchAmountController.class);

    @PostMapping(value = "/h5/fetch",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object fetchAmount(@CookieValue("ssid") String ssid, @RequestBody  FetchAmountReqParam fetchAmountReqParam  ) {

        logger.debug("fetch  amount");

        //1、验证登录态、验证前置状态
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null) {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(), BizError.GET_SESSION_FAILED.errorMsg());
        }

        //2、金额判断
        if (StringUtils.isBlank(fetchAmountReqParam.getZZ_amt().toString())){
            throw new BizException(BizError.FETCH_AMOUNT_IS_EMPTY.errorCode(), BizError.FETCH_AMOUNT_IS_EMPTY.errorMsg());
        }

        //不可大于余额
        // AcBa	账户余额

        if (StringUtils.isBlank(fetchAmountReqParam.getAcBa().toString())){
            throw new BizException(BizError.BALANCE_IS_EMPTY.errorCode(), BizError.BALANCE_IS_EMPTY.errorMsg());
        }
        fetchAmountReqParam.setAcBa(toFormat(fetchAmountReqParam.getAcBa()));
        if (fetchAmountReqParam.getZZ_amt() > fetchAmountReqParam.getAcBa()) {
            throw new BizException(BizError.FETCH_AMOUNT_BIG_THAN_BALANCE.errorCode(),BizError.FETCH_AMOUNT_BIG_THAN_BALANCE.errorMsg());
        }


        String userid = sessionInfo.getUserId();
        String corpid = sessionInfo.getCorpId();


        /**
         a)若是同行提现，调用银行提现接口
         b)若是他行提现
            i.验证提现金额不得高于5万
            ii.调用银行提现接口
         */
        //获取银行类型
        String bankType = sessionInfo.getBankType();
        if (Constant.SAME_BANK.equals(fetchAmountReqParam.getCard_banks_relation())) {
            fetchAmountReqParam.setZZ_amt(toFormat(fetchAmountReqParam.getZZ_amt()));
            if (!((fetchAmountReqParam.getZZ_amt()) > Constant.ZERO_MONEY)) {
                throw new BizException(BizError.FETCH_AMOUNT_ERROR.errorCode(), BizError.FETCH_AMOUNT_ERROR.errorMsg());
            }
            FetchAmountRes fetchAmountRes = fetchAmountService.fetch(fetchAmountReqParam,corpid, userid, bankType);
            return new FrontEndResponse(fetchAmountRes.getRetcode(), fetchAmountRes.getErrmsg());
        }
        else {
            fetchAmountReqParam.setZZ_amt(toFormat(fetchAmountReqParam.getZZ_amt()));
            if (!((fetchAmountReqParam.getZZ_amt()) > Constant.ZERO_MONEY && (fetchAmountReqParam.getZZ_amt()) <= Constant.FIFTY_THOUSAND)) {
                throw new BizException(BizError.FETCH_AMOUNT_TOO_LARGE.errorCode(), BizError.FETCH_AMOUNT_TOO_LARGE.errorMsg());
            }
            FetchAmountRes fetchAmountRes = enjambentFetchService.fetch(fetchAmountReqParam, corpid, userid, bankType);
            return new FrontEndResponse(fetchAmountRes.getRetcode(), fetchAmountRes.getErrmsg());
        }
    }

    private Double toFormat(Double num){
        DecimalFormat df  = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(num));
    }

}

