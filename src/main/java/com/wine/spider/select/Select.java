package com.wine.spider.select;

import java.util.List;

import org.jsoup.nodes.Document;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-11 Time: 下午11:14 To change this template use File | Settings |
 * File Templates.
 */
public interface Select<T, X> {

    List<T> execute(Document document, X x);
}
