/**
 * 上报监控
 *
 */
package com.tenpay.wxwork.salary.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.service.MonitorLogService;
import com.tenpay.wxwork.salary.util.MsgnoGenerator;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MonitorInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(MonitorInterceptor.class);

    @Autowired
    private MonitorLogService monitorLogService;

    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
    throws Exception {
        // 用于标记请求，有待完善，在 preHandler ... postHandler 之外的处理部分会没有
        String msgno = MsgnoGenerator.genMsgno();
        MDC.put(Constant.REQUEST_ATTR_MSG_NO, msgno);

        String uri = request.getRequestURI().toString();
        uri = uri.replaceFirst("/salary/", "");
        String[] components = uri.split("/");
        String service = components[components.length - 1];
        MDC.put(Constant.REQUEST_ATTR_SERVICE_NAME, service);
        request.setAttribute(Constant.REQUEST_ATTR_SERVICE_NAME, service);

        long startTime = System.currentTimeMillis();
        request.setAttribute(Constant.REQUEST_ATTR_START_TIME, startTime);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
    throws Exception {

        try {
            String retCode = (String)request.getAttribute(Constant.REQUEST_ATTR_RET_CODE);
            if (null == retCode) {
                logger.warn("no retcode attr found in request object");
                return;
            }

            long startTime = (Long)request.getAttribute(Constant.REQUEST_ATTR_START_TIME);
            long endTime = System.currentTimeMillis();
            long executeTime = (endTime - startTime) * 1000;

            String service = (String)request.getAttribute(Constant.REQUEST_ATTR_SERVICE_NAME);
            if (service.length() < 3) {
                logger.debug("service name too short: {}", service);
                return;
            }
        
            String srcIp = request.getRemoteAddr().toString();
            String localIp = request.getLocalAddr().toString();

            monitorLogService.sendAccLog(srcIp, localIp, service, retCode, executeTime);

            MDC.remove(Constant.REQUEST_ATTR_MSG_NO);
            MDC.remove(Constant.REQUEST_ATTR_SERVICE_NAME);
        } catch (Exception e) {
            logger.error("error in afterCompletion: {}", e);
        }
    }
}