package com.tenpay.wxwork.salary.model;

import com.tenpay.wxwork.salary.util.download.ExcelDownloadAnnotation;

import java.io.Serializable;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/21
 */
public class Example implements Serializable {

    @ExcelDownloadAnnotation(cnName="系统编码", order=1)
    private String sys_id;
    @ExcelDownloadAnnotation(cnName="用户id", order=2)
    private String user_id;
    @ExcelDownloadAnnotation(cnName="用户姓名", order=3)
    private String name;

    public String getSys_id() {
        return sys_id;
    }

    public void setSys_id(String sys_id) {
        this.sys_id = sys_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
