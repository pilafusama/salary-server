package com.tenpay.wxwork.salary.common.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;


public class XmlUtils {

    private static DocumentBuilderFactory documentBuilderFactory;
    private static XPathFactory xPathFactory;

    static {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);

        xPathFactory = XPathFactory.newInstance();
    }

    public static Document newDocument(String source) {
        DocumentBuilder db;
        try {
            db = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        try {
            return db.parse(new InputSource(new StringReader(source)));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Node findNode(Node document, String xpath) {
        try {
            XPathExpression xPathExpression = xPathFactory.newXPath().compile(xpath);
            Node element = (Node) xPathExpression.evaluate(document, XPathConstants.NODE);
            return element;
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findNodeContent(Node document, String xpath) {
        Node node = findNode(document, xpath);
        if (node == null) {
            return "";
        }
        return node.getTextContent();
    }

    public static int findNodeContentInt(Node document, String xpath) {
        Node node = findNode(document, xpath);
        if (node == null) {
            return 0;
        }
        return Integer.parseInt(node.getTextContent());
    }
}
