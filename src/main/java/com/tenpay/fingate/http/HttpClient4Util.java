package com.tenpay.fingate.http;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * http请求的响应处理
 * 
 * @author robenzhang
 */
public class HttpClient4Util {
	private static Log logger = LogFactory.getLog(HttpClient4Util.class);

	// 获取连接
	public CloseableHttpClient getConnection() {
		// HttpHost proxy = new HttpHost("proxy.tencent.com", 8080, "http");
		// requestConfig = RequestConfig.custom().setProxy(proxy).build();
		// httpBulder = HttpClients.custom();
		// httpBulder.setDefaultRequestConfig(requestConfig);
		// httpBulder.setRetryHandler(new DefaultHttpRequestRetryHandler(3,
		// false));// 默认重新连接3次
		// httpBulder.setConnectionTimeToLive(60000, TimeUnit.MILLISECONDS);//
		// 设置60s的持久连接时间
		// return httpBulder.build();
		HttpClientBuilder hBuilder = HttpClients.custom();
		hBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, false));// 默认重新连接3次
		hBuilder.setConnectionTimeToLive(60000, TimeUnit.MILLISECONDS);// 设置60s的持久连接时间
		return hBuilder.build();
	}

	// get调用
	public HttpClient4ResponseResult doHttpGet(String url) throws Exception {
		return this.doHttpRequest(new HttpGet(url));
	}

	protected HttpClient4ResponseResult doHttpRequest(HttpRequestBase httpRequestBase) throws Exception {
		CloseableHttpClient httpClient = null;
		HttpEntity responseEntity = null;
		try {
			httpClient = getConnection();
			logger.info("httpRequestBase:" + httpRequestBase);
			HttpResponse response = httpClient.execute(httpRequestBase);

			Header[] headers = response.getAllHeaders();
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			responseEntity = response.getEntity();
			byte[] bytes = EntityUtils.toByteArray(responseEntity);
			HttpClient4ResponseResult result = new HttpClient4ResponseResult();
			result.setStatusCode(statusCode);
			result.setResponseBody(bytes);
			result.setResponseHeaders(headers);
			return result;
		} finally {
			this.freeHttpClientResource(httpRequestBase, responseEntity);
			httpClient.close();
		}
	}

	private void freeHttpClientResource(HttpRequestBase httpRequestBase, HttpEntity httpEntity) throws Exception {
		try {
			if (httpEntity != null) {
				InputStream input = httpEntity.getContent();
				if (input != null) {
					input.close();
				}
			}
		} catch (Exception e) {
			logger.error("HttpClient4Util.freeHttpClientResource()释放连接异常：" + e);
		} finally {
			httpRequestBase.releaseConnection();
		}
	}
}
