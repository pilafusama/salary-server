package com.tenpay.wxwork.salary.presvr.sender.bean;

import java.util.List;
import com.tenpay.bap.relay.protocol.BankProxyRelayResponseMsg;
import com.tenpay.wxwork.salary.model.Ea02Group;

public class QueryExistCategory2AccountsRes extends BankProxyRelayResponseMsg {
    private static final String VLD_RCRD_CNT = "Vld_Rcrd_Cnt";
    private static final String EBNK_CST_PLTFRM_ID = "Ebnk_Cst_Pltfrm_ID";
    private static final String EA02_GROUP = "ea02_group_str";

    private String Vld_Rcrd_Cnt;
    private String Ebnk_Cst_Pltfrm_ID;
    private List<Ea02Group> ea02_group;
    private String ea02_group_str;

    public QueryExistCategory2AccountsRes(int result, String resInfo, String bankResult, String bankResInfo) {
        super(result, resInfo, bankResult, bankResInfo);
    }

    public  void setResponse(String Vld_Rcrd_Cnt, String Ebnk_Cst_Pltfrm_ID, String ea02_group_str){
        setVld_Rcrd_Cnt(Vld_Rcrd_Cnt);
        setEbnk_Cst_Pltfrm_ID(Ebnk_Cst_Pltfrm_ID);
        setEa02_group_str(ea02_group_str);
    }

    public String getVld_Rcrd_Cnt() {
        this.Vld_Rcrd_Cnt = this.get(VLD_RCRD_CNT);
        return Vld_Rcrd_Cnt;
    }

    public void setVld_Rcrd_Cnt(String vld_Rcrd_Cnt) {
        this.put(VLD_RCRD_CNT,vld_Rcrd_Cnt);
        Vld_Rcrd_Cnt = vld_Rcrd_Cnt;
    }

    public String getEbnk_Cst_Pltfrm_ID() {
        this.Ebnk_Cst_Pltfrm_ID = this.get(EBNK_CST_PLTFRM_ID);
        return Ebnk_Cst_Pltfrm_ID;
    }

    public void setEbnk_Cst_Pltfrm_ID(String ebnk_Cst_Pltfrm_ID) {
        this.put(EBNK_CST_PLTFRM_ID,ebnk_Cst_Pltfrm_ID);
        Ebnk_Cst_Pltfrm_ID = ebnk_Cst_Pltfrm_ID;
    }

    public List<Ea02Group> getEa02_group() {
        return ea02_group;
    }

    public void setEa02_group(List<Ea02Group> ea02_group) {
        this.ea02_group = ea02_group;
    }

    public String getEa02_group_str() {
        this.ea02_group_str =  this.get(EA02_GROUP);
        return ea02_group_str;
    }

    public void setEa02_group_str(String ea02_group_str) {
        this.put(EA02_GROUP,ea02_group_str);
        this.ea02_group_str = ea02_group_str;
    }
}
