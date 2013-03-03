package com.wine.spider.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.entity.SiteEntity;
import com.wine.spider.select.Select;
import com.wine.spider.service.*;
import com.wine.spider.util.RandomSleepUtil;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 13-1-5 Time: 下午11:50 To change this template use File | Settings |
 * File Templates.
 */
@Service
public class SpiderDataServiceImpl implements SpiderDataService, ApplicationContextAware {

    private final static Logger     logger = LoggerFactory.getLogger(SpiderDataServiceImpl.class);
    @Autowired
    private SiteService             siteService;
    @Autowired
    private SearchService           searchService;
    @Autowired
    private ListService             listService;
    @Autowired
    private ItemService             itemService;
    @Autowired
    private ImportSpiderDataService importSpiderDataService;
    private ApplicationContext      applicationContext;

    @Override
    public void build(Long id) {
        SiteEntity siteEntity = siteService.get(id);
        build(siteEntity);
    }

    @Override
    public void build(SiteEntity siteEntity) {
        if (siteEntity == null) {
            return;
        }
        List<SearchEntity> searchEntityList = siteEntity.getSearchEntityList();
        if (searchEntityList == null || searchEntityList.isEmpty()) {
            return;
        }
        for (SearchEntity searchEntity : searchEntityList) {
            try {
                build(searchEntity);
            } catch (Exception e) {
                logger.error("error!searchId:{}", searchEntity.getId(), e);
                continue;
            }
        }
    }

    @Override
    public void build() {
        List<SiteEntity> list = siteService.enableList();
        if (list == null || list.isEmpty()) {
            return;
        }
        for (SiteEntity siteEntity : list) {
            build(siteEntity);
        }
    }

    public void build(SearchEntity searchEntity) {
        SiteEntity siteEntity = searchEntity.getSiteEntity();
        String selectName = searchEntity.getSelectName();
        if (StringUtils.isBlank(selectName)) {
            return;
        }
        Map<String, Select> sm = null;
        try {
            sm = applicationContext.getBean(searchEntity.getSelectName(), Map.class);
        } catch (Exception e) {
            logger.error("找不到selectName。searchId:", searchEntity.getId(), e);
            return;
        }
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(searchEntity.getUrl()), 5000);
        } catch (Exception e) {
            logger.error("访问搜索页面失败！searchId:" + searchEntity.getId(), e);
            return;
        }
        searchEntity.setHtml(doc.html());
        searchService.save(searchEntity);
        List<ListEntity> lists = sm.get("list").execute(doc, searchEntity);
        if (lists == null || lists.isEmpty()){
            logger.error("can not find list!url:"+searchEntity.getUrl());
            return;
        }
        List<ListEntity> newList = new ArrayList<ListEntity>(lists.size());
        for (ListEntity listEntity : lists) {
            ListEntity listOld = listService.findByUrl(listEntity.getUrl());
            if (listOld != null) {
                newList.add(listOld);
            }else {
                listEntity = listService.save(listEntity);
                newList.add(listEntity);
            }
        }
        for (ListEntity listEntity : newList) {
            try {
                listEntity.setSearchEntity(searchEntity);
                build(listEntity);
            } catch (Exception e) {
                logger.error("访问列表页面失败！searchId:" + searchEntity.getId(), e);
            }
        }
    }


    public void build(ListEntity listEntity) throws IOException {
        logger.info("spider list:url=" + listEntity.getUrl());
        SearchEntity searchEntity = listEntity.getSearchEntity();
        SiteEntity siteEntity = searchEntity.getSiteEntity();
        Document doc = Jsoup.parse(new URL(listEntity.getUrl()), 5000);
        randomSleep(siteEntity);

        listEntity.setHtml(doc.html());
        listService.save(listEntity);
        Map<String, Select> sm = null;
        try {
            sm = applicationContext.getBean(searchEntity.getSelectName(), Map.class);
        } catch (Exception e) {
            logger.error("找不到selectName。searchId:", searchEntity.getId(), e);
            return;
        }
        List<ItemEntity> items = sm.get("item").execute(doc, listEntity);
        for (ItemEntity itemEntity : items) {
            itemEntity.setListEntity(listEntity);
            try {
                build(itemEntity);
            } catch (Exception e) {
                //由下次迭代来处理
            }

        }
    }

    public void build(ItemEntity itemEntity) {
        logger.info("spider item:url=" + itemEntity.getUrl());
        SearchEntity searchEntity = itemEntity.getListEntity().getSearchEntity();
        SiteEntity siteEntity = searchEntity.getSiteEntity();
        try {
            ItemEntity tmp = itemService.findByUrl(itemEntity.getUrl());
            if (tmp != null) {
                itemEntity = tmp;
            }
            itemEntity.setSuccess(true);
            itemService.save(itemEntity);
            Document doc = Jsoup.parse(new URL(itemEntity.getUrl()), 5000);
            itemEntity.setHtml(doc.html());
            itemService.save(itemEntity);
            List<ItemEntity> list = new ArrayList<ItemEntity>(1);
            list.add(itemEntity);
            importSpiderDataService.runByItem(list);
        } catch (Exception e) {
            itemEntity.setSuccess(false);
            itemService.save(itemEntity);
            throw new RuntimeException(e);
        } finally {
            randomSleep(siteEntity);
        }
    }

    private void randomSleep(SiteEntity siteEntity) {
        Integer rate = siteEntity.getRate();
        if (rate == null || rate == 0) {
            return;
        }
        Integer random = siteEntity.getRandom();
        if (random == null) {
            random = 10;
        }
        RandomSleepUtil.sleep(rate, random);

    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
