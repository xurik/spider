package com.wine.spider.select.spider.yemaijiu;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.ListEntity;
import com.wine.spider.select.Select;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-12 Time: 上午12:47 To change this template use File | Settings |
 * File Templates.
 */
public class ItemSelect implements Select<ItemEntity, ListEntity> {

    private final static Logger logger = LoggerFactory.getLogger(ItemSelect.class);

    @Override
    public List<ItemEntity> execute(Document document, ListEntity listEntity) {
        Elements pnames = document.select("a.pname");
        if (pnames == null || pnames.isEmpty()) {
            if (logger.isWarnEnabled()) {
                logger.warn("ItemSelect.execute:找不到商品！listId:" + listEntity.getId());
            }
            return Collections.EMPTY_LIST;
        }
        List<ItemEntity> result = new ArrayList<ItemEntity>();
        Iterator<Element> iterator = pnames.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setListEntity(listEntity);
            itemEntity.setUrl(element.attr("href"));
            itemEntity.setName(element.attr("title"));
            result.add(itemEntity);
        }
        return result;
    }
}
