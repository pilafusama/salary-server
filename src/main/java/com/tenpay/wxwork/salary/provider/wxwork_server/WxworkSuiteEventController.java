package com.tenpay.wxwork.salary.provider.wxwork_server;

import com.qq.weixin.mp.aes.AesException;
import com.tenpay.wxwork.wxworklib.controller.AbstractSuiteController;
import com.tenpay.wxwork.wxworklib.service.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 应用的指令回调URL
 * https://work.weixin.qq.com/api/doc#12977/使用接收消息 <br/>
 * https://work.weixin.qq.com/api/doc#10982 <br/>
 * Created by yaogangli on 2018/2/23.
 */
@RestController
public class WxworkSuiteEventController extends AbstractSuiteController {

    @Resource
    private AuthService authService;

    // 开发环境前置代理探测
    @RequestMapping(value = "/", method = RequestMethod.HEAD)
    public String keepalive() {
        return "ok";
    }

    /**
     * 响应企业通过点击链接安装应用，非通过应用市场方式（指令通知）
     * https://work.weixin.qq.com/api/doc#10974 见服务商网站部分
     *
     * 生产环境暂时不会走到。
     */
    @RequestMapping(value = "/pc/corp_auth_callback", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String doCorpAuth(@RequestParam("auth_code") String authCode,
                             @RequestParam("expires_in") String expiresIn,
                             String state, HttpServletRequest request) {
        LOGGER.info("request:{},{}", request.getMethod(), request.getRequestURI());
        LOGGER.info("query string:{}", request.getQueryString());
        String suiteId = state;
        authService.createAuth(suiteId, authCode);

        return "<html><body><div>Thank you for using our app!</div></body></html>";
    }

    /**
     * 指令回调URL 有效性验证
     *
     */
    @RequestMapping(value = "/wxwork_server/directive_callback", method = RequestMethod.GET)
    public String verifyURL(@RequestParam("msg_signature") String msgSignature, String timestamp, String nonce,
                            int type, String echostr, HttpServletRequest request) {
        LOGGER.info("request:{},{}", request.getMethod(), request.getRequestURI());
        LOGGER.info("query string:{}", request.getQueryString());
        return verify(type, msgSignature, timestamp, nonce, echostr);
    }

    /**
     * 应用级别第三方回调URL，对应应用级别的“指令回调URL”<br>
     * https://work.weixin.qq.com/api/doc#10982 <br>
     *
     * @param msgSignature
     * @param timestamp
     * @param nonce
     * @param postData
     * @return
     * @throws AesException
     */
    @RequestMapping(value = {"/wxwork_server/directive_callback"}, method = RequestMethod.POST)
    public String receive(@RequestParam("msg_signature") String msgSignature, String timestamp, String nonce,
                          @RequestBody String postData, HttpServletRequest request) throws AesException {
        LOGGER.debug("request:{},{}", request.getMethod(), request.getRequestURI());
        LOGGER.debug("query string:{}", request.getQueryString());

        return handleDirective(msgSignature, timestamp, nonce, postData);
    }

    /**
     * 数据回调 URL 有效性验证
     * https://work.weixin.qq.com/api/doc#10514
     */
    @RequestMapping(value = "/wxwork_server/data_callback", method = RequestMethod.GET)
    public String verifyDataURL(@RequestParam("msg_signature") String msgSignature,
                                String timestamp, String nonce,
                                int type, String echostr, HttpServletRequest request) {
        LOGGER.info("request:{} {}", request.getMethod(), request.getRequestURI());
        LOGGER.info("query string:{}", request.getQueryString());
        return verify(type, msgSignature, timestamp, nonce, echostr);
    }

}
