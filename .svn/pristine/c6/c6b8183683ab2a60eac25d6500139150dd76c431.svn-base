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
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * OCR 请求基类
 *
 */
public class OcrRequest extends DeepseaRequest
{
    protected String nonce;

    protected byte[] image;

    public OcrRequest(byte[] photo)
    {
        super();

        byte[] nonceBytes = genNonceBytes();
        setNonce(BinUtil.bytes2HexString(nonceBytes));

        setImage(encryptData(nonceBytes, photo));

        String signSrc = String.format("appid=%s&nonce=%s&timestamp=%d",
                                       this.appid, this.nonce, this.timestamp);
        setSignature(calcSignature(signSrc));
    }

    public byte[] getImage()
    {
        return image;
    }

    protected void setImage(byte[] image)
    {
        this.image = image;
    }

    public String getNonce()
    {
        return nonce;
    }

    public void setNonce(String nonce)
    {
        this.nonce = nonce;
    }
}