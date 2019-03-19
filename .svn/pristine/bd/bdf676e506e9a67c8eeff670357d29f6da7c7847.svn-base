package com.tenpay.wxwork.salary.dto.deepsea;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;

import java.util.Arrays;
import java.util.Date;
import java.util.Base64;
import java.text.SimpleDateFormat;

public class LivenessDetectionResponse extends DeepseaResponse {

    private String status;

    private int score;

    private String return_photo; // 返回的数据是加密并 base64 编码的

    @Override
    public String toString() {
        return String.format("status: %s, score: %d, return_photo: %d, %s",
                             status, score, return_photo == null ? 0 : return_photo.length(),
                             super.toString());
    }

    public byte[] getDecryptedReturnPhoto(byte[] nonce) {
        String basicBase64 = getReturn_photo().replace("\n", ""); // 返回中带了换行符 \n
        byte[] photoBytes = Base64.getDecoder().decode(basicBase64);
        return photoBytes; // Not encrypted yet.
        // return decryptData(nonce, photoBytes);
    }

    public String getReturn_photo()
    {
        return return_photo;
    }

    public void setReturn_photo(String return_photo)
    {
        this.return_photo = return_photo;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}