package com.wine.spider.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/25/12 Time: 6:04 PM To change this template use File | Settings |
 * File Templates.
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException,
                                          InstantiationException {
        Document doc = Jsoup.connect("http://list.yesmywine.com/z2-p1").get();

        Elements newsHeadlines = doc.select("li[data-goodsid]");
        Iterator<Element> iterator = newsHeadlines.iterator();
        while (!iterator.hasNext()) {
            Element element = iterator.next();
            System.out.println(element.attr("data-goodsid"));
            Thread.sleep(3000);
        }
    }
}
