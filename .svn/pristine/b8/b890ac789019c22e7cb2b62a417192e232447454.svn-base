package com.tenpay.fingate;

import java.util.HashMap;
import java.util.Map;

import com.tenpay.wxwork.salary.util.MapConvert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.net.URL;

import com.tenpay.fingate.http.HttpClient4;
import com.tenpay.fingate.http.HttpClient4ResponseResult;
import com.tenpay.fingate.l5.L5Info;
import com.tenpay.fingate.l5.L5Util;
// import com.tenpay.fingate.util.JsonUtil;
// import com.tenpay.fingate.util.ResolveProperties;
import com.tenpay.fingate.util.StrUtil;

/**
 * C2B资金流转http
 * 主要用于c2b的下单、支付、查单及退款
 * 
 * @author robenzhang
 * @date 2017-03-24
 */
public class FundTransferHttp {
	private static final Log log = LogFactory.getLog(FundTransferHttp.class);

	// 调用C2B资金流转接口
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> fundTransfer(String url, String paramStr,
                                                   int modId, int cmdId, float timeout) {
		try {
            URL urlObj = new URL(url);
            String l5Uri = urlObj.getPath() + "?";

			log.info("FundTransferHttp.fundTransfer(), request: " + url + paramStr);
            log.debug("l5 uri: " + l5Uri);

            boolean useDirectHttp = true;
            HttpClient4ResponseResult result = null;
            if (0 != modId) {
                log.debug("use l5 http");
                // 1、调用L5获取参数
                L5Info l5Info = new L5Info();
                l5Info.setCgi(l5Uri);
                l5Info.setCmdId(cmdId);
                l5Info.setModId(modId);
                l5Info.setTimeout(timeout);
                l5Info.setParam(paramStr);

                Map resultMap = L5Util.doL5(l5Info);
                // L5调用如果成功，直接返回对应结果
                if (resultMap.containsKey("flag") && "true".equals(resultMap.get("flag"))) {
                    result = (HttpClient4ResponseResult) resultMap.get("result");
                    useDirectHttp = false;
                }
            }

            if (useDirectHttp) {
                log.debug("use direct http");
				result = HttpClient4.doGet(url + paramStr);
            }

			byte[] bodyByte = result.getResponseBody();
			String returnInfo = StrUtil.byteToStrUTF8(bodyByte, "UTF-8");
			log.info("FundTransferHttp.fundTransfer() returnInfo: " + returnInfo);

			// 2、返回对应的信息
			return MapConvert.jsonToStrMap(returnInfo);
		} catch (Exception e) {
			log.info("FundTransferHttp.fundTransfer(): " + e.toString());
			return new HashMap<String, String>();
		}
	}
}
