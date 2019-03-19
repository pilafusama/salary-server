package com.tenpay.wxwork.salary.provider.h5;

import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.*;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.presvr.sender.bean.CurrentDetailRes;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.h5.CurrentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询账户交易明细(薪资发放记录)
 */
@RestController
public class CurrentDetailController {

    @Autowired
    private CurrentDetailService service;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private KeyCacheService keyCacheService;

    @PostMapping(value = "/h5/query_account_transaction_detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object queryCurrentDetail(@CookieValue("ssid") String ssid, @RequestBody CurrentDetailReqParam request) {

        //1、验证登录态、验证前置状态
        SessionInfo sessionInfo = sessionService.getSession(ssid);
        if (sessionInfo == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }

        //本地库中查找工资卡信息
        SalaryAccount fsalaryCardInfo = service.getCardInfo(sessionInfo.getCorpId(), sessionInfo.getUserId());
        String cardNo = AesUtil.decrypt(fsalaryCardInfo.getSalary_card_number(),keyCacheService.getAccountSecret());
        if(cardNo != null){
            //对加密数据进行解密
            fsalaryCardInfo.setSalary_card_number(cardNo);
        }else {
            throw new BizException(BizError.AES_DECRYPT_ERROR.errorCode(), BizError.AES_DECRYPT_ERROR.errorMsg());
        }
        //获取银行类型
        String bankType = sessionInfo.getBankType();
        CurrentDetailRes currentDetailRes = service.getCurrentDetail(request, fsalaryCardInfo, bankType);
        List<CurrentDetailResChildParam> resultList = new ArrayList<>();

        if(currentDetailRes.getPrim_Acc_Acg_Dtl() != null && currentDetailRes.getPrim_Acc_Acg_Dtl().size() > 0){

            for(PrimAccAcg txn: currentDetailRes.getPrim_Acc_Acg_Dtl()){
                String txnType = txn.getSmy_Cd();
                String txnName = txn.getSmy_Cntnt();
                String txnYear = txn.getTxn_Dt().substring(0,4);
                String txnMonth = txn.getTxn_Dt().substring(4,6);
                String txnDay = txn.getTxn_Dt().substring(6,8);
                String txnTm = txn.getTxn_Tm().substring(0,2) + ":" + txn.getTxn_Tm().substring(2,4) + ":" + txn.getTxn_Tm().substring(4,6);
                String txnAmt = txn.getDep_TxnAmt();

                resultList.add(new CurrentDetailResChildParam(txnYear, txnType,txnName,
                        txnMonth, txnDay, txnTm, txnAmt));
            }
        }
        return new CurrentDetailResParam(resultList);
    }


}
