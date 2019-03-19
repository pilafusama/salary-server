package com.tenpay.fingate.util;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 字符串的工具类
 * @author robenzhang
 */
public class StrUtil {
	
	private static final Log log = LogFactory.getLog(StrUtil.class);

	/**
	 * 将前台传入的对象转换为String[]类型
	 * 
	 * @param value
	 * @return
	 */
	public static String[] parseArray(Object value) {
		String[] ret = null;
		if (value == null) {
			ret = new String[0];
		} else if (value.getClass().isArray() && value instanceof String[]) {
			ret = (String[]) value;
		} else if (value instanceof String) {
			ret = new String[] { (String) value };
		} else {
			ret = new String[0];
		}
		return ret;
	}

	/**
	 * 将前台传入的对象转换为String类型
	 * 
	 * @param value
	 * @return
	 */
	public static String parseString(Object value) {
		return parseString(value, "");
	}

	public static String parseString(Object value, String defValue) {
		String ret = null;
		if (value == null) {
			log.debug("null");
		} else if (value.getClass().isArray() && value instanceof String[]) {
			String[] array = (String[]) value;
			if (array.length > 0)
				ret = array[0];
		} else if (value instanceof String) {
			ret = (String) value;
		}
		return (ret != null && ret.length() > 0) ? ret : defValue;
	}

	/**
	 * 将前台传入的对象转换为int类型
	 * 
	 * @param value
	 * @return
	 */
	public static int parseInt(Object value) {
		return parseInt(value, 0);
	}

	/**
	 * 将前台传入的对象转换为int类型
	 * 
	 * @param value
	 * @param defaultValue
	 *            转换失败时返回的缺省值
	 * @return
	 */
	public static int parseInt(Object value, int defaultValue) {
		int ret = defaultValue;
		if (value != null && value instanceof String) {
			try {
				ret = Integer.parseInt((String) value);
			} catch (NumberFormatException e) {
				log.debug(e.getLocalizedMessage());

			}
		} else if (value instanceof Number) {
			ret = ((Number) value).intValue();
		}
		return ret;
	}

	/**
	 * 将前台传入的对象转换为long类型
	 * 
	 * @param value
	 * @return
	 */
	public static long parseLong(Object value) {
		return parseLong(value, 0);
	}

	/**
	 * 将前台传入的对象转换为long类型
	 * 
	 * @param value
	 * @param defaultValue
	 *            转换失败时返回的缺省值
	 * @return
	 */
	public static long parseLong(Object value, long defaultValue) {
		long ret = defaultValue;
		if (value != null && value instanceof String) {
			try {
				ret = Long.parseLong((String) value);
			} catch (NumberFormatException e) {
				log.debug(e.getLocalizedMessage());

			}
		} else if (value instanceof Number) {
			ret = ((Number) value).longValue();
		}
		return ret;
	}

	/**
	 * 将前台传入的对象转换为float类型
	 * 
	 * @param value
	 * @return
	 */
	public static float parseFloat(Object value) {
		return parseFloat(value, 0.0F);
	}

	/**
	 * 将前台传入的对象转换为float类型
	 * 
	 * @param value
	 * @param defaultValue
	 *            转换失败时返回的缺省值
	 * @return
	 */
	public static float parseFloat(Object value, float defaultValue) {
		float ret = defaultValue;
		if (value != null && value instanceof String) {
			try {
				ret = Float.parseFloat((String) value);
			} catch (NumberFormatException e) {
				log.debug(e.getLocalizedMessage());

			}
		} else if (value instanceof Number) {
			ret = ((Number) value).floatValue();
		}
		return ret;
	}

	/**
	 * 将前台传入的对象转换为double类型
	 * 
	 * @param value
	 * @return
	 */
	public static double parseDouble(Object value) {
		return parseDouble(value, 0.0D);
	}

	/**
	 * 将前台传入的对象转换为double类型
	 * 
	 * @param value
	 * @param defaultValue
	 *            转换失败时返回的缺省值
	 * @return
	 */
	public static double parseDouble(Object value, double defaultValue) {
		double ret = defaultValue;
		if (value != null && value instanceof String) {
			try {
				ret = Double.parseDouble((String) value);
			} catch (NumberFormatException e) {
				log.debug(e.getLocalizedMessage());

			}
		} else if (value instanceof Number) {
			ret = ((Number) value).doubleValue();
		}
		return ret;
	}

	/**
	 * 将前台传入的对象转换为boolean类型
	 * 
	 * @param value
	 * @return
	 */
	public static boolean parseBoolean(Object value) {
		return parseBoolean(value, false);
	}

	/**
	 * 将前台传入的对象转换为boolean类型
	 * 
	 * @param value
	 * @param defaultValue
	 *            转换失败时返回的缺省值
	 * @return
	 */
	public static boolean parseBoolean(Object value, boolean defaultValue) {
		boolean ret = defaultValue;
		if (value != null && value instanceof String) {
			if (((String) value).toLowerCase().equals("true")
					|| value.equals("1")) {
				ret = true;
			} else if (((String) value).toLowerCase().equals("false")
					|| value.equals("0")) {
				ret = false;
			}
		} else if (value instanceof Boolean) {
			ret = ((Boolean) value).booleanValue();
		}
		return ret;
	}

	/**
	 * 数组转化为,分隔的字符串
	 * 
	 * @param arrays
	 * @return
	 */
	public static String arrayToString(Object[] arrays) {
		return arrayToString(arrays, ",");
	}

	/**
	 * 数组转化为symbol分隔字符串
	 */
	public static String arrayToString(Object[] arrays, String symbol) {
		return arrayToString(arrays, symbol, "");
	}

	/**
	 * 数组转化为symbol分隔且加引号的字符串
	 * 
	 * @param arrays
	 * @param symbol
	 * @param quote
	 * @return
	 */
	public static String arrayToString(Object[] arrays, String symbol,
			String quote) {
		if (arrays == null)
			return null;

		StringBuffer str = new StringBuffer();
		for (int i = 0, j = arrays.length; i < j; i++) {
			if (i > 0) {
				str.append(symbol);
			}
			if (isNotEmpty(quote)) {
				str.append(quote);
			}
			str.append(arrays[i]);
			if (isNotEmpty(quote)) {
				str.append(quote);
			}
		}
		return str.toString();
	}

	/**
	 * 将null转为""
	 * 
	 * @param str
	 * @return
	 */
	public static String noNull(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 判断是否为null或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 判断不为null且不为空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 执行trim操作
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (str == null)
			return null;
		else
			return str.trim();
	}

	/**
	 * 取字符串的一部分 判断不为null且不为空字符串
	 * 
	 * @param str
	 * @param maxLen
	 *            取的最大字符数，为负数则从右边取
	 * @return
	 */
	public static String maxLenString(String str, int maxLen) {
		log.debug("str: " + str + " maxLen: " + maxLen);
		if (str == null)
			return str;
		int x, y;
		if (maxLen >= 0) {
			x = 0;
			y = Math.min(str.length(), maxLen);
		} else {
			x = str.length() - Math.min(str.length(), Math.abs(maxLen));
			y = str.length();
		}
		String ret = str.substring(x, y);
		log.debug("ret: " + ret);
		return ret;
	}

	/**
	 * 替换src中的第1个str1为str2
	 * 
	 * @param src
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String replaceFirst(String src, String str1, String str2) {
		if (src == null || str1 == null)
			return src;
		StringBuffer ret = new StringBuffer();
		int pos = src.indexOf(str1);
		// log.debug("pos: \n" + pos);
		if (pos == 0) {
			ret.append(str2).append(src.substring(str1.length()));
		} else if (pos > 0) {
			ret.append(src.substring(0, pos)).append(str2)
					.append(src.substring(pos + str1.length()));
		} else {
			ret.append(src);
		}
		// log.debug("ret: \n" + ret);
		return ret.toString();
	}

	/**
	 * 替换src中所有的str1为str2
	 * 
	 * @param src
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String replaceAll(String src, String str1, String str2) {
		String ret = src;
		do {
			src = ret;
			ret = replaceFirst(src, str1, str2);
		} while (ret != null && !ret.equals(src));
		// log.debug("ret: \n" + ret);
		return ret;
	}

	/**
	 * 将strSource中所有的strFrom都改成strTo
	 * 
	 * @param src
	 *            源字符串
	 * @param str1
	 *            被替换的子串
	 * @param str2
	 *            要替换为的子串
	 * @return 修改后的字符串
	 */
	public static String replace(String src, String str1, String str2) {
		if (src == null || str1 == null)
			return src;
		StringBuffer strDest = new StringBuffer();
		int intFromLen = str1.length();
		int intPos;

		while (src != null && (intPos = src.indexOf(str1)) != -1) {
			strDest.append(src.substring(0, intPos));
			strDest.append(str2);
			src = src.substring(intPos + intFromLen);
		}
		strDest.append(src);

		return strDest.toString();
	}

	/**
	 * 
	 * 将 strSrc 中所有的 src 都改成 tgt
	 * 
	 * @param strSrc
	 *            源字符串
	 * @param delis
	 *            间隔字符
	 * @param src
	 *            被替换的子串
	 * @param tgt
	 *            要替换为的子串
	 * @return 修改后的字符串
	 */
	public static String replace(String strSrc, String[] delis, String src,
			String tgt) {
		// System.out.print("\nstrSrc: " + strSrc);
		if (null == strSrc || "".equals(strSrc) || null == src || "".equals(src)) {
			// System.out.print(" >> strDest: " + strSrc);
			return strSrc;
		}

		StringBuffer strDest = new StringBuffer();
		int srcLen = src.length();
		while (strSrc != null && strSrc.length() > 0) {
			int srcPos = strSrc.indexOf(src);
			if (srcPos == -1) {
				break;
			}
			String obj = src;
			if (delis == null || delis.length == 0) {
				obj = tgt;
			} else {
				boolean deliLeft = false, deliRight = false;
				// judge left match
				if (srcPos == 0) {
					deliLeft = true;
				} else {
					for (int i = 0; i < delis.length; i++) {
						int deliPos1 = strSrc.indexOf(delis[i] + src)
								+ delis[i].length();
						if (srcPos == deliPos1) {
							deliLeft = true;
							break;
						}
					}
				}
				// judge right match
				if (deliLeft == false) {
					deliRight = false;
				} else if (srcPos + src.length() == strSrc.length()) {
					deliRight = true;
				} else {
					for (int i = 0; i < delis.length; i++) {
						int deliPos2 = strSrc.indexOf(src + delis[i]);
						if (srcPos == deliPos2) {
							deliRight = true;
							break;
						}
					}
				}
				if (deliRight == true) {
					obj = tgt;
				}
			}
			strDest.append(strSrc.substring(0, srcPos));
			strDest.append(obj);
			strSrc = strSrc.substring(srcPos + srcLen);
		}
		strDest.append(strSrc);

		// System.out.print(" >> strDest: " + strDest);
		return strDest.toString();
	}

	/**
	 * 将字符串 src 按分隔符 regexs 分解为字符串数组
	 * 
	 * @param src
	 * @param regexs
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List split(String src, String[] regexs) {
		List tokens = new ArrayList();
		tokens.add(src);
		for (int i = 0; regexs != null && i < regexs.length; i++) {
			List arr = new ArrayList();
			for (int j = 0; j < tokens.size(); j++) {
				arr.addAll(split((String) tokens.get(j), regexs[i]));
			}
			tokens = arr;
		}

		return tokens;
	}

	/**
	 * 将字符串 src 按分隔符 regex 分解为字符串数组
	 * 
	 * @param src
	 * @param regex
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List split(String src, String regex) {
		List tokens = new ArrayList();
		if (src == null) {
			return tokens;
		}
		if ("".equals(src) || isEmpty(regex)) {
			tokens.add(src);
			return tokens;
		}

		int intFromLen = regex.length();
		int intPos;

		while (src != null && (intPos = src.indexOf(regex)) != -1) {
			tokens.add(src.substring(0, intPos));
			src = src.substring(intPos + intFromLen);
		}
		
			tokens.add(src);

		return tokens;
	}

	/**
	 * 返回对象的长度, 为空时返回-1，为字符串时返回字符串长度，为数组时返回数组长度
	 * 
	 * @param value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static int length(Object value) {
		if (value == null)
			return -1;
		int ret = 1;
		if (value instanceof String) {
			ret = ((String) value).length();
		} else if (value instanceof StringBuffer) {
			ret = ((StringBuffer) value).length();
		} else if (value instanceof java.util.Collection) {
			ret = ((java.util.Collection) value).size();
		} else if (value instanceof Map) {
			ret = ((Map) value).size();
		} else if (value.getClass().isArray()) {
			if (value instanceof Object[]) {
				ret = ((Object[]) value).length;
			} else {
				String type = value.getClass().getComponentType().getName();
				if (type.equals("byte")) {
					ret = ((byte[]) value).length;
				} else if (type.equals("char")) {
					ret = ((char[]) value).length;
				} else if (type.equals("double")) {
					ret = ((double[]) value).length;
				} else if (type.equals("float")) {
					ret = ((float[]) value).length;
				} else if (type.equals("int")) {
					ret = ((int[]) value).length;
				} else if (type.equals("long")) {
					ret = ((long[]) value).length;
				} else if (type.equals("short")) {
					ret = ((short[]) value).length;
				} else if (type.equals("boolean")) {
					ret = ((boolean[]) value).length;
				}
			}
		}
		return ret;
	}

	/**
	 * 返回字符串的长度, 每个英文字符长度为1，中文字符长度为2，为空时返回-1
	 * 
	 * @param value
	 * @return
	 */
	public static int byteLen(String value) throws UnsupportedEncodingException {
		if (value == null)
			return -1;
		int ret = 1;
		ret = value.getBytes("UTF-8").length;
		return ret;
	}

	/**
	 * 取字符串的一部分 判断不为null且不为空字符串
	 * 
	 * @param str
	 * @param maxLen
	 *            取的最大字节数，为负数则从右边取
	 * @return
	 */
	public static String maxLenByte(String str, int maxLen) throws UnsupportedEncodingException {
		log.debug("str: " + str + " maxLen: " + maxLen);
		if (str == null)
			return str;
		int x, y;
		byte[] src = str.getBytes("UTF-8");
		if (maxLen >= 0) {
			x = 0;
			y = Math.min(src.length, maxLen);
		} else {
			x = src.length - Math.min(src.length, Math.abs(maxLen));
			y = src.length;
		}
		byte[] dest = new byte[y - x];
		System.arraycopy(src, x, dest, 0, y - x);
		String ret = new String(dest,"UTF-8");
		log.debug("ret: " + ret);
		dest = null;
		return ret;
	}

	/**
	 * 判断对象是否在数组中
	 * 
	 * @param value
	 * @return
	 */
	public static boolean exists(Object value, Object[] arrays) {
		if (value == null || arrays == null)
			return false;

		int len = arrays.length;
		if (len == 0)
			return false;

		boolean ret = false;
		for (int i = 0; i < len; i++) {
			if (value.equals(arrays[i])) {
				ret = true;
				break;
			}
		}

		return ret;
	}

	/**
	 * 判断数值是否在数组中
	 * 
	 * @param value
	 * @return
	 */
	public static boolean exists(int value, int[] arrays) {
		if (arrays == null)
			return false;

		int len = arrays.length;
		if (len == 0)
			return false;

		boolean ret = false;
		for (int i = 0; i < len; i++) {
			if (value == arrays[i]) {
				ret = true;
				break;
			}
		}

		return ret;
	}

	public static String handleString(String str) {
		String paramete = str;
		if ("undefined".equals(paramete) || null == paramete
				|| 0 == paramete.toString().trim().length()) {
			paramete = "";
		}
		return paramete;
	}

	/**
	 * 判断是否为纯数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		if (isEmpty(str)) {
			return false;
		}
		Pattern p = Pattern.compile("(-?[0-9]+|[0-9]*)?");
		Matcher m = p.matcher(str);
		return m.matches();

	}
	
	public static boolean isTrue(String str) {
		boolean result = false;
		if (isEmpty(str)) {
			return false;
		}
		if ("true".equalsIgnoreCase(str.trim())
				|| "on".equalsIgnoreCase(str.trim())
				|| "1".equalsIgnoreCase(str.trim())) {
			result = true;
		}
		return result;
	}

	/**
	 * <p>
	 * 判断指定的输入字符串是否是EAMIL地址
	 * </p>
	 * 
	 * @param str
	 * @return <code>true</code>如果输入串是EAMIL地址
	 */
	public static boolean isEmailStr(String str) {
		if (str == null || str.length() < 6)
			return false;
		try {
			String check = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(str);
			boolean isMatched = matcher.matches();
			if (isMatched) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 */
	public static boolean doVerify(String code) {
		// 长度只有15和18两种情况
		if ((code.trim().length() != 15) && (code.trim().length() != 18)) {
			return false;
		}
		try {
			String check = "\\d{15,17}([\\dxX]{1})?";
			// 身份证号码必须为数字(18位的新身份证最后一位可以是x)
			Pattern pt = Pattern.compile(check);
			Matcher mt = pt.matcher(code);
			boolean isMatched = mt.matches();
			if (isMatched) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断日期是否有效 dateStr传入的格式有yyyyMMdd;yyyy-MM-dd;yyyy/MM/dd
	 * 
	 * @param dateStr
	 * @return true：表示有效；false：表示无效
	 */
	public static boolean checkDate(String dateStr) {

		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(dateStr);
		boolean b = m.matches();

		return b;

	}

	// 获取周岁函数
	// 传入格式YYYY-MM-DD比如：2010-04-08
	public static int getAge(String strBirthday) {

		int returnAge;
		String[] strBirthdayArr = strBirthday.split("-");
		int birthYear = Integer.parseInt(strBirthdayArr[0]);
		int birthMonth = Integer.parseInt(strBirthdayArr[1]);
		int birthDay = Integer.parseInt(strBirthdayArr[2]);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		int nowYear = calendar.get(Calendar.YEAR);
		int nowMonth = calendar.get(Calendar.MONTH) + 1;
		int nowDay = calendar.get(Calendar.DATE);

		if (nowYear <= birthYear) {
			returnAge = 0;// 同年 则为0岁
		} else {
			int ageDiff = nowYear - birthYear; // 年之差
			if (ageDiff > 0) {
				if (nowMonth == birthMonth) {
					int dayDiff = nowDay - birthDay;// 日之差
					if (dayDiff <= 0) {
						returnAge = ageDiff - 1;
					} else {
						returnAge = ageDiff;
					}
				} else {
					int monthDiff = nowMonth - birthMonth;// 月之差
					if (monthDiff < 0) {
						returnAge = ageDiff - 1;
					} else {
						returnAge = ageDiff;
					}
				}
			} else {
				returnAge = -1;// 返回-1 表示出生日期输入错误 晚于今天
			}
		}
		return returnAge;// 返回周岁年龄
	}

	/**
	 * 从身份证中获取生日信息
	 * 
	 * @param cardHolderId
	 * @return
	 */
	public static String getBirthDayByCardId(String cardHolderId) {

		String birthDay = "";

		if (cardHolderId.length() == 15) {
			birthDay = "19" + cardHolderId.substring(6, 8) + "-"
					+ cardHolderId.substring(8, 10) + "-"
					+ cardHolderId.substring(10, 12);
		} else if (cardHolderId.length() == 18) {
			birthDay = cardHolderId.substring(6, 10) + "-"
					+ cardHolderId.substring(10, 12) + "-"
					+ cardHolderId.substring(12, 14);
		}

		return birthDay;

	}

	/**
	 * 从身份证中获取性别
	 * 
	 * @param cardHolderId
	 * @return
	 */
	public static String getSexByCardId(String cardHolderId) {

		String sex = "";

		if (cardHolderId.length() == 15) {
			if (Integer.parseInt(cardHolderId.substring(14, 15)) % 2 == 0) {
				sex = "F";
			} else {
				sex = "M";
			}
		} else if (cardHolderId.length() == 18) {
			if (Integer.parseInt(cardHolderId.substring(16, 17)) % 2 == 0) {
				sex = "F";
			} else {
				sex = "M";
			}
		}

		return sex;

	}

	public static boolean compareDate(String dateStr, int day) {

		boolean flag = false;

		try {

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			Date compareDate = DateUtils.addDays(new Date(), day);

			flag = date.before(compareDate);

		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;

	}

	/**
	 * 去除输入字符串小数点后多余的零
	 * 
	 * @param b
	 * @return
	 */
	public static String removeTailZero(String b) {
		String s = b;
		int i, len = s.length();
		for (i = 0; i < len; i++)
			if (s.charAt(len - 1 - i) != '0')
				break;
		if (s.charAt(len - i - 1) == '.')
			return s.substring(0, len - i - 1);
		return s.substring(0, len - i);

	}

	/**
	 * 判断输入字符是否是英文字符
	 * 
	 * @param c
	 * @return 如果是英文字符返回true,否则返回false
	 */
	public static boolean isEnChar(char c) {
		if (c >= 0x0000 && c <= 0x00FF) // 英文字符
			return true;
		return false;
	}

	/**
	 * 判断输入字符是否是中文字符
	 * 
	 * @param c
	 * @return 如果是中文字符返回true,否则返回false
	 */
	public static boolean isCnChar(char c) {
		if ((c >= 0x0391 && c <= 0xFFE5))// 中文字符
			return true;
		return false;
	}

	/**
	 * 判断输入的字符串是否全部由中文字符组成
	 * 
	 * @param s
	 * @return 如果全部是中文字符返回true,否则false
	 */
	public static boolean isCnString(String s) {
		char[] chs = s.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			if (!isCnChar(chs[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 截取原字符串<code>originalStr</code>最大长度不超过<code>maxLength</code>,拼装子字符串不超过
	 * <code>maxCount</code>个数的子字符串，拼装子字符串通过分割字符串<code>splitStr</code>分割获取.
	 * 
	 * <p>
	 * eg.
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 * 
	 * @param originalStr
	 *            原字符串
	 * @param splitStr
	 *            分割字符串
	 * @param maxCount
	 *            最大拼装子字符串数
	 * @param maxLength
	 *            返回子字符串的最大长度
	 * @return
	 */
	public static String subString(String originalStr, String splitStr,
			int maxCount, int maxLength) {
		StringBuffer subStrBuff = new StringBuffer();
		
		String originalStrTmp = originalStr;
		if (StrUtil.isEmpty(originalStrTmp))
			return originalStrTmp;
		
		if (maxLength < 0) {
			maxLength = originalStrTmp.length();
		}
		
		String[] strs = originalStrTmp.split(splitStr);

		int count = maxCount > strs.length ? strs.length : maxCount;
		for (int i = 0; i < count; i++) {
			String strTmp = strs[i];
			if (i == count - 1) {
				subStrBuff.append(strTmp);
			} else {
				subStrBuff.append(strTmp).append(splitStr);
			}
		}

		String subStr = subStrBuff.toString();

		if (StrUtil.isNotEmpty(subStr) && subStr.length() > maxLength) {
			subStr = subStr.substring(0, maxLength);
		}

		return subStr;
	}
	
	/**
	 * 将类似{k1:v1,k2,v2,...}格式的字符串转换成Map,不符合格式的字符串返回<code>new HashMap()</code>.
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map parseMap(String str) {
		Map valueMap = new HashMap();
		if (StrUtil.isNotEmpty(str)) {
			str = str.trim();
			if (str.length() > 1) {
				char firstChar = str.charAt(0);
				char lastChar = str.charAt(str.length() - 1);
				if (firstChar == '{' && lastChar == '}') {
					str = str.substring(1, str.length() - 1);
					String[] strs = str.split(",");
					for (int i = 0; i < strs.length; i++) {
						String tmpValueStr = strs[i].trim();
						int index = tmpValueStr.indexOf(':');
						if (index != -1 && index > 0
								&& index < tmpValueStr.length() - 1) {
							String k = tmpValueStr.substring(0, index).trim();
							String v = tmpValueStr.substring(index + 1,
									tmpValueStr.length()).trim();
							valueMap.put(k, v);
						}
					}
				}
			}
		}
		return valueMap;
	}
	
	public static String replaceBlank(String str, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		String after = m.replaceAll("");
		return after;
	}
	
	public static String valueOfIgnoreExcption(double value) {
		String str = "";
		try {
			str = String.valueOf(value);
		} catch (Exception e) {
			log.error("valueOfIgnoreExcption", e);
		}
		return str;
	}
	
	public static String getByteSubStr(String str, int bytelength) {
        if (str == null) {
            return null;
        }
        if (bytelength <= 0) {
            return "";
        }
        try {
            if (str.getBytes("GBK").length <= bytelength) {
                return str;
            }
        } catch (Exception ex) {
        }
        StringBuffer buff = new StringBuffer();

        int index = 0;
        char c;
        while (bytelength > 0) {
            c = str.charAt(index);
            if (c < 128) {
                bytelength--;
            } else {//GBK编码汉字占2字节，其他编码的不一样，要修改下面几句
                bytelength--;
                if (bytelength < 1)//被截断了
                    break;
                bytelength--;
            }
            buff.append(c);
            index++;
        }
        return buff.toString();
    }
	
	/**
	 * 给指定字符串增加前缀
	 * @param originalStr 原始值
	 * @param prefix 前缀
	 * @return
	 */
	public static String addPrefix(String originalStr, String prefix) {
		if (isEmpty(originalStr)) {
			return null;
		}
		if (isEmpty(prefix)) {
			return originalStr;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(prefix);
		sb.append(originalStr);
		return sb.toString();
	}
	

	/**
	 * 将字节数组转为UTF-8字符串
	 * @param bytes 字节数组
	 * @return
	 */
	public static String byteToStrUTF8 (byte[] bytes){
		String str = "";
		try {
			str = new String(bytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 将字节数组转为UTF-8字符串
	 * @param bytes 字节数组
	 * @return
	 */
	public static String byteToStrUTF8 (byte[] bytes,String chart){
		String str = "";
		try {
			if(isEmpty(chart)){
			  str = new String(bytes,"UTF-8");
			}else{
			  str = new String(bytes,chart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 将字节数组转为UTF-8字符串
	 * @param bytes 字节数组
	 * @return
	 */
	public static String bytesToStrUTF8 (byte[] bytes){
		String str = "";
		try {
			str = new String(bytes,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
	/**
	 * 字符串全半角替换
	 * 
	 * @throws Exception
	 */
	public static String SBCToDBC(String str) throws Exception {
		if (null != str) {
			char c[] = str.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if ('\u3000' == c[i]) {
					c[i] = ' ';
				} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
					c[i] = (char) (c[i] - 65248);
				}
			}
			String dbc = new String(c);
			return dbc;
		} else {
			return null;
		}
	}

	/**
	 * 字符串去掉所有空白字符。
	 * 
	 * @throws Exception
	 */
	public static String replaceBlank(String str) {
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
		}
		return str;
	}
	
	public static String noNull(Object str) {
		if (str == null) {
			return "";
		}
		return str.toString();
	}
}
