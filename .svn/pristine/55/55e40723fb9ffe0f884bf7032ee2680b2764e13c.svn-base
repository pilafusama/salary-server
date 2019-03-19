package com.tenpay.wxwork.salary.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtils {

    private static final Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

    private static String token;
    private static String aesKey;
    private static String secret;
    private static String corpid;
    private static String suiteTicket;
    private static String suiteSecret;
    private static String providerSecret;
    private static int sessionExpiresIn;
    private static int debug;
    private static String agentId;
    private static String approvalInfoStartTime;

    // salary 配置
    private static String openAccountMsgTemplate;
    private static String salaryDetailMsgTemplate;
    private static String suiteId;
    private static String baseUrl;

    private static String deepseaAppId;
    private static String deepseaAppSecret;

    private static String finGateAppid;
    private static String finGateAppkey;
    private static String finGateCardNumberEncryptPasswd;
    private static String finGateCardbinUrl;
    private static String smsCodeSendUrl;
    private static String smsCodeVerifyUrl;
    private static String smsAppid;
    private static String smsTransactionId;
    private static String smsUin;
    private static String smsRelationKey;
    private static String finGateL5ModId;
    private static String finGateL5CmdId;
    private static float finGateL5Timeout;

    public static String getSalaryDetailMsgTemplate() {
        return salaryDetailMsgTemplate;
    }
    @Value("${salary.salaryDetailMsgTemplate}")
    public void setSalaryDetailMsgTemplate(String salaryDetailMsgTemplate) {
        ConfigUtils.salaryDetailMsgTemplate = salaryDetailMsgTemplate;
    }

    public static String getOpenAccountMsgTemplate()
    {
        return openAccountMsgTemplate;
    }

    @Value("${salary.openAccountMsgTemplate}")
    public void setOpenAccountMsgTemplate(String openAccountMsgTemplate)
    {
        ConfigUtils.openAccountMsgTemplate = openAccountMsgTemplate;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    @Value("${salary.baseUrl}")
    public void setBaseUrl(String baseUrl) {
        ConfigUtils.baseUrl = baseUrl;
    }

    public static float getFinGateL5Timeout()
    {
        return finGateL5Timeout;
    }

    @Value("${fingate.l5.timeout}")
    public void setFinGateL5Timeout(float finGateL5Timeout)
    {
        ConfigUtils.finGateL5Timeout = finGateL5Timeout;
    }

    public static String getFinGateL5CmdId()
    {
        return finGateL5CmdId;
    }

    @Value("${fingate.l5.cmdid}")
    public void setFinGateL5CmdId(String finGateL5CmdId)
    {
        ConfigUtils.finGateL5CmdId = finGateL5CmdId;
    }

    public static String getFinGateL5ModId()
    {
        return finGateL5ModId;
    }

    @Value("${fingate.l5.modid}")
    public void setFinGateL5ModId(String finGateL5ModId)
    {
        ConfigUtils.finGateL5ModId = finGateL5ModId;
    }

    public static String getFinGateCardbinUrl()
    {
        return ConfigUtils.finGateCardbinUrl;
    }

    @Value("${fingate.cardbinUrl}")
    public void setFinGateCardbinUrl(String finGateCardbinUrl)
    {
        ConfigUtils.finGateCardbinUrl = finGateCardbinUrl;
    }

    public static String getSmsCodeSendUrl() {
        return smsCodeSendUrl;
    }
    @Value("${fingate.smsCodeSendUrl}")
    public void setSmsCodeSendUrl(String smsCodeSendUrl) {
        ConfigUtils.smsCodeSendUrl = smsCodeSendUrl;
    }

    public static String getSmsCodeVerifyUrl() {
        return smsCodeVerifyUrl;
    }
    @Value("${fingate.smsCodeVerifyUrl}")
    public void setSmsCodeVerifyUrl(String smsCodeVerifyUrl) {
        ConfigUtils.smsCodeVerifyUrl = smsCodeVerifyUrl;
    }

    public static String getFinGateCardNumberEncryptPasswd()
    {
        return finGateCardNumberEncryptPasswd;
    }

    @Value("${fingate.cardNumberEncryptPasswd}")
    public void setFinGateCardNumberEncryptPasswd(String finGateCardNumberEncryptPasswd)
    {
        ConfigUtils.finGateCardNumberEncryptPasswd = finGateCardNumberEncryptPasswd;
    }

    public static String getFinGateAppkey()
    {
        return finGateAppkey;
    }

    @Value("${fingate.appkey}")
    public void setFinGateAppkey(String finGateAppkey)
    {
        ConfigUtils.finGateAppkey = finGateAppkey;
    }

    public static String getFinGateAppid()
    {
        return finGateAppid;
    }

    @Value("${fingate.appid}")
    public void setFinGateAppid(String finGateAppid)
    {
        ConfigUtils.finGateAppid = finGateAppid;
    }

    public static String getDeepseaAppSecret()
    {
        return deepseaAppSecret;
    }

    public static String getSmsAppid() {
        return smsAppid;
    }
    @Value("${fingate.smsAppid}")
    public void setSmsAppid(String smsAppid) {
        ConfigUtils.smsAppid = smsAppid;
    }

    public static String getSmsTransactionId() {
        return smsTransactionId;
    }
    @Value("${fingate.smsTransactionId}")
    public void setSmsTransactionId(String smsTransactionId) {
        ConfigUtils.smsTransactionId = smsTransactionId;
    }

    public static String getSmsUin() {
        return smsUin;
    }
    @Value("${fingate.smsUin}")
    public void setSmsUin(String smsUin) {
        ConfigUtils.smsUin = smsUin;
    }

    public static String getSmsRelationKey() {
        return smsRelationKey;
    }
    @Value("${fingate.smsRelationKey}")
    public void setSmsRelationKey(String smsRelationKey) {
        ConfigUtils.smsRelationKey = smsRelationKey;
    }

    @Value("${deepsea.appsecret}")
    public void setDeepseaAppSecret(String deepseaAppSecret)
    {
        ConfigUtils.deepseaAppSecret = deepseaAppSecret;
    }

    public static String getDeepseaAppId()
    {
        return deepseaAppId;
    }

    @Value("${deepsea.appid}")
    public void setDeepseaAppId(String deepseaAppId)
    {
        ConfigUtils.deepseaAppId = deepseaAppId;
    }

    public static String getApprovalInfoStartTime() {
        return approvalInfoStartTime;
    }

    @Value("${approvalInfoStartTime}")
    public void setApprovalInfoStartTime(String approvalInfoStartTime) {
        ConfigUtils.approvalInfoStartTime = approvalInfoStartTime;
    }

    public static String getAgentId() {
        return agentId;
    }
    @Value("${agentId}")
    public void setAgentId(String agentId) {
        ConfigUtils.agentId = agentId;
    }

    public static int getDebug() {
        return debug;
    }
    @Value("${debug}")
    public void setDebug(int debug) {
        ConfigUtils.debug = debug;
    }

    private static ConfigUtils configUtilInstance = new ConfigUtils();

    private ConfigUtils(){}

    public static ConfigUtils getInstance(){
        return configUtilInstance;
    }

    @Value("${wxworkdel.token}")
    public void setToken(String token) {
        ConfigUtils.token = token;
    }


    @Value("${wxworkdel.aesKey}")
    public void setAesKey(String aesKey) {
        ConfigUtils.aesKey = aesKey;
    }

    @Value("${salary.suiteId}")
    public void setSuiteid(String suiteId) {
        ConfigUtils.suiteId = suiteId;
    }

    public static String getSuiteId() {
        return suiteId;
    }

    public static String getSecret() {
        return secret;
    }

    @Value("${secret}")
    public  void setSecret(String secret) {
        ConfigUtils.secret = secret;
    }

    public static String getCorpid() {
        return corpid;
    }

    @Value("${wxworkdel.provider.corpid}")
    public  void setCorpid(String corpid) {
        ConfigUtils.corpid = corpid;
    }

    public static String getToken() {
        return token;
    }

    public static String getSuiteSecret() {
        return suiteSecret;
    }
    @Value("${wxworkdel.suiteSecret}")
    public  void setSuiteSecret(String suiteSecret) {
        ConfigUtils.suiteSecret = suiteSecret;
    }

    public static String getProviderSecret() {
        return providerSecret;
    }
    @Value("${wxworkdel.provider.secret}")
    public  void setProviderSecret(String providerSecret) {
        ConfigUtils.providerSecret = providerSecret;
    }
    public static int getSessionExpiresIn() {
        return sessionExpiresIn;
    }
    @Value("${sessionExpiresIn}")
    public  void setSessionExpiresIn(int sessionExpiresIn) {
        ConfigUtils.sessionExpiresIn = sessionExpiresIn;
    }

    public static String getStringValue(String key) {
        return "";
    }

}
