package com.tenpay.wxwork.salary.info;

public class AuthenType {
    /**
     * 发消息权限
     */
    public static final int sendMsg = 0;

    /**
     * 管理员权限
     */
    public static final int Admin = 1;
    
    public static boolean isAdmin(int authenType)
    {
    	if(authenType == Admin)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
