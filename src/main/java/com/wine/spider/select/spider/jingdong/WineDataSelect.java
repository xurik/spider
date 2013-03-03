package com.wine.spider.select.spider.jingdong;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.WineDataEntity;
import com.wine.spider.select.Select;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 1/13/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class WineDataSelect  implements Select<WineDataEntity, ItemEntity> {
    @Override
    public List<WineDataEntity> execute(Document document, ItemEntity itemEntity) {

        return null;
    }
}
