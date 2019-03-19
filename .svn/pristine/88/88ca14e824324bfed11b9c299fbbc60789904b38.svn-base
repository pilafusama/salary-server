package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.*;
import com.tenpay.wxwork.salary.model.FinancialPlan;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.presvr.sender.bean.BalanceRes;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.h5.BalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 查询账户余额
 */

@RestController
public class BalanceController {

    @Autowired
    private BalanceService service;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private KeyCacheService keyCacheService;

    private static Logger log = LoggerFactory.getLogger(BalanceController.class);

    @PostMapping(value = "/h5/query_account_balance", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object queryBalance(@CookieValue("ssid") String ssid, @RequestBody BalanceReqParam request){

        //1、验证登录态、验证前置状态
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        //本地库中查找工资卡信息
        SalaryAccount fsalaryCardInfo = service.getCardInfo(sessionInfo.getCorpId(), sessionInfo.getUserId());
        BalanceRes balance = null;
        //CurrentDetailRes detail = null;
        List<FinancialPlan> financialPlan = null;

        String cardNo = AesUtil.decrypt(fsalaryCardInfo.getSalary_card_number(),keyCacheService.getAccountSecret());
        if(cardNo != null){
            //对加密数据进行解密
            fsalaryCardInfo.setSalary_card_number(cardNo);
        }else {
            throw new BizException(BizError.AES_DECRYPT_ERROR.errorCode(), BizError.AES_DECRYPT_ERROR.errorMsg());
        }

        //获取银行类型
        String bankType = sessionInfo.getBankType();
        //查询余额
        balance = service.getBanlance(fsalaryCardInfo, request, bankType);

        //查询薪资理财计划
//        financialPlan = service.getFinancialPlan(sessionInfo.getCorpId(), sessionInfo.getUserId());


        BalanceResParam balanceRes = new BalanceResParam(balance.getTotalAmt(), balance.getCurrentAmt(),
                balance.getFixedAmt(), fsalaryCardInfo.getSalary_card_number(),
                fsalaryCardInfo.getSalary_card_bank_type(), fsalaryCardInfo.getBind_card_bank_sname(),
                fsalaryCardInfo.getCre_name(), fsalaryCardInfo.getBind_card_number(),
                fsalaryCardInfo.getCreate_time(),
                fsalaryCardInfo.getBind_card_bank_sname());
        balanceRes.setFinancialPlans(financialPlan);
        return balanceRes;
    }
}
