package com.tenpay.wxwork.wxworklib.hanlder;

import org.w3c.dom.Document;

import static com.tenpay.wxwork.wxworklib.utils.XmlUtils.findNodeContent;

/**
 * Created by yaogangli on 2018/3/3.
 */
abstract public class AbstractInfoHandler implements InfoHanlder {

    protected String getSuiteId(Document document) {
        return findNodeContent(document, "/xml/SuiteId");
    }

}
