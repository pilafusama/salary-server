package com.tenpay.wxwork.salary.util;

import com.tenpay.wxwork.salary.common.utils.Constant;

import org.apache.log4j.MDC;

public class MsgnoGenerator{
	
	public static String genMsgno(){
		long currentTime = System.currentTimeMillis();
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(Long.toString(currentTime));
        stringBuffer.append("_");
		Thread thread = Thread.currentThread();
		stringBuffer.append(Long.toString(thread.getId()));
		
		return stringBuffer.toString();
	}

    public static String getMsgnoFromMDC() {
        String msgno = (String)MDC.get(Constant.REQUEST_ATTR_MSG_NO);
        if (null == msgno || msgno.isEmpty()) {
            msgno = genMsgno();
        }

        return msgno;
    }
}
