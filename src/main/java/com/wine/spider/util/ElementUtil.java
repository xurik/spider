package com.wine.spider.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-29 Time: 下午4:12 To change this template use File | Settings |
 * File Templates.
 */
public class ElementUtil {

    public static String getAttr(Element element, String cssQuery, String attributeKey) {
        Element el = ElementUtil.getElement(element, cssQuery);
        if (el == null) {
            return null;
        }
        return el.attr(attributeKey);
    }

    public static String getText(Element element, String cssQuery) {
        Element el = ElementUtil.getElement(element, cssQuery);
        if (el == null) {
            return null;
        }
        return el.text();
    }

    public static Element getElement(Element element, String cssQuery) {
        Elements elements = element.select(cssQuery);
        if (elements == null || elements.isEmpty()) {
            return null;
        }
        Element el = elements.first();
        if (el == null) {
            return null;
        }
        return el;
    }
}
