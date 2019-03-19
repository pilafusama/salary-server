package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class QueryExistCategory2AccountsReqParam extends FrontEndReqBase {

    @NotBlank(message = "Crdt_No不能为空")
    @Length(max = 120)
    @JsonProperty(value="Crdt_No",required=true)
    private String Crdt_No;

    //@NotBlank(message = "DbCrd_CardNo不能为空")
    @Length(max = 19)
    @JsonProperty(value="DbCrd_CardNo",required=false)
    private String DbCrd_CardNo;

    @JsonIgnore
    public String getCrdt_No() {
        return Crdt_No;
    }
    @JsonIgnore
    public void setCrdt_No(String crdt_No) {
        Crdt_No = crdt_No;
    }
    @JsonIgnore
    public String getDbCrd_CardNo() {
        return DbCrd_CardNo;
    }
    @JsonIgnore
    public void setDbCrd_CardNo(String dbCrd_CardNo) {
        DbCrd_CardNo = dbCrd_CardNo;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Crdt_No=");
        sb.append(RelayCodeUtils.encode(this.getCrdt_No()));
        sb.append("&DbCrd_CardNo=");
        sb.append(RelayCodeUtils.encode(this.getDbCrd_CardNo()));
        sb.append("&bankType=");
        sb.append(RelayCodeUtils.encode(this.getBankType()));
        sb.append("&sign=");
        sb.append(RelayCodeUtils.encode(this.getSign()));
        sb.append("&nonce=");
        sb.append(RelayCodeUtils.encode(this.getNonce()));
        return sb.toString();
    }
}
