package com.tenpay.wxwork.salary.service;

import com.tenpay.wxwork.salary.common.utils.AesUtil;
import com.tenpay.wxwork.salary.service.KeyCacheService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by yaogangli on 2018/3/6.
 */
@Service
public class IdGenerator  {

    @Autowired
    private KeyCacheService keyCacheService;

    public String genenateOpenId(String corpId, String userId, String appId) {
        String source = corpId + userId + appId;
        // 创建加密对象 并傳入加密類型
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            // 传入要加密的字符串
            messageDigest.update(source.getBytes("UTF-8"));
            // 得到 byte 類型结果
            byte byteBuffer[] = messageDigest.digest();

            return Base64.getUrlEncoder().encodeToString(byteBuffer);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 工资条的 openid ，上边定义的不满足
     *
     */
    public String genSalaryOpenid(String corpid, String userid) {
        String version = "v01";
        String plain = corpid + "::" + userid;
        return version + AesUtil.encrypt(plain, keyCacheService.getOpenidSecret());
    }

    public String genSessionId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成管理员 ssid
     *
     */
    public String genAdminSessionId() {
        return "admin-" + genSessionId();
    }

    public boolean isAdminSessionId(String ssid) {
//        return ssid.startsWith("admin-");
        return true;
    }
}
