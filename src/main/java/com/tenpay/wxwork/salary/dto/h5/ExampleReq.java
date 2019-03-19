package com.tenpay.wxwork.salary.dto.h5;

import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/19
 */
public class ExampleReq extends BankProxyRelayRequestMsg {

    private static final String TEST_ID = "test_id";
    private static final String TEST_NAME = "test_name";

    private String testId;

    private String testName;

    public ExampleReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }

    public void setRequest(ExampleReqParam request, String bankType)
    {

        this.setTestId(request.getTestId());
        this.setTestName(request.getTestName());
        this.validate();
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.put(TEST_ID,testId);
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.put(TEST_NAME,testName);
        this.testName = testName;
    }

    private void validate()
    {
        if(StringUtils.isBlank(this.getTestId())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "test_id is empty.");
        }
        if(StringUtils.isBlank(this.getTestName())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "test_name is empty.");
        }
    }
}
