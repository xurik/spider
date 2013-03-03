package com.wine.spider.service;

import com.wine.spider.entity.ItemEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-15 Time: 下午5:22 To change this template use File | Settings |
 * File Templates.
 */
public interface ItemService {

    ItemEntity save(ItemEntity entity);

    ItemEntity findByUrl(String url);

    List<ItemEntity> failedList();
}
