package com.tenpay.wxwork.salary.service.h5;

import com.tenpay.wxwork.salary.dao.CorpApprovalInfoDAO;
import com.tenpay.wxwork.salary.dao.ExampleDAO;
import com.tenpay.wxwork.salary.dto.h5.ExampleReq;
import com.tenpay.wxwork.salary.dto.h5.ExampleReqParam;
import com.tenpay.wxwork.salary.dto.h5.ExampleRes;
import com.tenpay.wxwork.salary.model.Example;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.model.User;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/19
 */
@Service
public class ExampleService {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private BankProxyRequestService bankProxyRequestService;
    @Autowired
    private ExampleDAO exampleDAO;

    public String testMethod(String ssid, ExampleReqParam request) {

//        SessionInfo session = sessionService.getSession(ssid);

        ExampleRes exampleRes = bankProxyRequestService.example(request);

        return exampleRes.getMyMoney();
    }

    public List<Example> getTestData(Example example){
        return exampleDAO.getTestData(example);
    }
}
