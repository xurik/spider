package com.wine.spider.service;

import java.util.List;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.entity.SiteEntity;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-17 Time: 上午9:57 To change this template use File | Settings |
 * File Templates.
 */
public interface ImportSpiderDataService {

    void runAll();

    void runBySite(Long id);

    void runBySite(List<SiteEntity> list);

    void runBySearch(List<SearchEntity> list);

    void runByList(List<ListEntity> list);

    void runByItem(List<ItemEntity> list);

}
