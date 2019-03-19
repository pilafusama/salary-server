package com.tenpay.wxwork.salary.model;

/**
 * t_salary_fields 工资字段表
 *
 */
public class SalaryFields {

    private String corpid;

    private String salaryFields;

    private String deductionFields;

    private String remark;

    private String createTime;

    private String modifyTime;

    /**
     * 是否选择模板
     *
     */
    public enum TemplateSelect {
        YES,
        NO
    }

    private TemplateSelect template;


    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getSalaryFields() {
        return salaryFields;
    }

    public void setSalaryFields(String salaryFields) {
        this.salaryFields = salaryFields;
    }

    public String getDeductionFields() {
        return deductionFields;
    }

    public void setDeductionFields(String deductionFields) {
        this.deductionFields = deductionFields;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public TemplateSelect getTemplate() {
        return template;
    }

    public void setTemplate(TemplateSelect template) {
        this.template = template;
    }
}