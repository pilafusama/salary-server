package com.tenpay.wxwork.salary.service;

//this is the interface how to get session、userifo、and token

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.common.utils.DateUtils;
import com.tenpay.wxwork.salary.common.utils.ExceptionUtils;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.dao.AuthCkvDAO;
import com.tenpay.wxwork.salary.dao.UserDAO;
import com.tenpay.wxwork.salary.model.AuthenCkv;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.model.User;
import com.tenpay.wxwork.salary.service.wxauth.WxHttpClient;

@Service
public class SessionService {

    private final static int ACCESS_TOKEN_EXPIRE_IN = 7200;

    private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

    @Resource
    private CkvPlusClientFactory ckvPlusClientFactory;

    @Resource
    private Gson gson;

    // private static UserDAO userDAO;

    @Autowired
    private AuthCkvDAO authCkvDao;

    private AuthenCkv auth;

    //private AuthenCkvMapper authMapper;

    @Autowired
    private   WxHttpClient wxClient ;

    // @Autowired
    // private SuiteService suiteTicketStoreService;

    private AuthenCkv getCkvAccessToken(String id) {
        CkvPlusClient client = ckvPlusClientFactory.getClient();
        String sRes = client.get(Constant.PRE_WX_ACCESS_TOKEN + id);
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("getCkvAccessToken:{}",sRes );
        JsonObject jsonRet = gson.fromJson(sRes, JsonObject.class);
        String token = jsonRet.get("token").getAsString();
        int expire = jsonRet.get("expire").getAsInt();
        return  new AuthenCkv(token, ""+expire);
    }

    private void setCkvAccessToken(String id, AuthenCkv newAccessToken) throws Exception{
        CkvPlusClient client = ckvPlusClientFactory.getClient();
        String reply = client.set(Constant.PRE_WX_ACCESS_TOKEN + id, gson.toJson(newAccessToken));
        client.close();
        logger.info("setCkvAccessToken:{}", id);
        if (!Constant.OK.equals(reply)) {
            throw new Exception("setCkvAccessToken err:"+reply);
        }
    }


    public String getSuiteAccessToken(String suiteid) {
        return getCkvAccessToken(suiteid).getAuthCode();
    }


    // public String getProviderAccessToken() {
    //     return getCkvAccessToken(ConfigUtils.getCorpid()).getAuthCode();
    // }

    public String getCorpAccessToken(String corpId, String agentId) {
        return getCkvAccessToken(ConfigUtils.getCorpid()).getAuthCode();
    }

    public SessionInfo getSession(String sessionId) {
        if (StringUtils.isEmpty(sessionId)) {
            logger.warn("sessionId is null:{}", sessionId);
            return null;
        }
        CkvPlusClient client = ckvPlusClientFactory.getClient();
        String sessionStr = client.get(Constant.USE_SESSION_PREFIX + sessionId);
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("getSession from ckv+:{},{}", sessionId, sessionStr);
        SessionInfo sessionInfo = gson.fromJson(sessionStr, SessionInfo.class);

        // 判断有没有过期
        if (sessionInfo != null && sessionInfo.isValidate()) {
            // 每次获取的时候是否更新一下session的过期时间
            //setSession(sessionId, sessionInfo);
            return sessionInfo;
        }

        return null;
    }

    public void setSession( SessionInfo sessionInfo) {
        String sessionId = sessionInfo.getSessionId();

        int currTime = DateUtils.getTimestamp();
        sessionInfo.setCreateTime(currTime);
        sessionInfo.setExpireTime(currTime + ConfigUtils.getSessionExpiresIn());
        CkvPlusClient client = ckvPlusClientFactory.getClient();
        // cache过期时间比sesesion长即可：暂定比session过期时间长10分钟
        String reply = client.setex(Constant.USE_SESSION_PREFIX + sessionId,
                                                              ConfigUtils.getSessionExpiresIn() + 600,
                                                              gson.toJson(sessionInfo));
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!Constant.OK.equals(reply)) {
            throw new BizException(BizError.SET_CKV_FAIL.errorCode(), BizError.GET_CKV_FAIL.errorMsg());
        }

        logger.info("set session to ckv+:{}, {}, {}", sessionId, sessionInfo, reply);
    }

    /*
    public  AuthenCkv getNewSuiteAccessToken(String suiteId) {
        // https://work.weixin.qq.com/api/doc#10975/获取第三方应用凭证
        //AuthenCkv authTicket = new AuthenCkv("", "", suiteId, "suite_ticket");
        //AuthenCkv authKey = authMapper.selectByPrimaryKey(authTicket);
        JsonObject data = new JsonObject();
        data.addProperty("suite_id", suiteId);
        data.addProperty("suite_secret", ConfigUtils.getSuiteSecret());
        //data.addProperty("suite_ticket", authKey.getAuthCode());
        data.addProperty("suite_ticket", suiteTicketStoreService.getSuiteTicket(suiteId));

        String res = wxClient.get_suite_token(data.toString());
        logger.debug(res);
        JsonObject jsonRet =  gson.fromJson(wxClient.get_suite_token(data.toString()), JsonObject.class) ;
        if(jsonRet.has("errcode") ) {
            ExceptionUtils.checkResult(jsonRet.get("errcode").getAsInt(), jsonRet.get("errmsg").getAsString());
        }
        String token = jsonRet.get("suite_access_token").getAsString();
        int expire = jsonRet.get("expires_in").getAsInt();
        expire -= 600;

        // 提前10分钟更新access_token
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, ACCESS_TOKEN_EXPIRE_IN - 600);
        //authCkvDao.updateAuthCkv(token, expire, "suite_access_token", suiteId, "", "");
        //auth.setAuthCode(token);
        //auth.setExpired(""+expire);
        auth = new AuthenCkv(token, ""+expire);
        try{
            setCkvAccessToken(suiteId, auth);
        }
        catch(Exception e){
            logger.error("setCkvAccessToken"+e.getMessage());
        }

        return auth;
    }
    */

    public  AuthenCkv getNewCorpAccessToken(String corpId, String agentId/*应用类型，审批、通讯录等*/) {
        // https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRECT
        JsonObject data = new JsonObject();
        data.addProperty("corpid", corpId);
        data.addProperty("corpsecret", ConfigUtils.getSecret() );

        JsonObject jsonRet = gson.fromJson(wxClient.get_token(corpId, ConfigUtils.getSecret()), JsonObject.class);
        if(jsonRet.has("errcode") ) {
            ExceptionUtils.checkResult(jsonRet.get("errcode").getAsInt(), jsonRet.get("errmsg").getAsString());
        }
        String token = jsonRet.get("access_token").getAsString();
        int expire = jsonRet.get("expires_in").getAsInt();
        expire -= 600;

        // 提前10分钟更新access_token
        //Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.SECOND, ACCESS_TOKEN_EXPIRE_IN - 600);
        //authCkvDao.updateAuthCkv(token, expire, "access_token", "", corpId, "");
        AuthenCkv accessToken = new AuthenCkv(token, ""+expire);
        try{
        setCkvAccessToken(corpId+agentId, auth);
        }
        catch(Exception e){
            logger.error("setCkvAccessToken"+e.getMessage());
        }
        return accessToken;
    }

    public AuthenCkv getNewProviderAccessToken() {
        // https://work.weixin.qq.com/api/doc#11791/服务商的token
        JsonObject data = new JsonObject();
        data.addProperty("corpid", ConfigUtils.getCorpid());
        data.addProperty("provider_secret", ConfigUtils.getProviderSecret());
        JsonObject jsonRet = gson.fromJson(wxClient.get_provider_token(data.toString()), JsonObject.class);
        if(jsonRet.has("errcode") ) {
            ExceptionUtils.checkResult(jsonRet.get("errcode").getAsInt(), jsonRet.get("errmsg").getAsString());
        }
        String token = jsonRet.get("provider_access_token").getAsString();
        int expire = jsonRet.get("expires_in").getAsInt();

        // 提前10分钟更新access_token，所以我们的过期时间要比企业微信少10分钟
        //Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.SECOND, expire - 600);
        return new AuthenCkv(token, ""+expire);

    }

    public  User getuserinfo3rd(String suiteAccessToken, String code) {
        User user = new User();

        //1. ticket
        String sresult= wxClient.getuserinfo3rd( suiteAccessToken , code);
        logger.debug(sresult);
        JsonObject result =gson.fromJson(sresult, JsonObject.class) ;
        if(null == result){
            return null;
        }
        else if(result.has("errcode") ) {
            ExceptionUtils.checkResult(result.get("errcode").getAsInt(), result.get("errmsg").getAsString());
        }

        user.setCorpId(null == result.get("CorpId") ? null:result.get("CorpId").getAsString());
        user.setUserId(null == result.get("UserId") ? null:result.get("UserId").getAsString());

        if(null != result.get("user_ticket")){
            //2. info
            JsonObject reqData = new JsonObject();
            reqData.addProperty("user_ticket", result.get("user_ticket").getAsString());
            sresult = wxClient.getuserdetail3rd(suiteAccessToken, reqData.toString()); //invoke("/service/getuserdetail3rd?access_token=" + suiteAccessToken, reqData);
            logger.debug(sresult);
            JsonObject jsonRet =  gson.fromJson(sresult, JsonObject.class) ;
           if(jsonRet.has("errcode") ) {
                ExceptionUtils.checkResult(jsonRet.get("errcode").getAsInt(), jsonRet.get("errmsg").getAsString());
            }


            user.setCorpId(null == result.get("corpid") ? null:result.get("corpid").getAsString());
            user.setUserId(null == result.get("userid") ? null:result.get("userid").getAsString());
            user.setName(null == result.get("name") ? null:result.get("name").getAsString());
            if (result.has("department")) {
                user.setDepartment(result.get("department").getAsJsonArray().toString());
            }
            if (result.has("position")) {
                user.setPosition(result.get("position").getAsString());
            }
            if (result.has("mobile")) {
                user.setMobile(result.get("mobile").getAsString());
            }
            if (result.has("email")) {
                user.setEmail(result.get("email").getAsString());
            }
            if (result.has("gender")) {
                user.setGender(result.get("gender").getAsInt());
            }
            if (result.has("avatar")) {
                user.setAvatar(result.get("avatar").getAsString());
            }
            user.setLastLogintime(new Date());
        }

        return user;

    }

    public  User getuserinfo3plat(String providerAccessToken, String code) {
        User user = new User();

        //1. ticket
        JsonObject reqData = new JsonObject();
        reqData.addProperty("auth_code", code);
        String sresult= wxClient.get_login_info( providerAccessToken , reqData.toString());
        logger.debug(sresult);
        JsonObject result =gson.fromJson(sresult, JsonObject.class) ;
        if(null == result){
            return null;
        }
        else if(result.has("errcode") ) {
            ExceptionUtils.checkResult(result.get("errcode").getAsInt(), result.get("errmsg").getAsString());
        }

        JsonObject user_info = result.get("user_info").getAsJsonObject();
        if(null != user_info){
            user.setUserId(null == user_info.get("userid") ? null: user_info.get("userid").getAsString());
            user.setName( null == user_info.get("name") ? null:user_info.get("name").getAsString());
            user.setAvatar(null == user_info.get("avatar") ? null:user_info.get("avatar").getAsString());
        }
        JsonObject corp_info = result.get("corp_info").getAsJsonObject();
        if(null != corp_info){
        user.setCorpId(null == corp_info.get("corpid") ? null: corp_info.get("corpid").getAsString());
        }
        user.setLastLogintime(new Date());

        return user;

    }

    public void verifySign(String ssid, int timestamp, String nonce, String sign) throws BizException{
        SessionInfo ssion = getSession(ssid);
        if(null == ssion){
            throw new BizException(BizError.GET_SESSION_FAILED.errorCode(),"invalid ssid:"+ssid);
        }
        else{
            // if(sign.equals(md5(ssid+timestamp+nonce))

            //String  src = ssid+timestamp+nonce+ssion.getSskey();
            //MessageDigest md5=MessageDigest.getInstance("MD5");
            //byte[] bSign = md5.digest(src.getBytes("GBK"));

        }
    }

}
