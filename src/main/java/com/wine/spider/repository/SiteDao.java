package com.wine.spider.repository;

import java.util.List;

import com.wine.spider.entity.SiteEntity;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/26/12 Time: 7:44 PM To change this template use File | Settings |
 * File Templates.
 */
public interface SiteDao {

    SiteEntity save(SiteEntity entity);

    List<SiteEntity> list();
    List<SiteEntity> enableList();

    SiteEntity get(Long id);
}
