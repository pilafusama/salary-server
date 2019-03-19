package com.tenpay.wxwork.salary.util.download;

import java.lang.annotation.*;

/**
 * Copy Right Information : Forms Syntron <br>
 * author : treesen <br>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelDownloadAnnotation
{
	public String enName() default "";
	
	public String cnName() default "";
	
	public String format() default "@";
	
	public int order() default 1;

	public int mark() default -1;
}
