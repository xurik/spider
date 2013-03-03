package com.wine.spider.service;

import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/8/12 Time: 10:24 PM To change this template use File | Settings |
 * File Templates.
 */
public interface SearchService {

    SearchEntity save(SearchEntity entity);

    List<SearchEntity> list();

    SearchEntity get(Long id);

    SearchEntity addList(Long id, ListEntity listEntity);

    SearchEntity findByUrl(String url);
}
