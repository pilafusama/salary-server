package com.tenpay.wxwork.salary.model;

import java.io.Serializable;

public class SalaryCorpConfInfo implements Serializable {

    //状态
    public enum State {
        AUDITING,
        AUDITED,
        DELETED
    }
    public String corpid;//企业id
    private String state;//状态
    private String type;//配置类型
    private String key;
    private String value;

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
