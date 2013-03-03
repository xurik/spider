package com.wine.spider.service.impl;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.ListEntity;
import com.wine.spider.repository.ItemDao;
import com.wine.spider.repository.ListDao;
import com.wine.spider.service.ListService;
import com.wine.spider.util.BeanCopyUtil;
import com.wine.spider.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/9/12 Time: 10:51 AM To change this template use File | Settings |
 * File Templates.
 */
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListDao listDao;
    @Autowired
    private ItemDao itemDao;

    @Override
    @Transactional
    public ListEntity save(ListEntity entity) {
        Assert.notNull(entity);
        if (entity.getId() == null) {
            entity.setUuid(UUIDUtil.random());
            entity.setGmtCreate(new Date());
        } else {
            entity = BeanCopyUtil.copyWithoutNull(listDao.get(entity.getId()), entity);
        }
        entity.setGmtModify(new Date());
        listDao.save(entity);
        return entity;
    }

    @Override
    public List<ListEntity> list() {
        return listDao.list();
    }

    @Override
    public ListEntity get(Long id) {
        return listDao.get(id);
    }

    @Override
    @Transactional
    public ListEntity addItem(Long id, ItemEntity itemEntity) {
        if (itemEntity.getId() == null) {
            itemEntity.setUuid(UUIDUtil.random());
            itemEntity.setGmtCreate(new Date());
        } else {
            itemEntity = itemDao.get(itemEntity.getId());
        }
        itemEntity.setGmtModify(new Date());
        ListEntity listEntity = listDao.get(id);
        listEntity.addItemEntityList(itemEntity);
        return listDao.save(listEntity);
    }

    @Override
    public ListEntity findByUrl(String url) {
        return listDao.findByUrl(url);
    }
}
