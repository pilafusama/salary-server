package com.tenpay.wxwork.salary.presvr.sender.bean;

import com.tenpay.bap.relay.protocol.BankProxyRelayRequestMsg;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.dto.h5.BalanceReqParam;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import org.apache.commons.lang3.StringUtils;

public class BalanceReq extends BankProxyRelayRequestMsg {

    private static final String DbCRD_CARDNO  = "DbCrd_CardNo";
    private static final String CCYCD  = "CcyCd";
    private static final String CSHEX_CD  = "CshEx_Cd";

    private String DbCrd_CardNo; //借记卡卡号
    private String CcyCd; //币种代码
    private String CshEx_Cd; //钞汇代码

    public BalanceReq(BankProxyRelayRequestMsg msg) {
        super(msg.getRawStr());
    }

   public void setRequest(SalaryAccount fsalaryCard, BalanceReqParam request)
    {
        this.setDbCrd_CardNo(fsalaryCard.getSalary_card_number());
        this.setCcyCd(request.getCcyCd());
        this.setCshEx_Cd(request.getCshEx_Cd());
        this.validate();
    }

    public String getDbCrd_CardNo() {
        return DbCrd_CardNo;
    }

    public void setDbCrd_CardNo(String dbCrd_CardNo) {
        this.put(DbCRD_CARDNO, dbCrd_CardNo);
        this.DbCrd_CardNo = dbCrd_CardNo;
    }

    public String getCcyCd() {
        return CcyCd;
    }

    public void setCcyCd(String ccyCd) {
        this.put(CCYCD, ccyCd);
        this.CcyCd = ccyCd;
    }

    public String getCshEx_Cd() {
        return CshEx_Cd;
    }

    public void setCshEx_Cd(String cshEx_Cd) {
        this.put(CSHEX_CD, cshEx_Cd);
        this.CshEx_Cd = cshEx_Cd;
    }

    private void validate()
    {
        if(StringUtils.isBlank(this.getDbCrd_CardNo())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "DbCrd_CardNo is empty.");
        }

        if(StringUtils.isBlank(this.getCcyCd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "CcyCd is empty.");
        }

        if(StringUtils.isBlank(this.getCshEx_Cd())){
            throw new BizException(BizError.PARAM_NOT_EXIST.errorCode(), "CshEx_Cd is empty.");
        }
    }
}
