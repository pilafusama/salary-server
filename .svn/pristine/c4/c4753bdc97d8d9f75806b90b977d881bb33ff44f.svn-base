package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.ReuseSalaryAccountReqParam;
import org.apache.commons.lang3.StringUtils;

public class ReuseSalaryAccountReq extends BankProxyRelayRequestMsg {

    private static final String SMS = "sms";
    private static final String FUNC_CODE = "func_code";
    private static final String CHNL_CUST_NO = "chnl_cust_no";
    private static final String EBNK_SIGN_ACCNO = "ebnk_sign_accno";
    private static final String PLTFRM_NM = "pltfrm_nm";
    private static final String HDL_INSID = "hdl_insid";
    private static final String SIGN_DT = "sign_dt";
    private static final String MBLPH_NO = "mblph_no";
    private static final String MBSH_NO = "mbsh_no";

    private String sms;
    private String func_code;
    private String chnl_cust_no;
    private String ebnk_sign_accno;
    private String pltfrm_nm;
    private String hdl_insid;
    private String sign_dt;
    private String mblph_no;
    private String mbsh_no;

    public ReuseSalaryAccountReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }

    public void setRequest(ReuseSalaryAccountReqParam request, String bankType){
        this.setSms(request.getSms());
        this.setFunc_code(request.getFunc_code());
        this.setChnl_cust_no(request.getChnl_cust_no());
        this.setEbnk_sign_accno(request.getEbnk_sign_accno());
        this.setPltfrm_nm(request.getPltfrm_nm());
        this.setHdl_insid(request.getHdl_insid());
        this.setSign_dt(request.getSign_dt());
        this.setMblph_no(request.getMblph_no());
        this.setMbsh_no(request.getMbsh_no());
        this.validate();

    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.put(SMS, sms);
        this.sms = sms;
    }

    public String getFunc_code() {
        return func_code;
    }

    public void setFunc_code(String func_code) {
        this.put(FUNC_CODE, func_code);
        this.func_code = func_code;
    }

    public String getChnl_cust_no() {
        return chnl_cust_no;
    }

    public void setChnl_cust_no(String chnl_cust_no) {
        this.put(CHNL_CUST_NO, chnl_cust_no);
        this.chnl_cust_no = chnl_cust_no;
    }

    public String getEbnk_sign_accno() {
        return ebnk_sign_accno;
    }

    public void setEbnk_sign_accno(String ebnk_sign_accno) {
        this.put(EBNK_SIGN_ACCNO, ebnk_sign_accno);
        this.ebnk_sign_accno = ebnk_sign_accno;
    }

    public String getPltfrm_nm() {
        return pltfrm_nm;
    }

    public void setPltfrm_nm(String pltfrm_nm) {
        this.put(PLTFRM_NM, pltfrm_nm);
        this.pltfrm_nm = pltfrm_nm;
    }

    public String getHdl_insid() {
        return hdl_insid;
    }

    public void setHdl_insid(String hdl_insid) {
        this.put(HDL_INSID, hdl_insid);
        this.hdl_insid = hdl_insid;
    }

    public String getSign_dt() {
        return sign_dt;
    }

    public void setSign_dt(String sign_dt) {
        this.put(SIGN_DT, sign_dt);
        this.sign_dt = sign_dt;
    }

    public String getMblph_no() {
        return mblph_no;
    }

    public void setMblph_no(String mblph_no) {
        this.put(MBLPH_NO, mblph_no);
        this.mblph_no = mblph_no;
    }

    public String getMbsh_no() {
        return mbsh_no;
    }

    public void setMbsh_no(String mbsh_no) {
        this.put(MBSH_NO, mbsh_no);
        this.mbsh_no = mbsh_no;
    }


    private void validate()
    {
        if(StringUtils.isBlank(this.getSms())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "SMS is empty.");
        }
        if(StringUtils.isBlank(this.getMblph_no())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Mblph_no is empty.");
        }
        if(StringUtils.isBlank(this.getChnl_cust_no())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "SMS is empty.");
        }
        if(StringUtils.isBlank(this.getMblph_no())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Mblph_no is empty.");
        }
        if(StringUtils.isBlank(this.getSms())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "SMS is empty.");
        }
        if(StringUtils.isBlank(this.getMblph_no())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "Mblph_no is empty.");
        }
    }
}
