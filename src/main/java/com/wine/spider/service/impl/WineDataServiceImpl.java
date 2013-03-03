package com.wine.spider.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.wine.spider.entity.WineDataEntity;
import com.wine.spider.repository.WineDataDao;
import com.wine.spider.service.WineDataService;
import com.wine.spider.util.UUIDUtil;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-27 Time: 上午7:21 To change this template use File | Settings |
 * File Templates.
 */
@Service
public class WineDataServiceImpl implements WineDataService {

    @Autowired
    private WineDataDao wineDataDao;

    @Override
    @Transactional
    public WineDataEntity save(WineDataEntity entity) {
        Assert.notNull(entity);
        if (entity.getId() == null) {
            entity.setUuid(UUIDUtil.random());
            entity.setGmtCreate(new Date());
        }
        entity.setGmtModify(new Date());
        return wineDataDao.save(entity);
    }

    @Override
    public WineDataEntity findByUrl(String url) {
        return wineDataDao.findByUrl(url);
    }
}
