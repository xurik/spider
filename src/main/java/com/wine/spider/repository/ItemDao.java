package com.wine.spider.repository;

import com.wine.spider.entity.ItemEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/9/12 Time: 10:48 AM To change this template use File | Settings |
 * File Templates.
 */
public interface ItemDao {

    ItemEntity save(ItemEntity entity);

    ItemEntity get(Long id);

    List<ItemEntity> list();

    ItemEntity findByUrl(String url);

    List<ItemEntity> failedList();
}
