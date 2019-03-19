package com.tenpay.wxwork.salary.dto.admin;

import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.util.download.ExcelDownloadAnnotation;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lcq
 * @description
 * @create 2018/10/16 10:07
 * @since 1.0.0
 */
public class SalaryDetailInfo extends FrontEndResponse implements Serializable {
    @ExcelDownloadAnnotation(cnName="序号", order=1)
    private Integer fsequence_number;//序号
    @ExcelDownloadAnnotation(cnName="月份", order=2)
    private String fmonth;//月份
    @ExcelDownloadAnnotation(cnName="姓名", order=3)
    private String fuser_name;//姓名
    @ExcelDownloadAnnotation(cnName="企业微信号", order=4)
    private String fuserid;//企业微信号
    @ExcelDownloadAnnotation(cnName="基本工资", order=5,format="#,#0.00")
    private BigDecimal fbasic_salary;//基本工资
    @ExcelDownloadAnnotation(cnName="岗位补贴", order=6,format="#,#0.00")
    private BigDecimal fpost_salary;//岗位补贴
    @ExcelDownloadAnnotation(cnName="加班费", order=7,format="#,#0.00")
    private BigDecimal fovertime_salary;//加班费
    @ExcelDownloadAnnotation(cnName="房屋补贴", order=8,format="#,#0.00")
    private BigDecimal fhouse_salary;//房屋补贴
    @ExcelDownloadAnnotation(cnName="绩效全额", order=9,format="#,#0.00")
    private BigDecimal fperformance_salary;//绩效全额
    @ExcelDownloadAnnotation(cnName="绩效得分", order=10)
    private String fperformance_grate;//绩效得分
    @ExcelDownloadAnnotation(cnName="实发", order=11,format="#,#0.00")
    private BigDecimal fperformance_actual;//绩效实发
    @ExcelDownloadAnnotation(cnName="通讯补贴", order=12,format="#,#0.00")
    private BigDecimal fphone_allowance;//通讯补贴
    @ExcelDownloadAnnotation(cnName="其他", order=13,format="#,#0.00")
    private BigDecimal fother;//其他
    @ExcelDownloadAnnotation(cnName="合计", order=14,format="#,#0.00")
    private BigDecimal fshould_get_salary;//应发款合计
    @ExcelDownloadAnnotation(cnName="房租", order=15,format="#,#0.00")
    private BigDecimal fhouse_rent;//房租
    @ExcelDownloadAnnotation(cnName="社保个人缴交", order=16,format="#,#0.00")
    private BigDecimal fsocial_person_tax;//社保个人缴交
    @ExcelDownloadAnnotation(cnName="应纳税所得额", order=17,format="#,#0.00")
    private BigDecimal fshould_tax_money;//应纳税所得额
    @ExcelDownloadAnnotation(cnName="个人所得税", order=18,format="#,#0.00")
    private BigDecimal fperson_tax;//个人所得税
    @ExcelDownloadAnnotation(cnName="病事假/迟到", order=19,format="#,#0.00")
    private BigDecimal fsick_later;//病事假/迟到
    @ExcelDownloadAnnotation(cnName="公积金", order=20,format="#,#0.00")
    private BigDecimal fhousing_fund;//公积金
    @ExcelDownloadAnnotation(cnName="其他扣款", order=21,format="#,#0.00")
    private BigDecimal fother_deduction;//其他扣款
    @ExcelDownloadAnnotation(cnName="合计", order=22,format="#,#0.00")
    private BigDecimal fshould_deduction_salary;//应扣款合计
    @ExcelDownloadAnnotation(cnName="实发额", order=23,format="#,#0.00")
    private BigDecimal factual_salary;//实发额
    @ExcelDownloadAnnotation(cnName="备注", order=24)
    private String fmark;//备注

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

    public String getFmonth() {
        return fmonth;
    }

    public void setFmonth(String fmonth) {
        this.fmonth = fmonth;
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

    public BigDecimal getFpost_salary() {
        return fpost_salary;
    }

    public void setFpost_salary(BigDecimal fpost_salary) {
        this.fpost_salary = fpost_salary;
    }

    public BigDecimal getFovertime_salary() {
        return fovertime_salary;
    }

    public void setFovertime_salary(BigDecimal fovertime_salary) {
        this.fovertime_salary = fovertime_salary;
    }

    public BigDecimal getFhouse_salary() {
        return fhouse_salary;
    }

    public void setFhouse_salary(BigDecimal fhouse_salary) {
        this.fhouse_salary = fhouse_salary;
    }

    public BigDecimal getFperformance_salary() {
        return fperformance_salary;
    }

    public void setFperformance_salary(BigDecimal fperformance_salary) {
        this.fperformance_salary = fperformance_salary;
    }

    public String getFperformance_grate() {
        return fperformance_grate;
    }

    public void setFperformance_grate(String fperformance_grate) {
        this.fperformance_grate = fperformance_grate;
    }

    public BigDecimal getFperformance_actual() {
        return fperformance_actual;
    }

    public void setFperformance_actual(BigDecimal fperformance_actual) {
        this.fperformance_actual = fperformance_actual;
    }

    public BigDecimal getFphone_allowance() {
        return fphone_allowance;
    }

    public void setFphone_allowance(BigDecimal fphone_allowance) {
        this.fphone_allowance = fphone_allowance;
    }

    public BigDecimal getFother() {
        return fother;
    }

    public void setFother(BigDecimal fother) {
        this.fother = fother;
    }

    public BigDecimal getFshould_get_salary() {
        return fshould_get_salary;
    }

    public void setFshould_get_salary(BigDecimal fshould_get_salary) {
        this.fshould_get_salary = fshould_get_salary;
    }

    public BigDecimal getFhouse_rent() {
        return fhouse_rent;
    }

    public void setFhouse_rent(BigDecimal fhouse_rent) {
        this.fhouse_rent = fhouse_rent;
    }

    public BigDecimal getFsocial_person_tax() {
        return fsocial_person_tax;
    }

    public void setFsocial_person_tax(BigDecimal fsocial_person_tax) {
        this.fsocial_person_tax = fsocial_person_tax;
    }

    public BigDecimal getFshould_tax_money() {
        return fshould_tax_money;
    }

    public void setFshould_tax_money(BigDecimal fshould_tax_money) {
        this.fshould_tax_money = fshould_tax_money;
    }

    public BigDecimal getFperson_tax() {
        return fperson_tax;
    }

    public void setFperson_tax(BigDecimal fperson_tax) {
        this.fperson_tax = fperson_tax;
    }

    public BigDecimal getFsick_later() {
        return fsick_later;
    }

    public void setFsick_later(BigDecimal fsick_later) {
        this.fsick_later = fsick_later;
    }

    public BigDecimal getFhousing_fund() {
        return fhousing_fund;
    }

    public void setFhousing_fund(BigDecimal fhousing_fund) {
        this.fhousing_fund = fhousing_fund;
    }

    public BigDecimal getFother_deduction() {
        return fother_deduction;
    }

    public void setFother_deduction(BigDecimal fother_deduction) {
        this.fother_deduction = fother_deduction;
    }

    public BigDecimal getFshould_deduction_salary() {
        return fshould_deduction_salary;
    }

    public void setFshould_deduction_salary(BigDecimal fshould_deduction_salary) {
        this.fshould_deduction_salary = fshould_deduction_salary;
    }

    public BigDecimal getFactual_salary() {
        return factual_salary;
    }

    public void setFactual_salary(BigDecimal factual_salary) {
        this.factual_salary = factual_salary;
    }

    public String getFmark() {
        return fmark;
    }

    public void setFmark(String fmark) {
        this.fmark = fmark;
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
