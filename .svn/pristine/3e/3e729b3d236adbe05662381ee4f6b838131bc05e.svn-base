package com.tenpay.wxwork.salary.info;

public class ApprovalStatus {
    /**
     * 1 审批中
     */
    public static final int Approvaling = 1;

    /**
     * 2 已通过
     */
    public static final int Approvaled = 2;
    
    /**
     * 3 已驳回
     */
    public static final int Rejected = 3;
    /**
     * 4 已取消
     */
    public static final int Canceled = 4;

    /**
     * 6 通过后撤销
     */
    public static final int ApprovaledCanceled = 6;
    
    /**
     * 10 已提交支付（审批单状态终态）
     */
    public static final int SubmitPayment = 10;
    
    public static boolean contains(int status){    
    	if(status<Approvaling  || status>SubmitPayment)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    } 
}

