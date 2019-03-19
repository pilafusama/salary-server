package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenpay.bap.relay.utils.RelayCodeUtils;
import com.tenpay.wxwork.salary.dto.FrontEndReqBase;

public class UploadIdCardPhotosReqParam  extends FrontEndReqBase {

    @JsonProperty(required=false)
    private String file_date;
    @JsonProperty(required=false)
    private String file_nm;
    @JsonProperty(required=false)
    private String send_file;
    @JsonProperty(required=true)
    private String corpid;
    @JsonProperty(required=true)
    private String userid;
    private String password;
    private String vectorKey;


    @JsonIgnore
    public String getFile_date() {
        return file_date;
    }
    @JsonIgnore
    public void setFile_date(String file_date) {
        this.file_date = file_date;
    }
    @JsonIgnore
    public String getFile_nm() {
        return file_nm;
    }
    @JsonIgnore
    public void setFile_nm(String file_nm) {
        this.file_nm = file_nm;
    }
    @JsonIgnore
    public String getSend_file() {
        return send_file;
    }
    @JsonIgnore
    public void setSend_file(String send_file) {
        this.send_file = send_file;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVectorKey() {
        return vectorKey;
    }

    public void setVectorKey(String vectorKey) {
        this.vectorKey = vectorKey;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("File_Date=");
        sb.append(RelayCodeUtils.encode(this.getFile_date()));
        sb.append("&File_Nm=");
        sb.append(RelayCodeUtils.encode(this.getFile_nm()));
        sb.append("&Send_File=");
        sb.append(RelayCodeUtils.encode(this.getSend_file()));
        sb.append("&corpid=");
        sb.append(RelayCodeUtils.encode(this.getCorpid()));
        sb.append("&userid=");
        sb.append(RelayCodeUtils.encode(this.getUserid()));
        sb.append("&bankType=");
        sb.append(RelayCodeUtils.encode(this.getBankType()));
        sb.append("&sign=");
        sb.append(RelayCodeUtils.encode(this.getSign()));
        sb.append("&nonce=");
        sb.append(RelayCodeUtils.encode(this.getNonce()));
        return sb.toString();
    }
}
