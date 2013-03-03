package com.wine.spider.repository;

import com.wine.spider.entity.WineDataEntity;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-27 Time: 上午7:17 To change this template use File | Settings |
 * File Templates.
 */
public interface WineDataDao {

    WineDataEntity save(WineDataEntity entity);

    WineDataEntity get(Long id);

    WineDataEntity findByUrl(String url);
}
