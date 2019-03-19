package com.tenpay.fingate.http;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpStatus;

/**
 * http请求的响应处理
 * @author robenzhang
 */
public class HttpClient4ResponseResult {
	private int statusCode=0;
	private byte[] responseBody=null;
	@SuppressWarnings("rawtypes")
	private Map responseHeaders=null;
	 
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		if(statusCode != HttpStatus.SC_OK ){
			Exception ex=new Exception(this.getClass().getName()+"::提示：HTTP访问返回非正确码："+statusCode+"！需各自处理该异常");
			ex.printStackTrace();
		}
	}
	public byte[] getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(byte[] responseBody) {
		this.responseBody = responseBody;
	} 
	
	@SuppressWarnings("rawtypes")
	public Set getResponseHeaderNames(){
		return this.responseHeaders.keySet();
	}
	
	public String getResponseHeader(String headerName){
		return (String)this.responseHeaders.get(headerName);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setResponseHeaders(Header[] rspsHeaders) {
		this.responseHeaders=new HashMap();
		for(int i=0;i<rspsHeaders.length;i++ ){
			this.responseHeaders.put( rspsHeaders[i].getName(),rspsHeaders[i].getValue());
		}
	}  
	
	public String toString(){
		String content=super.toString()+"::该结果对象的具体字符值："+"\n";
		content +="http response status_code="+this.statusCode+"\n";  
		content +="http response headers="+this.responseHeaders+"\n";
		try {
			content +="http response body ="+new String(this.responseBody,"UTF-8")+"\n";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return content;
	}
}
