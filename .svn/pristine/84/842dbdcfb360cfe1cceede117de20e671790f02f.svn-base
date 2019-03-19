package com.tenpay.wxwork.salary.common.utils;

import com.tenpay.wxwork.salary.common.BizException;






public class ExceptionUtils {

    //递归查找指定异常
    public static <T> T findFixException(Throwable e, Class<T> t) {
        if (t.isInstance(e)) {
            return (T) e;
        }
        if (e.getCause() != null) {
            T cause = findFixException(e.getCause(), t);
            if (cause != null) {
                return cause;
            }
        }
        return null;
    }
    
    public static void checkOK(String reply)throws BizException{
        if (!Constant.OK.equals(reply)) {
            throw new BizException(-1, "err response:"+reply);
        }   	
    }
    
    public static void checkResult(int retCode, String resinfo)throws BizException{
        if (!Constant.SUCCESS_CODE.equals(""+ retCode)) {        	
            throw new BizException(retCode, resinfo);
        }   	
    }   
}
