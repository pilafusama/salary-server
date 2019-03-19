package com.tenpay.wxwork.wxworklib.service;

/**
 * Created by yaogangli on 2018/3/2.
 */
public interface WxCryptService {

    String encryptMsg(String replyMsg, String timeStamp, String nonce);

    String decryptMsg(String msgSignature, String timeStamp, String nonce, String postData);

    String verifyURL(String msgSignature, String timeStamp, String nonce, String echoStr);

}
