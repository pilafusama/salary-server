package com.tenpay.fingate.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.tenpay.fingate.util.HexUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * sha256加密 
 * @author robenzhang
 */
public class Sha256 {
	// sha-256加密
	private static final Log log = LogFactory.getLog(AesUtil.class);
	public static String sha256(String input) {

		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
			byte[] result = mDigest.digest(input.getBytes("UTF-8"));
			return HexUtil.byteArrayToHexStr(result);
		} catch (NoSuchAlgorithmException e) {
			log.error("Sha256Util.sha256()加密失败：" + e.toString());
			return "";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}

	}
}
