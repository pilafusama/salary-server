package com.tenpay.wxwork.salary.dto.admin;

import com.tenpay.wxwork.salary.util.download.ExcelDownloadAnnotation;

import java.io.Serializable;

/**
 * Created by hxyh on 2018/8/22.
 */
public class DownLoadSalaryReq implements Serializable {

    @ExcelDownloadAnnotation(cnName="序号(必填)", order=1)
    private String f_no;
    @ExcelDownloadAnnotation(cnName="账号(必填)", order=2)
    private String fsalary_card_number;
    @ExcelDownloadAnnotation(cnName="户名(必填)", order=3)
    private String fcre_name;
    @ExcelDownloadAnnotation(cnName="金额(必填)", order=4)
    private String f_momeny;
    @ExcelDownloadAnnotation(cnName="跨行标识(选填 建行填0 他行填1)", order=5)
    private String f_bankmark;
    @ExcelDownloadAnnotation(cnName="行名(跨行业务与联行行号不能同时为空)", order=6)
    private String f_bankname;
    @ExcelDownloadAnnotation(cnName="联行行号(跨行业务与行名不能同时为空)", order=7)
    private String f_bankno;
    @ExcelDownloadAnnotation(cnName="摘要(选填 显示在收款账户流水明细中)", order=8)
    private String f_mark;
    @ExcelDownloadAnnotation(cnName="备注(选填)", order=9)
    private String f_remark;

    private String f_corpId;

    private String month;//月份

    private String f_userid;

    private int batch_no;//此月份第几次发薪

    public String getF_no() {
        return f_no;
    }

    public void setF_no(String f_no) {
        this.f_no = f_no;
    }

    public String getFsalary_card_number() {
        return fsalary_card_number;
    }

    public void setFsalary_card_number(String fsalary_card_number) {
        this.fsalary_card_number = fsalary_card_number;
    }

    public String getFcre_name() {
        return fcre_name;
    }

    public void setFcre_name(String fcre_name) {
        this.fcre_name = fcre_name;
    }

    public String getF_momeny() {
        return f_momeny;
    }

    public void setF_momeny(String f_momeny) {
        this.f_momeny = f_momeny;
    }

    public String getF_bankmark() {
        return f_bankmark;
    }

    public void setF_bankmark(String f_bankmark) {
        this.f_bankmark = f_bankmark;
    }

    public String getF_bankname() {
        return f_bankname;
    }

    public void setF_bankname(String f_bankname) {
        this.f_bankname = f_bankname;
    }

    public String getF_bankno() {
        return f_bankno;
    }

    public void setF_bankno(String f_bankno) {
        this.f_bankno = f_bankno;
    }

    public String getF_mark() {
        return f_mark;
    }

    public void setF_mark(String f_mark) {
        this.f_mark = f_mark;
    }

    public String getF_remark() {
        return f_remark;
    }

    public void setF_remark(String f_remark) {
        this.f_remark = f_remark;
    }

    public String getF_corpId() {
        return f_corpId;
    }

    public void setF_corpId(String f_corpId) {
        this.f_corpId = f_corpId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getF_userid() {
        return f_userid;
    }

    public void setF_userid(String f_userid) {
        this.f_userid = f_userid;
    }

    public int getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(int batch_no) {
        this.batch_no = batch_no;
    }
}
