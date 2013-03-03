package com.wine.spider.select.spider.jingdong;

import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.select.Select;
import com.wine.spider.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 1/13/13
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListSelect implements  Select<ListEntity, SearchEntity> {


    @Override
    public List<ListEntity> execute(Document document, SearchEntity searchEntity) {
        Element pageCountElement = document.select("#top_pagi span").first();
        if (pageCountElement == null){
            return Collections.emptyList();
        }
        String ptext = pageCountElement.text();
        if (StringUtils.isBlank(ptext)){
            return Collections.emptyList();
        }
        String scount = StringUtils.split(ptext,"/")[1];
        Integer count = Integer.valueOf(scount);
        String url = "http://search.360buy.com/s.php?keyword=%C6%CF%CC%D1%BE%C6&area=1&psort=&page=${page}&qr=&qrst=UNEXPAND&et=&sttr=;";
        List<ListEntity> result = new ArrayList<ListEntity>(count);
        Document doc = null;
        for (int i = 1; i <= count; i++) {
            ListEntity listEntity = new ListEntity();
            listEntity.setSearchEntity(searchEntity);
            String u = StringUtils.replace(url,"${page}",i+"");
            listEntity.setUrl(u);
            listEntity.setGmtCreate(new Date());
            listEntity.setGmtModify(new Date());
            listEntity.setUuid(UUIDUtil.random());
            result.add(listEntity);
        }
        return result;
    }
}
