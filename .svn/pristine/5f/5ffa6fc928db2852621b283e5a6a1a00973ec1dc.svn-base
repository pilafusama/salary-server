package com.tenpay.wxwork.wxworklib.hanlder;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by yaogangli on 2018/3/3.
 */
@Component
public class InfoHandlerFactory {
    private ConcurrentMap<String, InfoHanlder> handlerMap = new ConcurrentHashMap<>();

    @Resource
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(InfoType.class);
        for (String name : beanNames) {
            InfoType annotation = applicationContext.findAnnotationOnBean(name, InfoType.class);
            handlerMap.put(annotation.type(), applicationContext.getBean(name, InfoHanlder.class));
        }
    }

    public InfoHanlder getInfoHanlder(String infoType) {
        return handlerMap.get(infoType);
    }
}
