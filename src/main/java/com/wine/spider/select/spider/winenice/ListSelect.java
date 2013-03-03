package com.wine.spider.select.spider.winenice;

import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.select.Select;
import com.wine.spider.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 1/13/13
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListSelect  implements Select<ListEntity, SearchEntity> {
    private final static Logger logger = LoggerFactory.getLogger(ListSelect.class);

    @Override
    public List<ListEntity> execute(Document document, SearchEntity searchEntity) {
        Elements elements = document.select("div.page a");
        if (elements == null || elements.isEmpty()){
            logger.error("get page error!searchId=",searchEntity.getId());
            return Collections.EMPTY_LIST;
        }
        Integer count = 0;
        Integer index = 0;
        for (int i=0;i<elements.size();i++){
            Element element = elements.get(i);
            if (StringUtils.equals("下一页",element.text().trim())){
                index = i-1;
                break;
            }
        }
        count = Integer.valueOf(elements.get(index).text());
        String totalCount = document.select("li.liw100").select("span").text();
        String url = "http://www.winenice.com" + "/product/pro_list_-1-0-0-0-0-0-0-0-0-0-0-1-${page}.shtml?totalCount="+totalCount;
        List<ListEntity> result = new ArrayList<ListEntity>();
        for (int i=1;i<=count;i++){
            String u = StringUtils.replace(url,"${page}",i+"");
            ListEntity listEntity = new ListEntity();
            listEntity.setSearchEntity(searchEntity);
            listEntity.setUrl(u);
            listEntity.setGmtCreate(new Date());
            listEntity.setGmtModify(new Date());
            listEntity.setUuid(UUIDUtil.random());
            result.add(listEntity);
        }
        return result;
    }
}
