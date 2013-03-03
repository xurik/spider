package com.wine.spider.select.spider.winenice;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.wine.spider.service.FileDownLoadService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.WineDataEntity;
import com.wine.spider.select.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 1/13/13 Time: 4:18 PM To change this template use File | Settings |
 * File Templates.
 */
public class WineDataSelect implements Select<WineDataEntity, ItemEntity> {
    private static final Logger logger     = LoggerFactory.getLogger(WineDataSelect.class);
    private static final Pattern imagePattern = Pattern.compile("'(.+?)'");
    @Autowired
    @Qualifier("imagePath")
    private String                           imagePath;
    @Autowired
    private FileDownLoadService fileDownLoadService;

    @Override
    public List<WineDataEntity> execute(Document document, ItemEntity itemEntity) {
        List<WineDataEntity> list = new ArrayList<WineDataEntity>();
        WineDataEntity wineDataEntity = new WineDataEntity();
        wineDataEntity.setWineKinds("红葡萄酒");
        wineDataEntity.setSource1("美酒网");
        wineDataEntity.setAddress1("http://www.winenice.com/");
        detail(wineDataEntity, document);
        price(wineDataEntity, document);
        wineDataEntity.setWinePhotos(JSON.toJSONString(getImages(document, itemEntity)));
        moren(wineDataEntity, document);
        wineTastingWord(wineDataEntity, document);

        name(wineDataEntity, document);
        list.add(wineDataEntity);
        return list;
    }

    private void wineKinds(WineDataEntity wineDataEntity, Document document){
        Elements elements = document.select("div.Status a");
        if (elements ==null || elements.isEmpty()){
            return ;
        }
        String wineKinds = null;
        if (elements.size() >3){
            wineKinds = elements.get(2).text();
            wineDataEntity.setWineKinds(wineKinds);
        }
    }
    //品酒词
    private void wineTastingWord(WineDataEntity wineDataEntity, Document document){
        Element element = document.select("p.ltext").first();
        if (element == null){
            return;
        }
        String wineTastingWord   =element.text().trim();
        if (StringUtils.isNotBlank(wineTastingWord)){
            wineDataEntity.setWineTastingWord(wineTastingWord);
        }
    }

    private void price(WineDataEntity wineDataEntity, Document document){
        Element element = document.select("div.cart_li").first();
        Elements scjs = element.select("div.scj");
        if (scjs != null && !scjs.isEmpty()){
            Iterator<Element> iterator = scjs.iterator();
            while (iterator.hasNext()){
                Element e = iterator.next();
                if (StringUtils.contains(e.text(),"酒美价")){
                    Element e1 = e.select("del").first();
                    if (e1 != null){
                        String p1 = e1.text().trim().replace("￥","");
                        wineDataEntity.setPrice1(p1);
                    }
                    continue;
                }
                if (StringUtils.contains(e.text(),"市场价")){
                    Element e1 = e.select("del").first();
                    if (e1 != null){
                        String p1 = e1.text().trim().replace("￥","");;
                        wineDataEntity.setReferenceRetailPrice(p1.trim());
                    }
                    continue;
                }
            }
        }
        Element hyj =  element.select("div.hyj").first();
        if (hyj != null){
            String promotionPrice = hyj.select("font").text().trim().replace("￥","")+".00";
            if (StringUtils.contains(hyj.text(),"酒美价")){
                wineDataEntity.setPrice1(promotionPrice);
            } else {
                wineDataEntity.setPromotionPrice(promotionPrice);
            }


        }
    }

    private void moren(WineDataEntity wineDataEntity, Document document){
        Elements morens = document.select("a.moren");
        if (morens == null || morens.isEmpty()){
            return;
        }
        if (morens.size() >=3){
            String standard = morens.get(2).text();
            wineDataEntity.setStandard(standard);
        }
    }

    private void name(WineDataEntity wineDataEntity, Document document){
        Element element = document.select("div.tle_li").first();
        if (element == null){
            return;
        }
        Element zh = element.select("h1").first();
        Element en = element.select("h2").first();
        if(zh != null){
            wineDataEntity.setFullNameZh(zh.text().trim());
        }
        if(en != null){
            wineDataEntity.setFullNameEn(en.text().trim());
        }
    }

    private void detail(WineDataEntity wineDataEntity, Document document) {
        Elements elements = document.select("div.detail_list li");
        if (elements == null || elements.isEmpty()) {
            return;
        }
        Iterator<Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            try{
                Element element = iterator.next();
                String className = element.className();
                // 酒庄/品牌
                if (StringUtils.equals(className, "Chateau")) {
                    Element first =   element.select("a").first();
                    if (first == null){
                        continue;
                    }
                    String value = first.text();
                    wineDataEntity.setWinery(value);
                    continue;
                }
                //所属产地
                if (StringUtils.equals(className, "site")) {
                    Elements values = element.select("a");
                    if (values == null || values.isEmpty()){
                        continue;
                    }
                    if (values.size()>0){
                        wineDataEntity.setCountry(values.get(0).text());
                    }
                    if (values.size() == 1){
                        continue;
                    }
                    for (int i=1;i<values.size();i++){
                        wineDataEntity.setRegion(values.get(i).text()+"/");
                    }
                    continue;
                }
                //等级
                if (StringUtils.equals(className, "grade_l")) {
                    Element  first = element.select("a").first();
                    if (first == null){
                        continue;
                    }
                    String value = first.text();
                    wineDataEntity.setRank(value);
                    continue;
                }
                //年份
                if (StringUtils.equals(className, "year")) {
                    String value = getDetailSpanValue(element);
                    wineDataEntity.setReferenceYear(value.replaceAll("NV",""));
                    continue;
                }
                //酒精度
                if (StringUtils.equals(className, "alcohol")) {
                    String value = getDetailSpanValue(element);
                    value = StringUtils.replace(value,"Vol","");
                    wineDataEntity.setAlcoholicStrength(value);
                    continue;
                }
                //葡萄品种
                if (StringUtils.equals(className, "grape")) {
                    String value = getDetailSpanValue(element);
                    wineDataEntity.setAlcoholicStrength(value);
                    continue;
                }
                //色泽
                if (StringUtils.equals(className, "Color")) {
                    String value = getDetailSpanValue(element);
                    wineDataEntity.setColorLabel(value);
                    continue;
                }
                //香气
                if (StringUtils.equals(className, "Aroma")) {
                    String value = getDetailSpanValue(element);
                    wineDataEntity.setSmellLabel(value);
                    continue;
                }
                //口感
                if (StringUtils.equals(className, "Taste")) {
                    String value = getDetailSpanValue(element);
                    wineDataEntity.setMouthfeel(value);
                    continue;
                }
                //酒体
                if (StringUtils.equals(className, "Taste")) {
                    String value = getDetailSpanValue(element);
                    wineDataEntity.setWineBody(value);
                    continue;
                }
                //搭配美食
                if (StringUtils.equals(className, "Collocation")) {
                    String value = getDetailSpanValue(element);
                    wineDataEntity.setCollocationDishes(value);
                    continue;
                }
                //获奖荣誉
                if (StringUtils.equals(className, "Awards")) {
                    String value = getDetailSpanValue(element);
                    wineDataEntity.setAwards(value);
                    continue;
                }
                //适用场合
                if (StringUtils.equals(className, "Occasion")) {
                    String value = getDetailSpanValue(element);
                    wineDataEntity.setOccasion(value);
                    continue;
                }
            } catch (Exception e){
                logger.error("sinenice detail error!",e);
            }
        }
    }

    private String getDetailSpanValue(Element element){
        Elements elements = element.select("span");
        if (elements == null || elements.isEmpty()){
            return null;
        }
        Element e = elements.first();
        String value = e.attr("title");
        if (StringUtils.isBlank(value)){
            value = e.text();
        }
        return value;
    }

    /**
     * 获取图片
     *
     * @param document
     * @param itemEntity
     * @return
     */
    private Map<String, String> getImages(Document document, ItemEntity itemEntity) {
        Elements elements = document.select("div.img_list img");
        Iterator<Element> images = elements.iterator();
        List<String> list = new ArrayList<String>();
        while (images.hasNext()) {
            Element element = images.next();
            downImage(element, itemEntity.getUuid(), list);
        }
        Map<String, String> result = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            result.put("img" + (i + 1), list.get(i));
        }
        return result;
    }

    /**
     * 下载图片
     *
     * @param element
     * @param uuid
     * @param list
     * @return
     */
    private List<String> downImage(Element element, String uuid, List<String> list) {
        Set<String> urlSet = new HashSet<String>();
        urlSet.add(element.attr("src"));
        String onmousemove = element.attr("onmousemove");
        Matcher matcher = imagePattern.matcher(onmousemove);
        String domain = "http://www.wineimg.com";
        while (matcher.find()){
            String u = matcher.group(1);
            urlSet.add(domain+u);
        }

        for (String url : urlSet) {
            String fileName = "";
            try {
                fileName = url.substring(url.lastIndexOf("/") + 1);
            } catch (Exception e) {
                logger.error("url error!url:{}", url);
                continue;
            }

            fileName = "wine" + "/" + uuid + "/" + fileName;
            list.add(fileName);
            if (!imagePath.endsWith("/")) {
                imagePath = imagePath + "/";
            }
            fileDownLoadService.downLoad(imagePath + fileName, url);
        }
        return list;
    }
}
