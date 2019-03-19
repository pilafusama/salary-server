package com.tenpay.wxwork.salary.info;

public class RequestType {

    /**
     *开发流程样例
     *
     */
    public static final int ExampleRequestType = 9999;

    /**
     * E账户平台绑定及账户信息查询
     *
     */
    public static final int QueryEBindQuery = 108046;
    /**
     * 开立薪资账户
     */
    public static final int OpenSalaryAccountRequestType = 108047;

    /**
     * 客户识别
     *
     */
    public static final int VerifyUser = 108048;

    /**
     * 上传身份证
     *
     */
    public static final int UploadIdCardPhotos = 108049;

    /**
     * 发送短信
     *
     */
    public static final int SendSmsCode = 108050;
    /**
     * E账户绑定关系查询
     *
     */
    public static final int QueryExistCategory2Accounts = 108051;
    /**
     * 绑定账户
     *
     */
    public static final int ReuseSalaryAccount = 108052;
    /**
     *余额查询
     *
     */
    public static final int BalanceRequestType = 108053;
    /**
     *卡内活期子账户明细查询
     *
     */
    public static final int CurrentDetailRequestType = 108054;
    /**
     * 工资条提现行内活期转帐
     *
     */
    public static final int FetchAmountQuery = 108055;

    /**
     * 3.1企业付款绑定申请接口
     */
    public static final int CorpBindAuthen = 7721;

    /**
     * 企业付款绑定确认接口
     */
    public static final int CorpBindConfirm = 7722;
    
    /**
     * 提交待付款的审批单接口（微信推送审批单模式)
     */
    public static final int FlowSubmit = 7723;
 
    /**
     * 查询审批单付款结果接口
     */
    public static final int FlowPaymentQuery = 7724;

}

