package com.tenpay.wxwork.salary.dto.deepsea;

import com.tenpay.wxwork.salary.util.BinUtil;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.config.ConfigUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.Mac;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 响应报文，包含基础的两个字段
 *
 */
public abstract class DeepseaResponse {

    protected static final Logger logger = LoggerFactory.getLogger(DeepseaResponse.class);

    public static final int GCM_NONCE_LENGTH = 16; // in bytes
    public static final int GCM_TAG_LENGTH = 16; // in bytes

    @JsonIgnore
    protected String appsecret;

    private int code;

    private String message;

    public DeepseaResponse() {
        appsecret = ConfigUtils.getDeepseaAppSecret();
    }

    @Override
    public String toString() {
        return String.format("code: %d, message: %s", getCode(), getMessage());
    }

    protected byte[] decryptData(byte[] nonce, byte[] data)
    {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(this.appsecret.getBytes("UTF-8"));
            SecretKey decryptKey = new SecretKeySpec(md5Bytes, 0, md5Bytes.length, "AES");

            // Decrypt
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "SunJCE");
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
            cipher.init(Cipher.DECRYPT_MODE, decryptKey, spec);

            return cipher.doFinal(data);
        }
        catch (Exception e) {
            logger.error("failed to decrypt data: " + e.getMessage());
            throw new BizException(BizError.AES_DECRYPT_ERROR);
        }
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}