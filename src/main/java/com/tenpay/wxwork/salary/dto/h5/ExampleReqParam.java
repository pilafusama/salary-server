package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/19
 */
public class ExampleReqParam extends FrontEndReqBase {

    @NotBlank(message = "testId不能为空")
    @Length(min = 1, max = 100)
    @JsonProperty(required=true)
    private String testId;

    @NotBlank(message = "testName不能为空")
    @Length(min = 1, max = 100)
    @JsonProperty(required=true)
    private String testName;

    //银行标识
    @NotBlank(message = "BankType不能为空")
    @Length(min = 4, max = 4)
    @JsonProperty(required=true)
    private String BankType;

    @JsonIgnore
    public String getBankType() {
        return BankType;
    }

    @JsonIgnore
    public void setBankType(String bankType) {
        BankType = bankType;
    }

    @JsonIgnore
    public String getTestId() {
        return testId;
    }

    @JsonIgnore
    public void setTestId(String testId) {
        this.testId = testId;
    }

    @JsonIgnore
    public String getTestName() {
        return testName;
    }

    @JsonIgnore
    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("testId=");
        sb.append(RelayCodeUtils.encode(this.getTestId()));
        sb.append("&testName=");
        sb.append(RelayCodeUtils.encode(this.getTestName()));
        sb.append("&bankType=");
        sb.append(RelayCodeUtils.encode(this.getBankType()));
        sb.append("&sign=");
        sb.append(RelayCodeUtils.encode(this.getSign()));
        sb.append("&nonce=");
        sb.append(RelayCodeUtils.encode(this.getNonce()));
        return sb.toString();
    }
}
