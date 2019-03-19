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
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class DeepseaRequest
{
    public static final int AES_KEY_SIZE = 128; // in bits
    public static final int GCM_NONCE_LENGTH = 16; // in bytes
    public static final int GCM_TAG_LENGTH = 16; // in bytes

    protected static final Logger logger = LoggerFactory.getLogger(DeepseaRequest.class);

    protected String appid;

    @JsonIgnore
    protected String appsecret;

    protected int timestamp;

    protected String signature;

    protected DeepseaRequest() {
        appid = ConfigUtils.getDeepseaAppId();
        appsecret = ConfigUtils.getDeepseaAppSecret();

        setTimestamp((int)(System.currentTimeMillis() / 1000L));
    }

    protected static String hmacSHA256(String message, String secret) {
        try {
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");

            Mac hmac = Mac.getInstance("HmacSHA256");
            hmac.init(secret_key);
            byte[] bytes = hmac.doFinal(message.getBytes("UTF-8"));

            return BinUtil.bytes2HexString(bytes);
        } catch (Exception e) {
            logger.error("failed to get hmac sha256 digest: " + e.getMessage());
            throw new BizException(BizError.AES_ENCRYPT_ERROR);
        }
    }

    // https://gist.github.com/praseodym/f2499b3e14d872fe5b4a
    protected byte[] encryptData(byte[] nonce, byte[] data)
    {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(this.appsecret.getBytes("UTF-8"));
            SecretKey encryptKey = new SecretKeySpec(md5Bytes, 0, md5Bytes.length, "AES");

            // Encrypt
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "SunJCE");
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
            cipher.init(Cipher.ENCRYPT_MODE, encryptKey, spec);

            return cipher.doFinal(data);
        }
        catch (Exception e) {
            logger.error("failed to encrypt data: " + e.getMessage());
            throw new BizException(BizError.AES_ENCRYPT_ERROR);
        }
    }

    protected String calcSignature(String signSrc)
    {
        logger.debug("sign src: {}", signSrc);

        return hmacSHA256(signSrc, appsecret);
    }

    protected byte[] genNonceBytes()
    {
        try {
            byte[] randomBytes = new byte[GCM_NONCE_LENGTH];

            // SecureRandom 可能被阻塞，先采用伪随机
            // https://stackoverflow.com/questions/137212/how-to-solve-slow-java-securerandom
            // https://zhaoyanblog.com/archives/784.html
            // SecureRandom random = SecureRandom.getInstanceStrong();
            // random.nextBytes(randomBytes);

            Random random = new Random();
            random.nextBytes(randomBytes);

            return randomBytes;
        }
        catch (Exception e) {
            logger.error("failed to generate random number: " + e.getMessage());
            throw new BizException(BizError.GEN_RANDOM_ERROR);
        }
    }

    public String getAppid()
    {
        return appid;
    }

    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    public int getTimestamp()
    {
        return timestamp;
    }

    protected void setTimestamp(int timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getSignature()
    {
        return signature;
    }

    protected void setSignature(String signature)
    {
        this.signature = signature;
    }
}