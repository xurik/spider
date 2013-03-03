package com.wine.spider.service;

import com.wine.spider.entity.WineDataEntity;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-27 Time: 上午7:21 To change this template use File | Settings |
 * File Templates.
 */
public interface WineDataService {

    WineDataEntity save(WineDataEntity entity);

    WineDataEntity findByUrl(String url);
}
