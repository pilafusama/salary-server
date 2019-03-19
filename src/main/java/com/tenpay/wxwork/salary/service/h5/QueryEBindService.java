package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.QueryEBindReqParam;
import com.tenpay.wxwork.salary.presvr.sender.bean.QueryEBindRes;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : wg
 * @description :
 * @date : 2018/8/19
 */
@Service
public class QueryEBindService {

    @Autowired
    private BankProxyRequestService bankProxyRequestService;
    @Autowired
    private SalaryAccountDAO salaryAccountDAO;
    @Autowired
    private KeyCacheService keyCacheService;


    public QueryEBindRes queryE( String corpid,String userid, String bankType) {

        /**
         * 2.有两种查询方式
         a)根据证件号码（二代身份证）查询：根据ssid获得用户ID（userid）和企业ID（corpid），查找数据表t_salary_account，得到身份证号并赋值给字段Crdt_No，设置func_code字段为1
         b)根据第三方APP用户ID查询：根据ssid获得用户ID（userid），赋值给字段Mbsh_No，并设置func_code字段为2
         */

        SalaryAccount salaryAccount = salaryAccountDAO.queryByUserId(corpid,userid);
        QueryEBindReqParam queryEBindReqParam = new QueryEBindReqParam();
//        queryEBindReqParam.setBankType(salaryAccount.getSalary_card_bank_type());
        queryEBindReqParam.setBankType(bankType);
        queryEBindReqParam.setCrdt_No(AesUtil.decrypt(salaryAccount.getCre_id(),keyCacheService.getAccountSecret()));
        queryEBindReqParam.setFunc_code("1");

        //第二种方式,userid现在还不明确,所以先注释
       /*request.setMbsh_No(salaryAccount.getUserid());
       request.setFunc_code("2");*/

        QueryEBindRes queryEBindRes = bankProxyRequestService.queryEBind(queryEBindReqParam);
        return  queryEBindRes;
    }
}
