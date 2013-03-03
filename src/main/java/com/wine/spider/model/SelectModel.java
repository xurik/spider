package com.wine.spider.model;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-29 Time: 下午3:11 To change this template use File | Settings |
 * File Templates.
 */
public class SelectModel {

    private String  property;
    private String  cssQuery;
    private String  attributeKey;
    private Integer index = 0;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
