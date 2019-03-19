package com.tenpay.wxwork.wxworklib.service.impl;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.tenpay.wxwork.wxworklib.service.WxCryptService;

/**
 * Created by yaogangli on 2018/3/2.
 */
public class WxCryptServiceImpl implements WxCryptService {

    private WXBizMsgCrypt wxcpt;

    public WxCryptServiceImpl(WXBizMsgCrypt wxcpt) {
        this.wxcpt = wxcpt;
    }

    @Override
    public String encryptMsg(String replyMsg, String timeStamp, String nonce) {
        try {
            return wxcpt.EncryptMsg(replyMsg, timeStamp, nonce);
        } catch (AesException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decryptMsg(String msgSignature, String timeStamp, String nonce, String postData) {
        try {
            return wxcpt.DecryptMsg(msgSignature, timeStamp, nonce, postData);
        } catch (AesException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String verifyURL(String msgSignature, String timeStamp, String nonce, String echoStr) {
        try {
            return wxcpt.VerifyURL(msgSignature, timeStamp, nonce, echoStr);
        } catch (AesException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void main(String[] args) {
        String token = "UTu4Ofl0ch35dTgeCKfc4p3VNQLXBadG";
        String encodingAESKey = "xQwg6YPFxxnowEus6Iw9VtDjSIo8odlvtdf9Bi7XeWz";
        String corpId = "wwe897f06e8d6b5fb1";
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, encodingAESKey, corpId);
        String msgSig = "c044ec22f023876b15cc42ac295a2ee734bb7dd2";
        String timestamp = "1521170961";
        String nonce = "1354101920";
        String postData = "<xml><ToUserName><![CDATA[wwe897f06e8d6b5fb1]]></ToUserName>\n" +
                "<Encrypt><![CDATA[EwzqHOYrRwAsvZ1R+ILrMg37D9r0gZqe1QcxYapITWjWVjDalWpdvDZcpjdZpNwo/Bwjr80n8x6rnw+JEQtqbRDdijlFsslC6uA+cZvh2Srh9+teax14shqg1kOa3NnPm+yWUpZl9c9RJji3CPB/Q5TeMc9CD0kGuzJ8Rpt4bNML1WqwVy87HvqKSi4ws3Imduvt07JGgvVfIPOc3r/NIJLfHmsThcbPSced/PJG2bIVnMUeQEl8P4pY5Mz1NAqqOvousQEImN0qHZRf5+XaYflZOzmHC0C/W9snvMpKfgryERyHidzE9fF9Ok5jvQd/Ybzp9nhFBYmgpbinunNPoQhOFTdtO8NOVSvGtcQdnrd+uL/42xIqq8mT2+nEe6Bi]]></Encrypt>\n" +
                "<AgentID><![CDATA[1000032]]></AgentID>\n" +
                "</xml>";
        String result = wxBizMsgCrypt.DecryptMsg(msgSig, timestamp, nonce, postData);
        System.out.println(result);
    }*/
}
