package com.tenpay.wxwork.salary.model;

/**
 * Created by win7 on 2018/10/17.
 */
public class MsgNotify {

    //业务类型 1:开户消息 2:发薪消息
    public static int BUSI_TYPE_OPEN = 1;
    public static int BUSI_TYPE_SALARY = 2;

    public enum State{
        TO_NOTIFY,
        SUCCESS,
        FAIL
    }
    private String busi_type;
    private State state;
    private String busi_id;
    private String retry_times;
    private String last_notify_time;
    private String create_time;
    private String modify_time;

    public String getBusi_type() {
        return busi_type;
    }

    public void setBusi_type(String busi_type) {
        this.busi_type = busi_type;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getBusi_id() {
        return busi_id;
    }

    public void setBusi_id(String busi_id) {
        this.busi_id = busi_id;
    }

    public String getRetry_times() {
        return retry_times;
    }

    public void setRetry_times(String retry_times) {
        this.retry_times = retry_times;
    }

    public String getLast_notify_time() {
        return last_notify_time;
    }

    public void setLast_notify_time(String last_notify_time) {
        this.last_notify_time = last_notify_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }
}
