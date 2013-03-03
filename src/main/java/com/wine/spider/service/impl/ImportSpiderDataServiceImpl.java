package com.wine.spider.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wine.spider.entity.*;
import com.wine.spider.select.Select;
import com.wine.spider.service.*;
import com.wine.spider.util.BeanCopyUtil;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-17 Time: 上午10:00 To change this template use File | Settings |
 * File Templates.
 */
@Service
public class ImportSpiderDataServiceImpl implements ImportSpiderDataService, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ImportSpiderDataServiceImpl.class);
    @Autowired
    private SiteService         siteService;
    @Autowired
    private SearchService       searchService;
    @Autowired
    private ListService         listService;
    @Autowired
    private ItemService         itemService;
    @Autowired
    private WineDataService     wineDataService;
    private ApplicationContext  applicationContext;

    @Override
    public void runAll() {
        List<SiteEntity> list = siteService.enableList();
        if (list == null || list.isEmpty()) {
            return;
        }
        runBySite(list);
    }

    @Override
    public void runBySite(Long id) {
        SiteEntity siteEntity = siteService.get(id);
        if (siteEntity == null) return;
        try {
            runBySearch(siteEntity.getSearchEntityList());
        } catch (Exception e) {
            logger.error("runBySite:", e);
        }
    }

    @Override
    public void runBySite(List<SiteEntity> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (SiteEntity siteEntity : list) {
            try {
                runBySearch(siteEntity.getSearchEntityList());
            } catch (Exception e) {
                logger.error("runBySite:", e);
            }

        }
    }

    @Override
    public void runBySearch(List<SearchEntity> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (SearchEntity searchEntity : list) {
            try {
                runByList(searchEntity.getListEntityList());
            } catch (Exception e) {
                logger.error("runBySearch:", e);
            }

        }
    }

    @Override
    public void runByList(List<ListEntity> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (ListEntity listEntity : list) {
            try {
                runByItem(listEntity.getItemEntityList());
            } catch (Exception e) {
                logger.error("runByList:", e);
            }

        }
    }

    @Override
    public void runByItem(List<ItemEntity> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (ItemEntity itemEntity : list) {
            try {
                run(itemEntity);
            } catch (Exception e) {
                logger.error("runByItem:", e);
            }

        }
    }

    private void run(ItemEntity itemEntity) {
        logger.info("import data:url="+itemEntity.getUrl());
        SearchEntity searchEntity = itemEntity.getListEntity().getSearchEntity();
        String html = itemEntity.getHtml();
        if (StringUtils.isBlank(html)) {
            logger.error("html is null! itemId:{}", itemEntity.getId());
        }
        String selectName = searchEntity.getSelectName();
        Map<String, Select> selectMap = null;
        try {
            selectMap = applicationContext.getBean(selectName, Map.class);
        } catch (Exception e) {
            logger.error("selectMap is null! searchId:{}", searchEntity.getId());
            return;
        }
        Select<WineDataEntity, ItemEntity> select = selectMap.get("data");
        if (select == null) {
            logger.error("selectMap.data is null! searchId:{}", searchEntity.getId());
            return;
        }
        List<WineDataEntity> list = select.execute(Jsoup.parse(html), itemEntity);
        for (WineDataEntity wineDataEntity : list) {
            try {
                wineDataEntity.setUrl(itemEntity.getUrl());
                wineDataEntity.setItemUUID(itemEntity.getUuid());
                wineDataEntity.setStatus("new");
                WineDataEntity old = wineDataService.findByUrl(itemEntity.getUrl());
                if (old != null) {
                    old = BeanCopyUtil.copyWithoutNull(old, wineDataEntity);
                    wineDataService.save(old);
                } else {
                    wineDataService.save(wineDataEntity);
                }
            } catch (Exception e) {
                logger.error("WineDataEntity:", e);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
