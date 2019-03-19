package com.tenpay.wxwork.salary.util;

import com.google.gson.reflect.TypeToken;

/**
 * 为解决{@link TypeToken}的构造函数是protected的，对其他了一个包装
 * 
 * @author runquanyao
 *
 * @param <T>
 */
public class TypeTokenWrapper<T> extends TypeToken<T> {
	public TypeTokenWrapper() {
		super();
	}
}
