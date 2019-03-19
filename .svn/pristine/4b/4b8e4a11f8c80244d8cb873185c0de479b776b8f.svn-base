package com.tenpay.wxwork.salary.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.print.DocFlavor;

import com.tenpay.fingate.util.MD5;
import com.tenpay.fingate.util.Sha256;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.service.KeyCacheService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * aes加解密工具类
 * 
 * @author robenzhang
 */
@Component
public class AesUtil {
	private static final Log log = LogFactory.getLog(AesUtil.class);
	private static final String ALGORITHM = "AES/CBC/NoPadding";// 加密模式
	private static final String AES = "AES";// 加密模式
	@Autowired
	private KeyCacheService auto_keyCacheService;
	private static KeyCacheService keyCacheService;

	@PostConstruct
	public void beforeInit(){
		keyCacheService = auto_keyCacheService;
	}

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
		try {
			byte[] raw = password.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
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
				String originalString = new String(original, "UTF-8");
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
    public static String[] getAesKey(String password) {
        String allKey = MD5.parseStrToMd5L32(password);
        return new String[] { allKey.substring(0, 16), allKey.substring(16, 32) };
    }

	/**
     * 加密账户表内容
	 * @param content
	 * @return
	 */
	public static String encryptAccount(String content){
		String[] allKey = getAesKey(keyCacheService.getAccountSecret());
		return encrypt(content, allKey[0], allKey[1]);
	}

	/**
	 * 加密薪资表内容
	 * @param content
	 * @return
	 */
	public static String encryptSalary(String content, String corpid){
		String[] allKey = getAesKey(Sha256.sha256(corpid+keyCacheService.getSalarySecret()));
		return encrypt(content, allKey[0], allKey[1]);
	}

	/**
	 * 字符串加密 openid
	 * @param content
	 * @return
	 */
	public static String encryptOpenid(String content){
		String[] allKey = getAesKey(keyCacheService.getOpenidSecret());
		return encrypt(content, allKey[0], allKey[1]);
	}

	/**
     * TODO 待删除
	 * 字符串加密
	 * @param content
	 * @return
	 */
	public static String encrypt(String content, String password) {
		String[] keys = getAesKey(password);
		return encrypt(content, keys[0], keys[1]);
	}

	/**
     * TODO 待删除
	 * 字符串密
	 * @param content
	 * @return
	 */
	public static String decrypt(String content, String password) {
		String[] keys = getAesKey(password);
		return decrypt(content, keys[0], keys[1]);
	}

	/**
	 * 字符串解密 上传工资明细 根据corpId进行加密
	 * @param content
	 * @return
	 */
	public static String decryptToCorp(String content,String corpidMsg){
		String[] allKey = getAesKey((Sha256.sha256(corpidMsg)));
		return decrypt(content, allKey[0], allKey[1]);
	}

	/**
     * TODO 最终改为 Autowired 注入方式（ encryptSalary ），直接取到密码，调用方就不需要每次传密码了
	 * 字符串加密 上传工资明细 根据corpId进行解密
	 * @param content
	 * @return
	 */
	public static String encryptToCorp(String content,String corpidMsg){
		String[] allKey = getAesKey(Sha256.sha256(corpidMsg));
		return encrypt(content, allKey[0], allKey[1]);
	}


	/**
	 * 字符串解密Openid
	 * @param content
	 * @return
	 */
	public static String decryptOpenid(String content){
		String[] allKey = getAesKey(keyCacheService.getOpenidSecret());
		return decrypt(content, allKey[0], allKey[1]);
	}

	/**
	 * 字符串解密Salary
	 * @param content
	 * @return
	 */
	public static String decryptSalary(String content,String corpid){
		String[] allKey = getAesKey((Sha256.sha256(corpid+keyCacheService.getSalarySecret())));
		return decrypt(content, allKey[0], allKey[1]);
	}

	/**
	 * 字符串解密Account
	 * @param content
	 * @return
	 */
	public static String decryptAccount(String content){
		String[] allKey = getAesKey(keyCacheService.getAccountSecret());
		return decrypt(content, allKey[0], allKey[1]);
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args){
		System.out.println(encrypt("105100000017", "7a166c6c4b7ca282", "c3d5e20963dc6e11"));
		System.out.println(decrypt("dqFeG3urtU6ZdJHmlBO32A==","7a166c6c4b7ca282", "c3d5e20963dc6e11"));

	}
}
