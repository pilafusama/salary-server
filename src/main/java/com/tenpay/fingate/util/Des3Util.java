package com.tenpay.fingate.util;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * des加解密工具类
 * 
 * @author robenzhang
 */
public class Des3Util {

	private static final Logger logger = Logger.getLogger(Des3Util.class);
	private static final String ALGORITHM = "DESede/ECB/NoPadding";// 加密模式
	private static final String DESEDE = "DESede";// 加密模式
	
	private Des3Util() {
	}

    // public static SecretKey toKey(byte[] key) throws Exception {
    //     KeySpec dks = new DESedeKeySpec(key);   
    //     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
    //     return keyFactory.generateSecret(dks);
    // }

	// 对于c2b转账key做特殊的处理
	private static byte[] buildKey(String key) {

        // 长度必须是32，一般是 MD5 摘要
        if (32 != key.length()) {
            throw new RuntimeException("key string must be of 32 bytes");
        }

		byte[] keyArr = HexUtil.hexStrToByteArray(key);
		// byte[] keyArr = key.getBytes();
		byte[] keyArr1 = Arrays.copyOfRange(keyArr, 0, 8); // key 必须24字节，所以从前部再拿8字节
		byte[] keyData = new byte[24];
        int i = 0;
		for (byte b : keyArr) {
			keyData[i++] = b;
		}

		for (byte b : keyArr1) {
			keyData[i++] = b;
		}

		return keyData;
	}

	// 进行加密
	public static String desEncode(String data, String key) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKey desKey = new SecretKeySpec(buildKey(key), DESEDE);
			cipher.init(Cipher.ENCRYPT_MODE, desKey);

            // https://stackoverflow.com/questions/16416039/java-triple-des-algorithm-padding-modes
            // 按照 java 标准，只有 NoPadding 和 PKCS5Padding ，这里是为了与金融网关实现保持一致
            int len = data.length();
            if (len % 8 > 0) {
                len += (8 - len % 8);
            }
            byte[] bytes = new byte[len];
            System.arraycopy(data.getBytes("UTF-8"), 0, bytes, 0, data.length());
            if (data.length() % 8 > 0) {
                int i = data.length();
                for (; i < len; i++) {
                    bytes[i] = 0; // padding
                }
            }
			byte[] encryptData = cipher.doFinal(bytes);

			return HexUtil.byteArrayToHexStr(encryptData);
		} catch (Exception e) {
			logger.error("Failed to encrypt data, stack:", e);
			return "";
		}
	}

	// 进行解密
	public static String desDecode(String encryptData, String key) {
		try {
			byte[] data = HexUtil.hexStrToByteArray(encryptData);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKey desKey = new SecretKeySpec(buildKey(key), DESEDE);
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			return new String(cipher.doFinal(data),"UTF-8");
		} catch (Exception e) {
			logger.error("Failed to encrypt data.", e);
			return "";
		}
	}

	// 测试结果
	public static void main(String[] args) throws Exception {
		String key = MD5.parseStrToMd5U32("%^;*FinPayBANKACCNO!@#$");
		String data = "6210985840000000000";
		System.out.println("data: " + data);
		System.out.println("encrypted data: " + desEncode(data, key));
	}

}
