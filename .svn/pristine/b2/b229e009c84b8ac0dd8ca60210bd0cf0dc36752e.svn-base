package com.tenpay.wxwork.salary.config;

import com.tenpay.wxwork.salary.common.BizError;
import com.tenpay.wxwork.salary.common.BizException;
import com.tenpay.wxwork.salary.common.utils.Constant;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : treesen
 * @description :
 * @date : 2018/8/23
 */
@Aspect
@Component
public class ControllerAspect {

    private static Logger log = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(* com.tenpay.wxwork.salary.provider..*Controller.*(..) )")
    public void controllerAround() {
    }

    @Around("controllerAround()")
    public Object exceptionHandle(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        try {
            Object proceed = joinPoint.proceed();
            request.setAttribute(Constant.REQUEST_ATTR_RET_CODE, "0");
            return proceed;
        } catch (Throwable throwable) {

            log.error("proceed error in aspect: ", throwable);
            if (throwable instanceof BizException) {
                BizException bizException = (BizException) throwable;
                if(bizException.getRetCode()>=BizError.BANK_ERROR_MES.errorCode()
                        && bizException.getRetCode()<=BizError.DEFAULT_ERROR_MSG.errorCode()
                        && !"".equals(bizException.getErrMsg())){
                    request.setAttribute(Constant.REQUEST_ATTR_RET_CODE, String.valueOf(bizException.getRetCode()));
                    return new FrontEndResponse(String.valueOf(bizException.getRetCode()), bizException.getErrMsg());
                }else {
                    request.setAttribute(Constant.REQUEST_ATTR_RET_CODE, String.valueOf(BizError.DEFAULT_ERROR_MSG.errorCode()));
                    return new FrontEndResponse(String.valueOf(BizError.DEFAULT_ERROR_MSG.errorCode()), BizError.DEFAULT_ERROR_MSG.errorMsg());
                }
            } else {
                request.setAttribute(Constant.REQUEST_ATTR_RET_CODE, String.valueOf(BizError.DEFAULT_ERROR_MSG.errorCode()));
                return new FrontEndResponse(String.valueOf(BizError.DEFAULT_ERROR_MSG.errorCode()), BizError.DEFAULT_ERROR_MSG.errorMsg());
            }
        }
    }
}
