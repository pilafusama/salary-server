package com.tenpay.wxwork.salary.dto.admin;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.util.download.ExcelDownloadAnnotation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author : treesen
 * @description :
 * @date : 2018/9/6
 */
public class UplaodPayroll extends FrontEndResponse implements Serializable {

    @ExcelDownloadAnnotation(cnName="序号", order=1)
    private Integer fsequence_number;//序号
    @ExcelDownloadAnnotation(cnName="部门", order=2)
    private String fdepartment_name;//部门
    @ExcelDownloadAnnotation(cnName="姓名", order=3)
    private String fuser_name;//姓名
    @ExcelDownloadAnnotation(cnName="企业微信号", order=4)
    private String fuserid;//员工微信号
    @ExcelDownloadAnnotation(cnName="基本工资", order=5,format="#,#0.00")
    private BigDecimal fbasic_salary;//基本工资
    @ExcelDownloadAnnotation(cnName="绩效工资", order=6,format="#,#0.00")
    private BigDecimal fperformance_salary;//绩效工资
    @ExcelDownloadAnnotation(cnName="加班工资", order=7,format="#,#0.00")
    private BigDecimal fovertime_salary;//加班工资
    @ExcelDownloadAnnotation(cnName="话费补贴", order=8,format="#,#0.00")
    private BigDecimal fphone_allowance;//话费补贴
    @ExcelDownloadAnnotation(cnName="交通补贴", order=9,format="#,#0.00")
    private BigDecimal ftransport_allowance;//交通补贴
    @ExcelDownloadAnnotation(cnName="餐费补贴", order=10,format="#,#0.00")
    private BigDecimal fmeal_allowance;//餐费补贴
    @ExcelDownloadAnnotation(cnName="扣款", order=11,format="#,#0.00")
    private BigDecimal fdeduction;//扣款
    @ExcelDownloadAnnotation(cnName="养老保险", order=12,format="#,#0.00")
    private BigDecimal fendowment_insurance;//养老保险
    @ExcelDownloadAnnotation(cnName="医疗保险", order=13,format="#,#0.00")
    private BigDecimal fmedical_insurance;//医疗保险
    @ExcelDownloadAnnotation(cnName="失业保险", order=14,format="#,#0.00")
    private BigDecimal funemployment_insurance;//失业保险
    @ExcelDownloadAnnotation(cnName="工伤保险", order=15,format="#,#0.00")
    private BigDecimal femployment_injury_insurance;//工伤保险
    @ExcelDownloadAnnotation(cnName="住房公积金", order=16,format="#,#0.00")
    private BigDecimal fhousing_fund;//住房公积金
    @ExcelDownloadAnnotation(cnName="税前工资合计", order=17,format="#,#0.00")
    private BigDecimal fbefore_tax_salary_sum;//税前工资合计
    @ExcelDownloadAnnotation(cnName="个人所得税", order=18,format="#,#0.00")
    private BigDecimal findividual_income_tax;//个人所得税
    @ExcelDownloadAnnotation(cnName="实发金额", order=19,format="#,#0.00")
    private BigDecimal factual_salary;//实发金额

    private String fmonth;//月份
    private String fcorpid;//企业id
    private String foperator_userid;//工资操作人员用户id
    private String fcard_number;//工资卡
    private String fcard_bank_number;//工资卡的发卡行联行号
    private String fbank_cards_relation;//工资卡与二类户所属银行间关系

    public Integer getFsequence_number() {
        return fsequence_number;
    }

    public void setFsequence_number(Integer fsequence_number) {
        this.fsequence_number = fsequence_number;
    }

    public String getFdepartment_name() {
        return fdepartment_name;
    }

    public void setFdepartment_name(String fdepartment_name) {
        this.fdepartment_name = fdepartment_name;
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

    public BigDecimal getFbasic_salary() {
        return fbasic_salary;
    }

    public void setFbasic_salary(BigDecimal fbasic_salary) {
        this.fbasic_salary = fbasic_salary;
    }

    public BigDecimal getFperformance_salary() {
        return fperformance_salary;
    }

    public void setFperformance_salary(BigDecimal fperformance_salary) {
        this.fperformance_salary = fperformance_salary;
    }

    public BigDecimal getFovertime_salary() {
        return fovertime_salary;
    }

    public void setFovertime_salary(BigDecimal fovertime_salary) {
        this.fovertime_salary = fovertime_salary;
    }

    public BigDecimal getFphone_allowance() {
        return fphone_allowance;
    }

    public void setFphone_allowance(BigDecimal fphone_allowance) {
        this.fphone_allowance = fphone_allowance;
    }

    public BigDecimal getFtransport_allowance() {
        return ftransport_allowance;
    }

    public void setFtransport_allowance(BigDecimal ftransport_allowance) {
        this.ftransport_allowance = ftransport_allowance;
    }

    public BigDecimal getFmeal_allowance() {
        return fmeal_allowance;
    }

    public void setFmeal_allowance(BigDecimal fmeal_allowance) {
        this.fmeal_allowance = fmeal_allowance;
    }

    public BigDecimal getFdeduction() {
        return fdeduction;
    }

    public void setFdeduction(BigDecimal fdeduction) {
        this.fdeduction = fdeduction;
    }

    public BigDecimal getFendowment_insurance() {
        return fendowment_insurance;
    }

    public void setFendowment_insurance(BigDecimal fendowment_insurance) {
        this.fendowment_insurance = fendowment_insurance;
    }

    public BigDecimal getFmedical_insurance() {
        return fmedical_insurance;
    }

    public void setFmedical_insurance(BigDecimal fmedical_insurance) {
        this.fmedical_insurance = fmedical_insurance;
    }

    public BigDecimal getFunemployment_insurance() {
        return funemployment_insurance;
    }

    public void setFunemployment_insurance(BigDecimal funemployment_insurance) {
        this.funemployment_insurance = funemployment_insurance;
    }

    public BigDecimal getFemployment_injury_insurance() {
        return femployment_injury_insurance;
    }

    public void setFemployment_injury_insurance(BigDecimal femployment_injury_insurance) {
        this.femployment_injury_insurance = femployment_injury_insurance;
    }

    public BigDecimal getFhousing_fund() {
        return fhousing_fund;
    }

    public void setFhousing_fund(BigDecimal fhousing_fund) {
        this.fhousing_fund = fhousing_fund;
    }

    public BigDecimal getFbefore_tax_salary_sum() {
        return fbefore_tax_salary_sum;
    }

    public void setFbefore_tax_salary_sum(BigDecimal fbefore_tax_salary_sum) {
        this.fbefore_tax_salary_sum = fbefore_tax_salary_sum;
    }

    public BigDecimal getFindividual_income_tax() {
        return findividual_income_tax;
    }

    public void setFindividual_income_tax(BigDecimal findividual_income_tax) {
        this.findividual_income_tax = findividual_income_tax;
    }

    public BigDecimal getFactual_salary() {
        return factual_salary;
    }

    public void setFactual_salary(BigDecimal factual_salary) {
        this.factual_salary = factual_salary;
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

    public String getFoperator_userid() {
        return foperator_userid;
    }

    public void setFoperator_userid(String foperator_userid) {
        this.foperator_userid = foperator_userid;
    }

    public String getFcard_number() {
        return fcard_number;
    }

    public void setFcard_number(String fcard_number) {
        this.fcard_number = fcard_number;
    }

    public String getFcard_bank_number() {
        return fcard_bank_number;
    }

    public void setFcard_bank_number(String fcard_bank_number) {
        this.fcard_bank_number = fcard_bank_number;
    }

    public String getFbank_cards_relation() {
        return fbank_cards_relation;
    }

    public void setFbank_cards_relation(String fbank_cards_relation) {
        this.fbank_cards_relation = fbank_cards_relation;
    }
}
