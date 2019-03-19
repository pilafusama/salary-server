package com.tenpay.wxwork.salary.service;

import com.tenpay.wxwork.salary.presvr.sender.bean.UploadIdCardPhotoReq;
import com.tenpay.wxwork.salary.presvr.sender.bean.UploadIdCardPhotoRes;
import com.tenpay.wxwork.salary.dao.SalaryAccountDAO;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.model.SalaryAccount;
import com.tenpay.wxwork.salary.service.SessionService;
import com.tenpay.wxwork.salary.service.BankProxyRequestService;
import com.tenpay.wxwork.salary.util.BinUtil;
import com.tenpay.wxwork.salary.util.MapConvert;
import com.tenpay.wxwork.salary.config.KeystoreConfig;
import com.tenpay.wxwork.salary.config.Range;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.KeyStoreService;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Base64;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class KeyCacheService {

    @Autowired
    private KeystoreConfig keystoreConfig;

    private static final Logger logger = LoggerFactory.getLogger(KeyCacheService.class);

    /**
     * 加密盐
     *
     */
    private String passwordSalt;

    /**
     * 帐号信息加密密码
     *
     */
    private String accountSecret;

    /**
     * 工资信息加密密码
     *
     */
    private String salarySecret;

    /**
     * openid 加密密码
     *
     */
    private String openidSecret;

    public String getSalarySecret() {
//        if (StringUtils.isBlank(salarySecret)) {
//            logger.info("init salary secret");
//            salarySecret = KeyStoreService.getDecryptedKey(keystoreConfig.getSalarySecretId(),
//                                                           keystoreConfig.getKeyVersion(),
//                                                           keystoreConfig.getSalaryPrivateKeyId(),
//                                                           keystoreConfig.getKeyVersion());
//        }
//
//        return salarySecret;
    	return "xxxx";
    }

    public String getAccountSecret() {
//        if (StringUtils.isBlank(accountSecret)) {
//            logger.info("init account secret");
//            accountSecret = KeyStoreService.getDecryptedKey(keystoreConfig.getAccountSecretId(),
//                                                            keystoreConfig.getKeyVersion(),
//                                                            keystoreConfig.getSalaryPrivateKeyId(),
//                                                            keystoreConfig.getKeyVersion());
//        }
//
//        return accountSecret;
        return "xxxx";
    }

    public String getOpenidSecret() {
//        if (StringUtils.isBlank(openidSecret)) {
//            logger.info("init openid secret");
//            openidSecret = KeyStoreService.getDecryptedKey(keystoreConfig.getOpenidSecretId(),
//                                                           keystoreConfig.getKeyVersion(),
//                                                           keystoreConfig.getSalaryPrivateKeyId(),
//                                                           keystoreConfig.getKeyVersion());
//        }
//
//        return openidSecret;
    	return "xxxx";
    }

    public String getPasswordSalt() {
//        if (StringUtils.isBlank(passwordSalt)) {
//            logger.info("init password salt");
//            passwordSalt = KeyStoreService.getKey(keystoreConfig.getPasswordSaltId(),
//                                                  keystoreConfig.getKeyVersion());
//        }
//
//        return passwordSalt;
    	return "xxxx";
    }

}
