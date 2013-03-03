package com.wine.spider.service;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.ListEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/9/12 Time: 10:50 AM To change this template use File | Settings |
 * File Templates.
 */
public interface ListService {

    ListEntity save(ListEntity entity);

    List<ListEntity> list();

    ListEntity get(Long id);

    ListEntity addItem(Long id, ItemEntity itemEntity);

    ListEntity findByUrl(String url);
}
