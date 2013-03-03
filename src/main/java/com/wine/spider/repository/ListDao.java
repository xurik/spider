package com.wine.spider.repository;

import com.wine.spider.entity.ListEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/9/12 Time: 10:10 AM To change this template use File | Settings |
 * File Templates.
 */
public interface ListDao {

    ListEntity save(ListEntity entity);

    ListEntity get(Long id);

    List<ListEntity> list();

    ListEntity findByUrl(String url);
}
