package com.tenpay.wxwork.salary.common;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrontEndPackageProcessor {
	private static final Logger logger = LoggerFactory.getLogger(FrontEndPackageProcessor.class);

	
	public static void verifySign(String logSsid,int timeStamp,String nonce,String sskey,String sign)
	{
		String calculatedSign = DigestUtils.sha256Hex(logSsid + Integer.toString(timeStamp) + nonce + sskey);
		logger.debug("calculatedSign:"+calculatedSign);
		if(!calculatedSign.equalsIgnoreCase(sign))
		{
			throw new BizException(BizError.VERIFY_SIGN_ERROR.errorCode(),"验证签名失败。"); 
		}
	}
}
