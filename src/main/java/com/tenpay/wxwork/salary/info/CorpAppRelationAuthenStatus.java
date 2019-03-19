package com.tenpay.wxwork.salary.info;

public class CorpAppRelationAuthenStatus {
    /**
     * 0 未认证
     */
    public static final int Initial = 0;

    /**
     * 1已绑定认证
     */
    public static final int Normal = 1;
    
    /**
     * 2 已关闭
     */
    public static final int Close = 2;
    
    
    public static boolean contains(int status){    
    	if(status<Initial  || status>Close)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    } 
}

