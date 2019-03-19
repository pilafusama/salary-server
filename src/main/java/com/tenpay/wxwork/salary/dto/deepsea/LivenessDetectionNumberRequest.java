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

public class LivenessDetectionNumberRequest extends DeepseaRequest
{

    public LivenessDetectionNumberRequest()
    {
        super();

        // DTO 数据都在基类中
        String signSrc = String.format("appid=%s&timestamp=%d",
                                       this.appid, this.timestamp);
        setSignature(calcSignature(signSrc));
    }
}