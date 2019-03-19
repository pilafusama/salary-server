package com.tenpay.wxwork.salary.dto.deepsea;

import com.tenpay.wxwork.salary.util.BinUtil;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.config.ConfigUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.Mac;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LivenessDetectionRequest extends DeepseaRequest
{

    private String return_photo;

    private String nonce;

    private String token;

    private String idcard_name;

    private String idcard_number;

    private byte[] video;

    // 不能传到服务端
    @JsonIgnore
    private byte[] nonceBytes;

    public LivenessDetectionRequest(String token, String idcard_name,
                                    String idcard_number, byte[] video)
    {
        super();

        nonceBytes = genNonceBytes();
        setNonce(BinUtil.bytes2HexString(nonceBytes));

        setToken(token);

        setReturn_photo("1"); // 必须返回人脸照片

        Base64.Encoder base64 = Base64.getEncoder();
        try {
            setIdcard_name(base64.encodeToString(encryptData(nonceBytes, idcard_name.getBytes("UTF-8"))));
            setIdcard_number(base64.encodeToString(encryptData(nonceBytes, idcard_number.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String signSrc = String.format("appid=%s&nonce=%s&idcard_name=%s&idcard_number=%s&token=%s&return_photo=%s&timestamp=%d",
                                       this.appid, this.nonce, this.idcard_name,
                                       this.idcard_number, this.token, this.return_photo, this.timestamp);
        setSignature(calcSignature(signSrc));

        setVideo(encryptData(nonceBytes, video));
    }

    public String getReturn_photo()
    {
        return return_photo;
    }
    public void setReturn_photo(String return_photo)
    {
        this.return_photo = return_photo;
    }

    public byte[] getVideo()
    {
        return video;
    }

    public void setVideo(byte[] video)
    {
        this.video = video;
    }

    public String getIdcard_number()
    {
        return idcard_number;
    }

    public void setIdcard_number(String idcard_number)
    {
        this.idcard_number = idcard_number;
    }

    public String getIdcard_name()
    {
        return idcard_name;
    }

    public void setIdcard_name(String idcard_name)
    {
        this.idcard_name = idcard_name;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getNonce()
    {
        return nonce;
    }

    public void setNonce(String nonce)
    {
        this.nonce = nonce;
    }

    public void setNonceBytes(byte[] nonceBytes) {
        this.nonceBytes = nonceBytes;
    }

    public byte[] getNonceBytes() {
        return nonceBytes;
    }

}