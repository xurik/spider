package com.wine.spider.select.spider.yihaodian;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.select.Select;
import com.wine.spider.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-11 Time: 下午11:44 To change this template use File | Settings |
 * File Templates.
 */
@Service
public class ListSelect implements Select<ListEntity, SearchEntity> {

    private final static Logger logger = LoggerFactory.getLogger(ListSelect.class);

    @Override
    public List<ListEntity> execute(Document document, SearchEntity searchEntity) {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
        HtmlPage page = null;
        try {
            page = webClient.getPage(searchEntity.getUrl());
        } catch (IOException e) {
            logger.error("find serach page error!url:"+searchEntity.getUrl(),e);
            return Collections.EMPTY_LIST;
        }
        List<ListEntity> listEntityList = new ArrayList<ListEntity>();
        ListEntity first = new ListEntity();
        first.setHtml(page.getWebResponse().getContentAsString());
        first.setUrl(searchEntity.getUrl());
        return null;
    }

    private HtmlPage pageNext(HtmlPage page) throws IOException {
        DomNode domNode = page.querySelector(".page_next");
        if (domNode == null){
            return null;
        }
        HtmlAnchor htmlAnchor =  (HtmlAnchor)domNode;
        return  htmlAnchor.click();
    }
}
