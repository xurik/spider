package com.wine.spider.service;

import java.util.List;

import com.wine.spider.entity.SearchEntity;
import com.wine.spider.entity.SiteEntity;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/27/12 Time: 12:00 AM To change this template use File | Settings |
 * File Templates.
 */
public interface SiteService {

    SiteEntity save(SiteEntity entity);

    List<SiteEntity> list();
    List<SiteEntity> enableList();
    SiteEntity get(Long id);

    SiteEntity addSearch(Long id, SearchEntity searchEntity);

}
