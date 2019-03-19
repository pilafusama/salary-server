package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.UploadIdCardPhotosReqParam;
import org.apache.commons.lang3.StringUtils;

public class UploadIdCardPhotosReq extends BankProxyRelayRequestMsg {

    private static final String FILE_DATE = "file_date";
    private static final String FILE_NM = "file_nm";
    private static final String SEND_FILE = "send_file";
    private static final String USERID = "userid";
    private static final String CORPID = "corpid";
    private static final String PASSWORD = "password";
    private static final String VECTORKEY = "vectorKey";

    private String password;
    private String vectorKey;
    private String corpid;
    private String userid;
    private String file_date;
    private String file_nm;
    private String send_file;

    public UploadIdCardPhotosReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }

    public void setRequest(UploadIdCardPhotosReqParam req, String bankType){
        this.setFile_date(req.getFile_date());
        this.setFile_nm(req.getFile_nm());
        this.setSend_file(req.getSend_file());
        this.setCorpid(req.getCorpid());
        this.setUserid(req.getUserid());
        this.setPassword(req.getPassword());
        this.setVectorKey(req.getVectorKey());
        this.validate();
    }

    public String getFile_date() {
        return file_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.put(PASSWORD, password);
        this.password = password;
    }

    public String getVectorKey() {
        return vectorKey;
    }

    public void setVectorKey(String vectorKey) {
        this.put(VECTORKEY, vectorKey);
        this.vectorKey = vectorKey;
    }

    public void setFile_date(String file_date) {
        this.put(FILE_DATE,file_date);
        this.file_date = file_date;
    }

    public String getFile_nm() {
        return file_nm;
    }

    public void setFile_nm(String file_nm) {
        this.put(FILE_NM,file_nm);
        this.file_nm = file_nm;
    }

    public String getSend_file() {
        return send_file;
    }

    public void setSend_file(String send_file) {
        this.put(SEND_FILE,send_file);
        this.send_file = send_file;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.put(CORPID, corpid);
        this.corpid = corpid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.put(USERID, userid);
        this.userid = userid;
    }

    private void validate()
    {
        if(StringUtils.isBlank(this.getFile_date())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "File_date is empty.");
        }
        if(StringUtils.isBlank(this.getFile_nm())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "File_nm is empty.");
        }
//        if(StringUtils.isBlank(this.getSend_file())){
//            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Send_File is empty.");
//        }
    }
}
