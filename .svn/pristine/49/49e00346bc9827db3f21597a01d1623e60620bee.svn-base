package com.tenpay.fingate.util;

import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * map的工具类
 * 
 * @author robenzhang
 * @date 2015-9-22
 */
public class MapUtil {
	private static Log log = LogFactory.getLog(MapUtil.class);

	// 获取一个key值为ASCII码从小到大排序的treeMap
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> getTreeMap() {
		return new TreeMap<String, String>(new Comparator() {
			public int compare(Object o1, Object o2) {
				if (o1 == null || o2 == null) {
					return 0;
				}
				return String.valueOf(o1).compareTo(String.valueOf(o2));
			}
		});
	}

	/**
	 * 将url参数转换成map
	 * 
	 * @param param
	 *            aa=11&bb=22&cc=33
	 * @return
	 */
	public static Map<String, String> getUrlParams(String param) {
		Map<String, String> map = new TreeMap<String, String>();
		if (StrUtil.isEmpty(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	// 把Map转化成url格式的String
	public static String mapToUrlString(Map<String, String> paramMap, Map<String, String> removeMap) {
		if (paramMap == null || paramMap.size() == 0) {
			return "";
		}
		StringBuilder url = new StringBuilder();
		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			// 去掉不需要组装的信息
			if (removeMap != null && removeMap.containsKey(entry.getKey())) {
				continue;
			}
			url.append(String.format("%1$s=%2$s&", entry.getKey(), entry.getValue()));
		}
		return url.substring(0, url.length() - 1);
	}
	
	// 把Map转化成url格式的String,处理：去掉空值
	public static String mapToUrlString(Map<String, String> paramMap) {
		if (paramMap == null || paramMap.size() == 0) {
			return "";
		}
		String url = "";
		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			// 去掉值为空的信息
			if (StrUtil.isEmpty(entry.getValue())) {
				continue;
			}
			try {
				url += String.format("%1$s=%2$s&", entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8"));
				//url += String.format("%1$s=%2$s&", entry.getKey(), entry.getValue());
			} catch (Exception e) {
				log.info("转化mapToUrlString失败：" + e.toString());
			}
		}
		return url.substring(0, url.length() - 1);
	}
}
