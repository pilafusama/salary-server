package com.tenpay.wxwork.wxworklib.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.tenpay.wxwork.wxworklib.exception.WxworkException;
import com.tenpay.wxwork.wxworklib.WxworkConfig;
import com.tenpay.wxwork.wxworklib.service.WxAccessTokenService;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * http://work.weixin.qq.com/api/devtools/devtool.php
 * Created by yaogangli on 2018/2/22.
 */
@Component
public class WxworkHttpClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(WxworkHttpClient.class);

    @Resource
    private WxworkConfig wxworkConfig;

    @Resource
    private WxAccessTokenService wxAccessTokenService;

    @Resource
    private Gson gson;

    /**
     * 获取预授权码
     * https://work.weixin.qq.com/api/doc#10975/%E8%8E%B7%E5%8F%96%E9%A2%84%E6%8E%88%E6%9D%83%E7%A0%81
     *
     */
    public String getPreAuthCode(String suiteId, boolean isSuiteReleased) {
        String accessToken = wxAccessTokenService.getSuiteAccessToken(suiteId);
        JsonObject result = invoke("/service/get_pre_auth_code?suite_access_token=" + accessToken);
        if (result.get("errcode").getAsInt() != 0) {
            throw new WxworkException(result.get("errcode").getAsInt(), result.get("errmsg").getAsString());
        }

        String preAuthCode = result.get("pre_auth_code").getAsString();

        /**
         * 设置授权码的授权类型
         * https://work.weixin.qq.com/api/doc#10975/%E8%AE%BE%E7%BD%AE%E6%8E%88%E6%9D%83%E9%85%8D%E7%BD%AE
         *
         */
        JsonObject req = new JsonObject();
        req.addProperty("pre_auth_code", preAuthCode);
        JsonObject reqSession = new JsonObject();
        reqSession.addProperty("auth_type", isSuiteReleased ? 0 : 1); // 授权类型：0 正式授权， 1 测试授权
        req.add("session_info", reqSession);
        invoke("/service/set_session_info?suite_access_token=" + accessToken, req);

        return preAuthCode;
    }

    /**
     * https://work.weixin.qq.com/api/doc#10975/获取企业永久授权码
     *
     * authCode 临时授权码
     */
    public JsonObject getPermanentCode(String suiteId, String authCode) {
        JsonObject body = new JsonObject();
        body.addProperty("auth_code", authCode);

        String accessToken = wxAccessTokenService.getSuiteAccessToken(suiteId);
        return invoke("/service/get_permanent_code?suite_access_token=" + accessToken, body);
    }

    /**
     * 获取企业access_token
     * https://work.weixin.qq.com/api/doc#10975/%E8%8E%B7%E5%8F%96%E4%BC%81%E4%B8%9Aaccess_token
     *
     */
    public JsonObject getCorpAccessToken(String suiteId, String corpid, String permanentCode) {
        JsonObject body = new JsonObject();
        body.addProperty("auth_corpid", corpid);
        body.addProperty("permanent_code", permanentCode);

        String accessToken = wxAccessTokenService.getSuiteAccessToken(suiteId);
        return invoke("/service/get_corp_token?suite_access_token=" + accessToken, body);
    }

    /**
     * 给指定用户发送文本消息
     *
     */
    public void sendTextToUser(String accessToken, int agentId, String userId, String content) {
        JsonObject text = new JsonObject();
        text.addProperty("content", content);

        JsonObject body = new JsonObject();
        body.addProperty("msgtype", "text");
        body.addProperty("agentid", agentId);
        body.addProperty("touser", userId);
        body.add("text", text);

        invoke("/message/send?access_token=" + accessToken, body);
    }

    /**
     * 获取全部部门
     * https://work.weixin.qq.com/api/doc#10093
     *
     */
    public JsonObject getAllDepartments(String accessToken) {
        return invoke(String.format("/department/list?access_token=%s",
                                    accessToken));
    }

    /**
     * 获取企业某部门所有成员
     *
     */
    public JsonObject getDepartmentUsers(String accessToken, String departmentId) {
        int needFetchChild = 1;
        return invoke(String.format("/user/list?access_token=%s&department_id=%s&fetch_child=%d",
                                     accessToken, departmentId, needFetchChild));
    }

    /**
     * 以第三方应用身份获取用户基本信息（通过登录 code ）
     * https://work.weixin.qq.com/api/doc#10975/%E7%AC%AC%E4%B8%89%E6%96%B9%E6%A0%B9%E6%8D%AEcode%E8%8E%B7%E5%8F%96%E4%BC%81%E4%B8%9A%E6%88%90%E5%91%98%E4%BF%A1%E6%81%AF
     *
     */
    public JsonObject getBasicUserInfoAsSuite(String suiteId, String code) {
        String suiteAccessToken = wxAccessTokenService.getSuiteAccessToken(suiteId);
        return invoke(String.format("/service/getuserinfo3rd?access_token=%s&code=%s", suiteAccessToken, code));
    }

    /**
     * 以第三方应用身份获取用户详细信息（通过登录 user ticket ）
     * https://work.weixin.qq.com/api/doc#10975/%E7%AC%AC%E4%B8%89%E6%96%B9%E4%BD%BF%E7%94%A8user_ticket%E8%8E%B7%E5%8F%96%E6%88%90%E5%91%98%E8%AF%A6%E6%83%85
     *
     */
    public JsonObject getDetailedUserInfoAsSuite(String suiteId, String userTicket) {
        String suiteAccessToken = wxAccessTokenService.getSuiteAccessToken(suiteId);
        JsonObject body = new JsonObject();
        body.addProperty("user_ticket", userTicket);

        return invoke(String.format("/service/getuserdetail3rd?access_token=%s",
                                    suiteAccessToken), body);
    }

    /**
     * 获取登录用户信息
     * https://work.weixin.qq.com/api/doc#10991/%E8%8E%B7%E5%8F%96%E7%99%BB%E5%BD%95%E7%94%A8%E6%88%B7%E4%BF%A1%E6%81%AF
     *
     */
    public JsonObject getLoginInfo(String authCode) {
        String providerAccessToken = wxAccessTokenService.getProviderAccessToken();
        JsonObject body = new JsonObject();
        body.addProperty("auth_code", authCode);

        return invoke(String.format("/service/get_login_info?access_token=%s",
                                    providerAccessToken), body);
    }

    /**
     * POST
     *
     */
    public JsonObject invoke(String url, JsonObject postData) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(wxworkConfig.url + url);
            LOGGER.info("Executing request:{}", httpPost.getRequestLine());

            StringEntity entity = new StringEntity(postData.toString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            //assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
            String result = EntityUtils.toString(response.getEntity());
            LOGGER.info("wxwork response:{}", result);
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            if (jsonObject.has("errcode") && jsonObject.get("errcode").getAsInt() != 0) {
                throw new WxworkException(jsonObject.get("errcode").getAsInt(), jsonObject.get("errmsg").getAsString());
            }
            return jsonObject;
        } catch (IOException e) {
            LOGGER.error("sendToWxwork err!", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * GET
     *
     */
    public JsonObject invoke(String uri) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(wxworkConfig.url + uri);
            LOGGER.info("Executing request:{}", httpGet.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity());
            LOGGER.info("wxwork response:{}", result);
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            if (jsonObject.get("errcode").getAsInt() != 0) {
                throw new WxworkException(jsonObject.get("errcode").getAsInt(), jsonObject.get("errmsg").getAsString());
            }
            return jsonObject;
        } catch (IOException e) {
            LOGGER.error("sendToWxwork err!", e);
            throw new RuntimeException(e);
        }
    }

}
