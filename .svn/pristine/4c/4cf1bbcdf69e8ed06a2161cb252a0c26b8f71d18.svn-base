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

public class OcrIdCardRequest extends OcrRequest
{
    private int type;

    public OcrIdCardRequest(boolean isFrontPhoto, byte[] photo)
    {
        super(photo);

        setType(isFrontPhoto ? 0 : 1);

        String signSrc = String.format("appid=%s&nonce=%s&type=%d&timestamp=%d",
                                       this.appid, this.nonce, this.type, this.timestamp);
        setSignature(calcSignature(signSrc));
    }

    public int getType()
    {
        return type;
    }

    private void setType(int type)
    {
        this.type = type;
    }
}