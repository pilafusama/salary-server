package com.tenpay.wxwork.salary.service.h5;

import java.util.List;

import com.tenpay.wxwork.salary.service.KeyCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.dto.h5.QueryExistCategory2AccountsReqParam;
import com.tenpay.wxwork.salary.model.Ea02Group;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.presvr.sender.bean.QueryExistCategory2AccountsRes;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.SessionService;

@Service
public class QueryExistCategory2AccountsService {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private BankProxyRequestService bankProxyRequestService;
    @Autowired
    private SalaryAccountDAO dao;
    @Autowired
    private KeyCacheService keyCacheService;

    public QueryExistCategory2AccountsRes queryExistCategory2Accounts(String ssid) {
        QueryExistCategory2AccountsReqParam reqParam = new QueryExistCategory2AccountsReqParam();
        SessionInfo session = sessionService.getSession(ssid);
        if (session == null)
        {
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),BizError.GET_SESSION_FAILED.errorMsg());
        }
        SalaryAccount salaryAccount = dao.queryByUserId(session.getCorpId(), session.getUserId());
//        reqParam.setBankType(salaryAccount.getSalary_card_bank_type());
        reqParam.setBankType(session.getBankType());
        reqParam.setCrdt_No(AesUtil.decrypt(salaryAccount.getCre_id(),keyCacheService.getAccountSecret()));
        QueryExistCategory2AccountsRes res = bankProxyRequestService.queryExistCategory2Accounts(reqParam);
        List<Ea02Group> ea02Groups = JSONArray.parseArray(res.getEa02_group_str(),Ea02Group.class);
        res.setEa02_group(ea02Groups);
        return res;
    }

}
