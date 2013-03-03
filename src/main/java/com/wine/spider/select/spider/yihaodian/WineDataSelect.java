package com.wine.spider.select.spider.yihaodian;

import com.alibaba.fastjson.JSON;
import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.SiteEntity;
import com.wine.spider.entity.WineDataEntity;
import com.wine.spider.helper.WineryHelper;
import com.wine.spider.select.Select;
import com.wine.spider.service.FileDownLoadService;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-17 Time: 上午10:23 To change this template use File | Settings |
 * File Templates.
 */
public class WineDataSelect implements Select<WineDataEntity, ItemEntity> {

    private static final Logger              logger     = LoggerFactory.getLogger(WineDataSelect.class);
    @Autowired
    @Qualifier("imagePath")
    private String                           imagePath;
    @Autowired
    private FileDownLoadService              fileDownLoadService;

    private static final Map<String, String> detailMap  = new HashMap<String, String>();
    static {
        // 参考年份
        detailMap.put("referenceYear", "year");
        // 葡萄酒种类
        detailMap.put("wineKinds", "zonglei");
        // 葡萄品种
        detailMap.put("grapeVarieties", "pinzhong");
        // 国家
        detailMap.put("country", "changuo");
        // 产区
        detailMap.put("region", "chandi");
        // 级别
        detailMap.put("rank", "jibie");
        // 酒精度
        detailMap.put("alcoholicStrength", "jiujingdu");
        // 规格
        detailMap.put("standard", "rongliang");
        // 色泽
        detailMap.put("colorLabel", "sezhe");
        // 香味
        detailMap.put("smellLabel", "xiangwei");
        // 味道
        detailMap.put("tasteLabel", "xiangwei");

        // 糖分
        detailMap.put("sugar", "tangfen");

        // 口感
        detailMap.put("mouthfeel", "kougan");
        // 配菜建议
        detailMap.put("collocationDishes", "caiyao");
        // 建议醒酒时间
        detailMap.put("sleepingTime", "time");
        // 最佳品尝温
        detailMap.put("tastingTemperature", "pincwendu");
        // 适用场合
        detailMap.put("occasion", "canghe");
        // 酒体
        detailMap.put("wineBody", "jiuti");
    }
    /***
     * 图片属性
     */
    private final static String[]            imageNames = new String[] { "onmousemove", "alt", "src", "original" };

    @Override
    public List<WineDataEntity> execute(Document document, ItemEntity itemEntity) {
        List<WineDataEntity> list = new ArrayList<WineDataEntity>();
        WineDataEntity wineDataEntity = new WineDataEntity();
        // 图片
        Map<String, String> images = getImages(document, itemEntity);
        if (images != null && !images.isEmpty()) {
            wineDataEntity.setWinePhotos(JSON.toJSONString(images));
        }
        // 中英文名和价格
        setFullNameAndPrices(document, wineDataEntity);
        // 酒庄
        if (StringUtils.isNotBlank(wineDataEntity.getFullNameZh())) {
            wineDataEntity.setWinery(WineryHelper.fromName(wineDataEntity.getFullNameZh()));
        }
        // 详情
        setDetails(document, wineDataEntity);
        // 来源
        SiteEntity siteEntity = itemEntity.getListEntity().getSearchEntity().getSiteEntity();
        wineDataEntity.setSource1(siteEntity.getName());
        wineDataEntity.setAddress1(siteEntity.getDomain());
        wineDataEntity.setWineTastingWord(getWineTastingWord(document));
        list.add(wineDataEntity);
        return list;
    }

    private String getWineTastingWord(Document document) {

        String wineTastingWord = "";
        Elements scaytWords = document.select("span[data-scaytid]");
        if (scaytWords == null || scaytWords.isEmpty()){
            return null;
        }
        if (scaytWords != null || !scaytWords.isEmpty()) {
            boolean flag = false;
            for (Element element : scaytWords) {
                if (flag) {
                    wineTastingWord = element.attr("data-scayt_word");
                    if (StringUtils.isNotBlank(wineTastingWord)) {
                        return wineTastingWord;
                    }
                }
                String word = element.attr("data-scayt_word");
                if (StringUtils.equals(word, "酒品介绍")) {
                    flag = true;
                    continue;
                }
                String jpjs = "酒品介绍";
                if (StringUtils.startsWith(word, jpjs) && word.length() > jpjs.length()) {
                    wineTastingWord = word.replace(jpjs, "");
                    if (StringUtils.isNotBlank(wineTastingWord)) {
                        return wineTastingWord;
                    }
                }

                String html = element.html();
                if (StringUtils.contains(html, jpjs) && html.length() > jpjs.length()) {
                    wineTastingWord = html.replace(jpjs, "");
                    if (StringUtils.isNotBlank(wineTastingWord)) {
                        return wineTastingWord;
                    }
                }
            }
        }
        String pinjiuci = document.select("div.proContent div").html();
        if (StringUtils.isNotBlank(pinjiuci)) {
            Pattern pattern = Pattern.compile("<strong>酒品介绍：</strong>(.+?)<br />");
            Matcher matcher = pattern.matcher(pinjiuci);
            if (matcher.find()) {
                wineTastingWord = matcher.group(1);
                if (StringUtils.isNotBlank(wineTastingWord)) {
                    return wineTastingWord;
                }
            }
        }

        return null;
    }

    /**
     * 详情
     * 
     * @param document
     * @param wineDataEntity
     */
    private void setDetails(Document document, WineDataEntity wineDataEntity) {
        Elements elements = document.select("div.xiangqing span");
        if (elements == null || elements.isEmpty()) {
            return;
        }
        Iterator<Element> elementIterator = elements.iterator();
        Map<String, String> valueMap = new HashMap<String, String>();
        while (elementIterator.hasNext()) {
            Element element = elementIterator.next();
            String className = element.className();
            String title = element.attr("title");
            if (StringUtils.isBlank(title)) {
                continue;
            }
            valueMap.put(className, title);
        }
        Set<Map.Entry<String, String>> entrySet = detailMap.entrySet();
        BeanMap beanMap = BeanMap.create(wineDataEntity);
        for (Map.Entry<String, String> entry : entrySet) {
            String propertyName = entry.getKey();
            if (!beanMap.containsKey(propertyName)) {
                continue;
            }
            String value = valueMap.get(entry.getValue());
            if (StringUtils.isNotBlank(value)) {
                beanMap.put(propertyName, value);
            }
        }
    }

    /**
     * 设置中英文名
     * 
     * @param document
     * @param wineDataEntity
     */
    private void setFullNameAndPrices(Document document, WineDataEntity wineDataEntity) {
        Elements elements = document.select("div.promotionMiddleTop");
        if (elements == null || elements.isEmpty()) {
            return;
        }
        Element element = elements.first();
        String zh = element.select("h1").text();
        String en = element.select("span").first().text();
        wineDataEntity.setFullNameZh(zh);
        wineDataEntity.setFullNameEn(en);
        Elements prices = element.select("li");
        if (prices.size() > 0) {
            Element p1 = prices.get(0).select("b").first();
            if (p1 == null) {
                return;
            }
            if (StringUtils.isNotBlank(p1.text())) {
                wineDataEntity.setPromotionPrice(p1.text());
            }

        }
        if (prices.size() > 1) {
            Elements p2 = prices.get(1).select("ins");
            if (p2 == null || p2.isEmpty()) {
                return;
            }
            String pt2 = p2.get(0).text();
            if (StringUtils.isNotBlank(pt2)) {
                wineDataEntity.setPrice1(pt2);
            }
            if (p2.size() > 1) {
                if (StringUtils.isNotBlank(p2.get(1).text())) {
                    wineDataEntity.setReferenceRetailPrice(p2.get(1).text());
                }
            }
        }
    }

    /**
     * 获取图片
     * 
     * @param document
     * @param itemEntity
     * @return
     */
    private Map<String, String> getImages(Document document, ItemEntity itemEntity) {
        Elements elements = document.select("ul#image_list li img");
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
        for (String a : imageNames) {
            String url = element.attr(a);
            if (StringUtils.isBlank(url)) {
                continue;
            }
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
