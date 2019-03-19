package com.tenpay.wxwork.salary.dto.admin;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.util.download.ExcelDownloadAnnotation;

import java.io.Serializable;

public class SalaryDetailBackInfo extends FrontEndResponse implements Serializable {
    @ExcelDownloadAnnotation(cnName="序号", order=1)
    private Integer fsequence_number;//序号
    @ExcelDownloadAnnotation(cnName="姓名", order=2)
    private String fuser_name;//姓名
    @ExcelDownloadAnnotation(cnName="企业微信ID", order=3)
    private String fuserid;//企业微信号
    @ExcelDownloadAnnotation(cnName="是否确认", order=4)
    private String fsure_state;//状态
    private String fcorpid;//企业id
    private String unfcorpid;
    private String fmonth;//月份

    public Integer getFsequence_number() {
        return fsequence_number;
    }

    public void setFsequence_number(Integer fsequence_number) {
        this.fsequence_number = fsequence_number;
    }

    public String getFuser_name() {
        return fuser_name;
    }

    public void setFuser_name(String fuser_name) {
        this.fuser_name = fuser_name;
    }

    public String getFuserid() {
        return fuserid;
    }

    public void setFuserid(String fuserid) {
        this.fuserid = fuserid;
    }

    public String getFsure_state() {
        return fsure_state;
    }

    public void setFsure_state(String fsure_state) {
        this.fsure_state = fsure_state;
    }

    public String getFmonth() {
        return fmonth;
    }

    public void setFmonth(String fmonth) {
        this.fmonth = fmonth;
    }

    public String getFcorpid() {
        return fcorpid;
    }

    public void setFcorpid(String fcorpid) {
        this.fcorpid = fcorpid;
    }

    public String getUnfcorpid() {
        return unfcorpid;
    }

    public void setUnfcorpid(String unfcorpid) {
        this.unfcorpid = unfcorpid;
    }
}
