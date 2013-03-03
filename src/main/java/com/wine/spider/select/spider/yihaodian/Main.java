package com.wine.spider.select.spider.yihaodian;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

/**
 * com.wine.spider.select.spider.yihaodian类说明
 *
 * @author ri.xur 1/30/13 8:34 PM
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
        final HtmlPage page = webClient.getPage("http://www.yihaodian.com/ctg/s2/c25887-%E8%91%A1%E8%90%84%E9%85%92/b0/a-s1-v0-p1-price-d2-f0-m1-rt0-pid-k/");
        DomNode domNode = page.querySelector(".page_next");
        HtmlAnchor htmlAnchor =  (HtmlAnchor)domNode;
        HtmlPage page1 = htmlAnchor.click();
        page1.initialize();
        System.out.println(page1.getDocumentURI());
    }
}
