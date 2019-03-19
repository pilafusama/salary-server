package com.tenpay.wxwork.salary.info;

public class CorpAppRelationStatus {
    /**
     * 业务开通
     */
    public static final String Open = "OPEN";
    
    /**
     * 业务关闭
     */
    public static final String Close = "CLOSE";
    
    /**
     * 业务暂停
     */
    public static final String Stop = "STOP";
    
    public static boolean contains(String status)
    {
    	switch(status)
    	{
	    	case Open:
	    		return true;
	    	case Close:
	    		return true;
	    	case Stop:
	    		return true;
	    	default:
	    		return false;
    	}
    }
}

