package com.wine.spider.service.impl;

import com.wine.spider.entity.SearchEntity;
import com.wine.spider.entity.SiteEntity;
import com.wine.spider.repository.SiteDao;
import com.wine.spider.service.ItemService;
import com.wine.spider.service.ListService;
import com.wine.spider.service.SearchService;
import com.wine.spider.service.SiteService;
import com.wine.spider.util.BeanCopyUtil;
import com.wine.spider.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/27/12 Time: 12:01 AM To change this template use File | Settings |
 * File Templates.
 */
@Service
public class SiteServiceImpl implements SiteService {

    private final static Logger logger = LoggerFactory.getLogger(SiteServiceImpl.class);
    @Autowired
    private SiteDao             siteDao;
    @Autowired
    private SearchService       searchService;
    @Autowired
    private ListService         listService;
    @Autowired
    private ItemService         itemService;

    @Transactional
    @Override
    public SiteEntity save(SiteEntity entity) {
        Assert.notNull(entity);
        if (entity.getId() == null) {
            entity.setUuid(UUIDUtil.random());
            entity.setGmtCreate(new Date());
        } else {
            entity = BeanCopyUtil.copyWithoutNull(siteDao.get(entity.getId()), entity);
        }
        entity.setGmtModify(new Date());
        siteDao.save(entity);
        return entity;
    }

    @Transactional
    public SiteEntity addSearch(Long id, SearchEntity searchEntity) {
        if (searchEntity.getId() == null) {
            searchEntity.setUuid(UUIDUtil.random());
            searchEntity.setGmtCreate(new Date());
        } else {
            searchEntity = BeanCopyUtil.copyWithoutNull(searchService.get(searchEntity.getId()), searchEntity);
        }
        searchEntity.setGmtModify(new Date());
        SiteEntity siteEntity = siteDao.get(id);
        siteEntity.addSearchEntity(searchEntity);
        return siteDao.save(siteEntity);
    }

    @Override
    public List<SiteEntity> list() {
        return siteDao.list();
    }

    @Override
    public List<SiteEntity> enableList() {
        return siteDao.enableList();
    }

    @Override
    public SiteEntity get(Long id) {
        Assert.notNull(id);
        return siteDao.get(id);
    }

}
