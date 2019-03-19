package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;


/**
 * @author : wg
 * @description :
 * @date : 2018/8/19
 */
public class QueryEBindReqParam extends FrontEndReqBase {

    //1-根据证件号码（二代身份证）查询
    //2-根据第三方APP用户ID查询
    //@NotBlank(message = "Func_code不能为空")
    @Length(min = 1, max = 1)
    @JsonProperty(required=true)
    private String Func_code; //功能号

    @Length( max = 120)
    @JsonProperty()
    private String Crdt_No; //证件号码


    @Length( max = 600)
    @JsonProperty(value="Mbsh_No")
    private String Mbsh_No;//第三方APP用户ID



    @JsonIgnore
    public String getFunc_code() {
        return Func_code;
    }

    @JsonIgnore
    public void setFunc_code(String Func_code) {
        this.Func_code = Func_code;
    }


    @JsonIgnore
    public String getMbsh_No() {
        return Mbsh_No;
    }
    @JsonIgnore
    public void setMbsh_No(String Mbsh_No) {
        this.Mbsh_No = Mbsh_No;
    }

    public String getCrdt_No() {
        return Crdt_No;
    }

    public void setCrdt_No(String crdt_No) {
        Crdt_No = crdt_No;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();


        sb.append("&Func_code=");
        sb.append(RelayCodeUtils.encode(this.getFunc_code()));
        if (this.getFunc_code().equals("1")){
            sb.append("&Crdt_No=");
            sb.append(RelayCodeUtils.encode((this.getCrdt_No())));
        }
        if(this.getFunc_code().equals("2")){
            sb.append("&Mbsh_No=");
            sb.append(RelayCodeUtils.encode((this.getMbsh_No())));
        }

        return sb.toString();
    }
}
