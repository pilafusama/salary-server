package com.tenpay.fingate.l5;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tenpay.fingate.http.HttpClient4;
import com.tenpay.fingate.http.HttpClient4ResponseResult;

import com.qq.l5.L5Config;
import com.qq.l5.L5EndPoint;
import com.qq.l5.L5Exception;
import com.qq.l5.L5sys;
// import com.tenpay.fingate.util.ResolveProperties;

/**
 * 接入L5进行内部系统调用
 * 
 * @author robenzhang
 * @date 2015-10-23
 */
public class L5Util {
	private static Log log = LogFactory.getLog(L5Util.class);

	// private static final String FILE_PATH = ResolveProperties.getValue("so.file");
	// static {
	// 	// 判断当前jvm里面是否加载so文件
	// 	System.load(FILE_PATH + "l5sys.so"); 
	// 	log.debug("加载L5的so文件");
	// }

	/**
	 * 获取当前登录态的信息
	 * 
	 * @param qq
	 * @param userIp
	 * @param skey
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map doL5(L5Info l5Info) {
        L5Config config = new L5Config(l5Info.getModId(), l5Info.getCmdId());
		L5sys.QosRequest qosRequest = new L5sys.QosRequest(config);

		L5sys l5sys = new L5sys();
		Map resultMap = new HashMap();
        long tm_start = System.currentTimeMillis();
        int rc = 0;// succ 0,fail <0
        try {
            L5EndPoint endPoint;
            float timeout = l5Info.getTimeout();
            endPoint = l5sys.fetchRoute(qosRequest, timeout);
            String ip = endPoint.getHostIp();
            int port = endPoint.getHostPort();
            
			log.debug("L5获取的ip:" + ip + ",port:" + port);
			String url = "http://" + ip + ":" + port + l5Info.getCgi() + l5Info.getParam();
			log.debug("通过L5调用的地址：" + url);
			// 进行远程调用
			HttpClient4ResponseResult result = null;
			try {
				result = HttpClient4.doGet(url);
			} catch (Exception e) {
				log.debug("L5获取制定的IP存在问题，直接调用外网支付接口");
				resultMap.put("flag", "false");
				return resultMap;
			}
			rc = 0;// succ 0,fail <0
            resultMap.put("result", result);
			resultMap.put("flag", "true");
        } catch (L5Exception e) {
            log.error(String.format("failed to get ip port from l5, modid: %s, cmdid: %s",
                                    l5Info.getModId(), l5Info.getCmdId()));
            rc = -1;
			resultMap.put("flag", "false");
        }

        long tm_end = System.currentTimeMillis();
        long tm_used = (tm_end - tm_start);
        if (0 == rc) {
            l5sys.updateRouteResult(qosRequest, L5sys.Status.SUCCESS, tm_used);
        } else {
            l5sys.updateRouteResult(qosRequest, L5sys.Status.FAILED, tm_used);
        }

        return resultMap;
	}
}
