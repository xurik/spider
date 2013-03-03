package com.wine.spider.service;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.SiteEntity;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 13-1-5 Time: 下午11:50 To change this template use File | Settings |
 * File Templates.
 */
public interface SpiderDataService {

    void build(Long id);

    void build(SiteEntity siteEntity);

    void build();
    void build(ItemEntity itemEntity) throws IOException;
}
