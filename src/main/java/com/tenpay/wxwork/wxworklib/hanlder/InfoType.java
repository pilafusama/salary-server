package com.tenpay.wxwork.wxworklib.hanlder;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * https://work.weixin.qq.com/api/doc#10982 InfoType 定义
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface InfoType {

    /**
     * 取值参考 https://work.weixin.qq.com/api/doc#10982 XML 报文中的 InfoType
     *
     */
    String type();

    /**
     * spring bean的名称 默认为空则为Service类的类名
     *
     * @return
     */
    String value() default "";
}
