package com.tenpay.wxwork.wxworklib.service;

import com.tenpay.wxwork.wxworklib.client.WxworkHttpClient;
import com.tenpay.wxwork.wxworklib.utils.UrlGenerator;
import com.tenpay.wxwork.wxworklib.WxworkConfig;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.w3c.dom.Document;

/**
 * 通讯录服务基类
 */
public abstract class AbstractContactBookService {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractContactBookService.class);

    // @Resource
    // protected WxworkHttpClient wxworkHttpClient;
    // @Resource
    // private WxworkConfig wxworkConfig;

    /**
     * 新增用户
     *
     */
    abstract public void createUser(Document document);

    /**
     * 更新用户
     *
     */
    abstract public void updateUser(Document document);

    /**
     * 删除用户
     *
     */
    abstract public void deleteUser(String corpid, String userid);
}
