package com.tenpay.fingate.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.tenpay.wxwork.salary.config.ConfigUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * aes加解密工具类
 * 
 * @author robenzhang
 */
public class AesUtil {
	private static final Log log = LogFactory.getLog(AesUtil.class);
	private static final String ALGORITHM = "AES/CBC/NoPadding";// 加密模式
	private static final String AES = "AES";// 加密模式

	/**
	 * 加密
	 * 
	 * @param content 需要加密的内容
	 * @param password 加密密码
	 * @param vectorKey 向量
	 * @return
	 */
	public static String encrypt(String content, String password, String vectorKey) {
		if (password == null) {
			log.info("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (password.length() != 16) {
			log.info("Key长度不是16位");
			return null;
		}
		byte[] raw = new byte[0];
		try {
			raw = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);// "算法/模式/补码方式"
			int blockSize = cipher.getBlockSize();
			byte[] srawt = content.getBytes("UTF-8");
			int plaintextLength = srawt.length;
			if (blockSize != 0 && plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(srawt, 0, plaintext, 0, srawt.length);
			IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes("UTF-8"));// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(plaintext);
			String result = Base64.encodeBase64String(encrypted); // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
			return result;
		} catch (Exception e) {
			log.error("AesUtil.encrypt()加密失败：" + e.toString());
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content 待解密内容
	 * @param password 解密密钥
	 * @param vectorKey 向量
	 * @return
	 */
	public static String decrypt(String content, String password, String vectorKey) {
		try {
			// 判断Key是否正确
			if (password == null) {
				log.info("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (password.length() != 16) {
				log.info("Key长度不是16位");
				return null;
			}
			byte[] raw = password.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes("UTF-8"));
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decodeBase64(content);// 先用base64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original, "gbk");
				return originalString.trim();
			} catch (Exception e) {
				log.error("AesUtil.decrypt()解密失败：" + e.toString());
				return null;
			}
		} catch (Exception ex) {
			log.error("AesUtil.decrypt()解密失败：" + ex.toString());
			return null;
		}
	}

    // 获取加密key
    public static String[] getAesKey() {
        String allKey = MD5.parseStrToMd5L32(ConfigUtils.getFinGateAppkey() + "_" + ConfigUtils.getFinGateAppid());
        return new String[] { allKey.substring(0, 16), allKey.substring(16, 32) };
    }

    /**
     * 加密
     * @param content
     * @return
     */
    public static String encrypt(String content){
        return content;
//        return encrypt(content, getAesKey()[0], getAesKey()[1]);
    }

    /**
     * 解密
     * @param content
     * @return
     */
    public static String decrypt(String content){
        return content;
//        return decrypt(content, getAesKey()[0], getAesKey()[1]);
    }
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args){
		System.out.println(encrypt("b9vXTTO2SIcVAtrP7uhTgV6KprwDzAiUk35cKO4YB1kxcr3KpCQ8uo4VpRXq76hHubxI9X+WmyMaR260EJPdp2TOq1uSnrYMrNpVITwo/5/wPkwPZuDuJywta6jEDOaa", "7A166C6C4B7CA282", "C3D5E20963DC6E11"));
	}
}
