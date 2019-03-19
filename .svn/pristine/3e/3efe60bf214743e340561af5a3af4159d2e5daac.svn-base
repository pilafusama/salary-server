package com.tenpay.wxwork.salary.dto.h5;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.model.Ea02Group;

public class QueryExistCategory2AccountsResponse extends FrontEndResponse {
    public QueryExistCategory2AccountsResponse(String Vld_Rcrd_Cnt, String Ebnk_Cst_Pltfrm_ID, List<Ea02Group> ea02_group) {
        super();

        this.Vld_Rcrd_Cnt = Vld_Rcrd_Cnt;
        this.Ebnk_Cst_Pltfrm_ID = Ebnk_Cst_Pltfrm_ID;
        this.ea02_group = ea02_group;

    }

    @JsonProperty(value="Vld_Rcrd_Cnt")
    private String Vld_Rcrd_Cnt;
    @JsonProperty(value="Ebnk_Cst_Pltfrm_ID")
    private String Ebnk_Cst_Pltfrm_ID;
    @JsonProperty(value="ea02_group")
    private List<Ea02Group> ea02_group;
    @JsonIgnore
    public String getVld_Rcrd_Cnt() {
        return Vld_Rcrd_Cnt;
    }
    @JsonIgnore
    public void setVld_Rcrd_Cnt(String vld_Rcrd_Cnt) {
        Vld_Rcrd_Cnt = vld_Rcrd_Cnt;
    }
    @JsonIgnore
    public String getEbnk_Cst_Pltfrm_ID() {
        return Ebnk_Cst_Pltfrm_ID;
    }
    @JsonIgnore
    public void setEbnk_Cst_Pltfrm_ID(String ebnk_Cst_Pltfrm_ID) {
        Ebnk_Cst_Pltfrm_ID = ebnk_Cst_Pltfrm_ID;
    }
    @JsonIgnore
    public List<Ea02Group> getEa02_group() {
        return ea02_group;
    }
    @JsonIgnore
    public void setEa02_group(List<Ea02Group> ea02_group) {
        this.ea02_group = ea02_group;
    }


}
