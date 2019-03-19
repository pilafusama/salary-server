package com.tenpay.wxwork.salary.dto.admin;

/**
 * Created by wg on 2018/11/16
 */
public class PageHelperParam {

    private int pageNum; //页数
    private int pageSize; //每页显示条数

    private String name; //员工姓名
    private String department_id; //部门ID
    private String bank_sname; //发卡银行
    private String state_ch; //开通状态

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getBank_sname() {
        return bank_sname;
    }

    public void setBank_sname(String bank_sname) {
        this.bank_sname = bank_sname;
    }

    public String getState_ch() {
        return state_ch;
    }

    public void setState_ch(String state_ch) {
        this.state_ch = state_ch;
    }
}
