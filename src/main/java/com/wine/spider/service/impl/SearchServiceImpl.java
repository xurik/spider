package com.wine.spider.service.impl;

import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.repository.ListDao;
import com.wine.spider.repository.SearchDao;
import com.wine.spider.service.SearchService;
import com.wine.spider.util.BeanCopyUtil;
import com.wine.spider.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/8/12 Time: 10:25 PM To change this template use File | Settings |
 * File Templates.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;
    @Autowired
    private ListDao   listDao;

    @Override
    @Transactional
    public SearchEntity save(SearchEntity entity) {
        Assert.notNull(entity);
        if (entity.getId() == null) {
            entity.setUuid(UUIDUtil.random());
            entity.setGmtCreate(new Date());
        } else {
            entity = BeanCopyUtil.copyWithoutNull(searchDao.get(entity.getId()), entity);
        }
        entity.setGmtModify(new Date());
        searchDao.save(entity);
        return entity;
    }

    @Override
    public List<SearchEntity> list() {
        return searchDao.list();
    }

    @Override
    public SearchEntity get(Long id) {
        return searchDao.get(id);
    }

    @Override
    @Transactional
    public SearchEntity addList(Long id, ListEntity listEntity) {
        if (listEntity.getId() == null) {
            listEntity.setUuid(UUIDUtil.random());
            listEntity.setGmtCreate(new Date());
        } else {
            listEntity = listDao.get(listEntity.getId());
        }
        listEntity.setGmtModify(new Date());
        SearchEntity searchEntity = searchDao.get(id);
        searchEntity.addListEntityList(listEntity);
        return searchDao.save(searchEntity);
    }

    @Override
    public SearchEntity findByUrl(String url) {
        return searchDao.findByUrl(url);
    }
}
