package com.tenpay.wxwork.salary.service.wxauth;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;



























import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;

import com.tenpay.wxwork.salary.common.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.config.ConfigUtils;
import com.tenpay.wxwork.salary.dto.wxauth.LoginRequest;
import com.tenpay.wxwork.salary.dto.wxauth.LoginResponse;
import com.tenpay.wxwork.salary.model.SessionInfo;
import com.tenpay.wxwork.salary.model.User;
import com.tenpay.wxwork.salary.service.IdGenerator;
import com.tenpay.wxwork.salary.service.SessionService;







@Service
public class WxAuthService {


    @Autowired
    private IdGenerator idGenerator;



    @Autowired
    private SessionService sessionService;

    public LoginResponse login(LoginRequest req) {
        LoginResponse res ;
        try{
            // 1、参数检查
            //checkArgument(!isNullOrEmpty(code), "code is null");
            String suiteId = ConfigUtils.getSuiteId();

            // 2、登录态检查（在session复用时有效)
            SessionInfo sessionInfo = sessionService.getSession(req.getLogSsid());
            User userResult = new User();
            if (sessionInfo == null) {
                // 3、拉取suite_access_token，
                String suiteAccessToken = sessionService.getSuiteAccessToken(suiteId);

                //4.使用code调用getuserinfo3rd接口，获取userticket以及用户信息
                userResult = sessionService.getuserinfo3rd(suiteAccessToken , req.getCode());
                if(userResult == null){
                    throw new BizException(BizError.LOGIN_FAILED.errorCode(), BizError.LOGIN_FAILED.errorMsg());
                }
                // 5、生成登录态（userid）
                userResult.setLastPlatAppid(suiteId);
                userResult.setSessionId(idGenerator.genSessionId());
                // todo ---调用“登记用户信息接口”登记用户登录信息
                //sessionService.updateUser(userResult);

                sessionInfo = new SessionInfo();
                BeanUtils.copyProperties(userResult, sessionInfo);
                sessionService.setSession(sessionInfo);

            }
            //Cookie cookie = new Cookie(SESSION_KEY, sessionId);
            //cookie.setMaxAge(3600 * 24); // cookie得过期时间暂时一天吧
            //response.addCookie(cookie);


            /*根据获取的企业信息，调用“查询企业安装应用信息接口”拉取需要展示的应用信息
            CorpAuthDto corpAuthDto = new CorpAuthDto();
            corpAuthDto.setCorpid(sessionInfo.getCorpId());
            corpAuthDto.setPlatAppId(suiteId);
            Type listType = new TypeToken<OmsResponse<List<AppDto>>>() {
            }.getType();
            List<AppDto> appList = wxworkServerClient.invoke("/corpAuthen/queryAppListInfo", corpAuthDto, listType);

            for (AppDto appDto : appList) {
                String verifyCode = idGenerator.genenateVerifyCode(sessionId, appDto.getAppId());
                appendUrl(appDto, verifyCode);
            }*/
            res = new LoginResponse("0", "ok") ;
            res.setLogSsid(sessionInfo.getSessionId());
            res.setSsKey(""+sessionInfo.getCreateTime());
            res.setUser(userResult);
        }
        catch(Exception e)
        {
            res = new LoginResponse(""+BizError.LOGIN_FAILED.errorCode(), BizError.LOGIN_FAILED.errorMsg() + e.getMessage()) ;
        }

        return res;

    }

    /*
    public LoginResponse loginProvider(LoginRequest req) {
        LoginResponse res ;
        try{
            // 1、参数检查
            //checkArgument(!isNullOrEmpty(code), "code is null");


            // 2、登录态检查（在session复用时有效)
            SessionInfo sessionInfo = sessionService.getSession(req.getLogSsid());
            User userResult = new User();
            if (sessionInfo == null) {
                // 3、拉取suite_access_token，
                String sAccessToken = sessionService.getProviderAccessToken();

                //4.使用code调用getuserinfo3rd接口，获取userticket以及用户信息
                userResult = sessionService.getuserinfo3plat( sAccessToken , req.getCode());

                // 5、生成登录态（userid）
                userResult.setLastPlatAppid("pc");
                userResult.setSessionId(idGenerator.genSessionId());
                // todo ---调用“登记用户信息接口”登记用户登录信息
                //sessionService.updateUser(userResult);

                sessionInfo = new SessionInfo();
                BeanUtils.copyProperties(userResult, sessionInfo);
                sessionService.setSession(sessionInfo);
            }

            res = new LoginResponse("0", "ok") ;
            res.setLogSsid(sessionInfo.getSessionId());
            res.setSsKey(""+sessionInfo.getCreateTime());
            res.setUser(userResult);
        }
        catch(Exception e)
        {
            res = new LoginResponse(""+BizError.LOGIN_FAILED.errorCode(), BizError.LOGIN_FAILED.errorMsg() + e.getMessage()) ;
        }

        return res;

    }
    */

}
