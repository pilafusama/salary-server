package com.tenpay.wxwork.salary.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ErrorModuleConvert {
	
	private static int moduleCode;
	public static final int minCode = 0;
	public static final int maxCode = 9999;
	private static final Logger logger = LoggerFactory.getLogger(ErrorModuleConvert.class);
	
	@Value("${moduleCode}")
    public void setModuleCode(int moduleCode) {  
		ErrorModuleConvert.moduleCode = moduleCode;  
    }  
	public static int toModuleError(int error)
	{
		logger.debug("moduleCode:"+Integer.toString(moduleCode));
	    if ((error >= minCode && error <= maxCode))
	    {
	    	logger.debug("ERROR:"+error+"moduleCode:"+Integer.toString(ErrorModuleConvert.moduleCode * 10000 + error));
	        return ErrorModuleConvert.moduleCode * 10000 + error;
	    }
	
	    return error;    
	}
}
