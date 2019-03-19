package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dao.SalaryAccountFetchDAO;
import com.tenpay.wxwork.salary.dto.h5.*;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.presvr.sender.bean.FetchAmountRes;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author : wg
 * @description :
 * @date : 2018/8/19
 */
@Service
public class FetchAmountService {

    @Autowired
    private BankProxyRequestService bankProxyRequestService;
    @Autowired
    private SalaryAccountDAO salaryAccountDAO;
    @Autowired
    private SalaryAccountFetchDAO salaryAccountFetchDAO;
    @Autowired
    private KeyCacheService keyCacheService;

    public FetchAmountRes fetch(FetchAmountReqParam fetchAmountReqParam, String corpid, String userid, String bankType) {

        //SalaryAccount salaryAccount = salaryAccountDAO.queryByUserId(corpid,userid);
        fetchAmountReqParam.setBankType(bankType);
        //建行提现联行号不传送
        fetchAmountReqParam.setRPPDBnk_BrNo("");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = dateFormat.format(new Date());

        FetchAmountRes fetchAmountRes = bankProxyRequestService.fetchAmount(fetchAmountReqParam);

        String endTime = dateFormat.format(new Date());
        Double fitch_amount = fetchAmountReqParam.getZZ_amt()*100;
        if ("0".equals(fetchAmountRes.getRetcode())){
            //成功
            salaryAccountFetchDAO.insertNewAccountFetch(corpid,userid,11,fetchAmountRes.getSYS_EVT_TRACE_ID(),AesUtil.encrypt(fetchAmountReqParam.getCst_AccNo(),keyCacheService.getAccountSecret()),AesUtil.encrypt(fetchAmountReqParam.getZZ_ass_acct_no(),keyCacheService.getAccountSecret()),fitch_amount.toString(),
                    fetchAmountReqParam.getCard_banks_relation(),"",startTime,endTime);
        }

        return  fetchAmountRes;

    }
}
