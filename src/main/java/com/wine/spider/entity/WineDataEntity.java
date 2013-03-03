package com.wine.spider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-25 Time: 下午9:51 To change this template use File | Settings |
 * File Templates.
 */
@Entity
@Table(name = "SPIDER_WINE_DATA")
public class WineDataEntity extends BaseEntity {

    /** 爬虫ID */
    @Column(name = "ITEM_UUID", unique = true)
    private String itemUUID;
    /** 被爬页面URL */
    @Column(name = "URL", unique = true)
    private String url;
    /** 状态 */
    @Column(name = "STATUS")
    private String status;
    /** 编码 */
    @Column(name = "CODE")
    private String code;
    /** 备注 */
    @Column(name = "REMARK")
    private String remark;
    /** 葡萄酒名-中文 */
    @Column(name = "FULL_NAME_ZH")
    private String fullNameZh;
    /** 品牌 */
    @Column(name = "BRAND")
    private String brand;
    /** 系列 */
    @Column(name = "SERIES")
    private String series;
    /** 葡萄酒名-英文 */
    @Column(name = "FULL_NAME_EN")
    private String fullNameEn;
    /** 参考年份 */
    @Column(name = "REFERENCE_YEAR")
    private String referenceYear;
    /** 葡萄酒种类 */
    @Column(name = "WINE_KINDS")
    private String wineKinds;
    /** 葡萄品种 */
    @Column(name = "GRAPE_VARIETIES")
    private String grapeVarieties;
    /** 国家 */
    @Column(name = "COUNTRY")
    private String country;
    /** 产区 */
    @Column(name = "REGION")
    private String region;
    /** 酒庄 */
    @Column(name = "WINERY")
    private String winery;
    /** 级别 */
    @Column(name = "RANK")
    private String rank;
    /** 酒精度 */
    @Column(name = "ALCOHOLIC_STRENGTH")
    private String alcoholicStrength;
    /** 规格 */
    @Column(name = "STANDARD")
    private String standard;
    /** 色泽 */
    @Column(name = "COLOR_LABEL")
    private String colorLabel;
    /** 香味、气味 */
    @Column(name = "SMELL_LABEL")
    private String smellLabel;
    /** 味道 */
    @Column(name = "TASTE_LABEL")
    private String tasteLabel;
    /** 口感 */
    @Column(name = "MOUTHFEEL")
    private String mouthfeel;
    /** 其他评语 */
    @Column(name = "OTHER_COMMENT")
    private String otherComment;
    /*** 配菜建议 */
    @Column(name = "COLLOCATION_DISHES")
    private String collocationDishes;
    /** 建议醒酒时间 */
    @Column(name = "SLEEPING_TIME")
    private String sleepingTime;
    /** 最佳品尝温℃ */
    @Column(name = "TASTING_TEMPERATURE")
    private String tastingTemperature;
    /** 适用场合 */
    @Column(name = "OCCASION")
    private String occasion;
    /*** 酒体 */
    @Column(name = "WINE_BODY")
    private String wineBody;

    /** Robert Parker 评分 */
    @Column(name = "ROBERT_PARKER_SCORE")
    private String roertParkerScore;
    /** Decanter评分 */
    @Column(name = "DECANTER_SCORE")
    private String decanterScore;
    /** Jancis Robinson 评分 */
    @Column(name = "JANCIS_ROBINSON_SCORE")
    private String jancisRobinsonScore;
    /** Wine Spectator评分 */
    @Column(name = "WINE_SPECTATOR_SCORE")
    private String wineSpectatorScore;
    /** 其他评分 */
    @Column(name = "OTHOR_SCORE")
    private String othorScore;
    /** 获得奖项 */
    @Column(name = "AWARDS")
    private String awards;
    /** wine100 评分 */
    @Column(name = "WINE100_SCORE")
    private String wine100Score;
    /** Wine100评语 */
    @Column(name = "WINE100_COMMENT")
    private String wine100Comment;
    /** 获得奖项(Wine100) */
    @Column(name = "WINE100_AWARDS")
    private String wine100Awards;
    /** 中国一级经销商 */
    @Column(name = "CHINA_DEALER")
    private String chinaDealer;
    /** 来源1 */
    @Column(name = "SOURCE1")
    private String source1;
    /** 价格1 */
    @Column(name = "PRICE1")
    private String price1;
    /** 地址1 */
    @Column(name = "ADDRESS1")
    private String address1;

    /** 来源2 */
    @Column(name = "SOURCE2")
    private String source2;
    /** 价格2 */
    @Column(name = "PRICE2")
    private String price2;
    /** 地址2 */
    @Column(name = "ADDRESS2")
    private String address2;

    /** 来源3 */
    @Column(name = "SOURCE3")
    private String source3;
    /** 价格3 */
    @Column(name = "PRICE3")
    private String price3;
    /** 地址3 */
    @Column(name = "ADDRESS3")
    private String address3;

    /** 来源4 */
    @Column(name = "SOURCE4")
    private String source4;
    /** 价格4 */
    @Column(name = "PRICE4")
    private String price4;
    /** 地址4 */
    @Column(name = "ADDRESS4")
    private String address4;

    /** 来源5 */
    @Column(name = "SOURCE5")
    private String source5;
    /** 价格5 */
    @Column(name = "PRICE5")
    private String price5;
    /** 地址5 */
    @Column(name = "ADDRESS5")
    private String address5;

    /** 条形码 */
    @Column(name = "BAR_CODE")
    private String barCode;

    /** 编号1 */
    @Column(name = "NUM1")
    private String num1;
    /** 编号2 */
    @Column(name = "NUM2")
    private String num2;
    /** 品酒词 */
    @Column(name = "WINE_TASTING_WORD")
    private String wineTastingWord;
    /** 图片 */
    @Column(name = "WINE_PHOTOS")
    private String winePhotos;
    /**糖分*/
    @Column(name = "SUGAR")
    private String sugar;
    /** 参考零售价 */
    @Column(name = "REFERENCE_RETAIL_PRICE")
    private String referenceRetailPrice;
    /** 促销价 */
    @Column(name = "PROMOTION_PRICE")
    private String promotionPrice;

    public String getItemUUID() {
        return itemUUID;
    }

    public void setItemUUID(String itemUUID) {
        this.itemUUID = itemUUID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFullNameZh() {
        return fullNameZh;
    }

    public void setFullNameZh(String fullNameZh) {
        this.fullNameZh = fullNameZh;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getReferenceYear() {
        return referenceYear;
    }

    public void setReferenceYear(String referenceYear) {
        this.referenceYear = referenceYear;
    }

    public String getWineKinds() {
        return wineKinds;
    }

    public void setWineKinds(String wineKinds) {
        this.wineKinds = wineKinds;
    }

    public String getGrapeVarieties() {
        return grapeVarieties;
    }

    public void setGrapeVarieties(String grapeVarieties) {
        this.grapeVarieties = grapeVarieties;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getWinery() {
        return winery;
    }

    public void setWinery(String winery) {
        this.winery = winery;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAlcoholicStrength() {
        return alcoholicStrength;
    }

    public void setAlcoholicStrength(String alcoholicStrength) {
        this.alcoholicStrength = alcoholicStrength;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getMouthfeel() {
        return mouthfeel;
    }

    public void setMouthfeel(String mouthfeel) {
        this.mouthfeel = mouthfeel;
    }

    public String getOtherComment() {
        return otherComment;
    }

    public void setOtherComment(String otherComment) {
        this.otherComment = otherComment;
    }

    public String getCollocationDishes() {
        return collocationDishes;
    }

    public void setCollocationDishes(String collocationDishes) {
        this.collocationDishes = collocationDishes;
    }

    public String getSleepingTime() {
        return sleepingTime;
    }

    public void setSleepingTime(String sleepingTime) {
        this.sleepingTime = sleepingTime;
    }

    public String getTastingTemperature() {
        return tastingTemperature;
    }

    public void setTastingTemperature(String tastingTemperature) {
        this.tastingTemperature = tastingTemperature;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getWineBody() {
        return wineBody;
    }

    public void setWineBody(String wineBody) {
        this.wineBody = wineBody;
    }

    public String getRoertParkerScore() {
        return roertParkerScore;
    }

    public void setRoertParkerScore(String roertParkerScore) {
        this.roertParkerScore = roertParkerScore;
    }

    public String getDecanterScore() {
        return decanterScore;
    }

    public void setDecanterScore(String decanterScore) {
        this.decanterScore = decanterScore;
    }

    public String getJancisRobinsonScore() {
        return jancisRobinsonScore;
    }

    public void setJancisRobinsonScore(String jancisRobinsonScore) {
        this.jancisRobinsonScore = jancisRobinsonScore;
    }

    public String getWineSpectatorScore() {
        return wineSpectatorScore;
    }

    public void setWineSpectatorScore(String wineSpectatorScore) {
        this.wineSpectatorScore = wineSpectatorScore;
    }

    public String getOthorScore() {
        return othorScore;
    }

    public void setOthorScore(String othorScore) {
        this.othorScore = othorScore;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getWine100Score() {
        return wine100Score;
    }

    public void setWine100Score(String wine100Score) {
        this.wine100Score = wine100Score;
    }

    public String getWine100Comment() {
        return wine100Comment;
    }

    public void setWine100Comment(String wine100Comment) {
        this.wine100Comment = wine100Comment;
    }

    public String getWine100Awards() {
        return wine100Awards;
    }

    public void setWine100Awards(String wine100Awards) {
        this.wine100Awards = wine100Awards;
    }

    public String getChinaDealer() {
        return chinaDealer;
    }

    public void setChinaDealer(String chinaDealer) {
        this.chinaDealer = chinaDealer;
    }

    public String getSource1() {
        return source1;
    }

    public void setSource1(String source1) {
        this.source1 = source1;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getSource2() {
        return source2;
    }

    public void setSource2(String source2) {
        this.source2 = source2;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getSource3() {
        return source3;
    }

    public void setSource3(String source3) {
        this.source3 = source3;
    }

    public String getPrice3() {
        return price3;
    }

    public void setPrice3(String price3) {
        this.price3 = price3;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getSource4() {
        return source4;
    }

    public void setSource4(String source4) {
        this.source4 = source4;
    }

    public String getPrice4() {
        return price4;
    }

    public void setPrice4(String price4) {
        this.price4 = price4;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getSource5() {
        return source5;
    }

    public void setSource5(String source5) {
        this.source5 = source5;
    }

    public String getPrice5() {
        return price5;
    }

    public void setPrice5(String price5) {
        this.price5 = price5;
    }

    public String getAddress5() {
        return address5;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getWineTastingWord() {
        return wineTastingWord;
    }

    public void setWineTastingWord(String wineTastingWord) {
        this.wineTastingWord = wineTastingWord;
    }

    public String getWinePhotos() {
        return winePhotos;
    }

    public void setWinePhotos(String winePhotos) {
        this.winePhotos = winePhotos;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getColorLabel() {
        return colorLabel;
    }

    public void setColorLabel(String colorLabel) {
        this.colorLabel = colorLabel;
    }

    public String getSmellLabel() {
        return smellLabel;
    }

    public void setSmellLabel(String smellLabel) {
        this.smellLabel = smellLabel;
    }

    public String getTasteLabel() {
        return tasteLabel;
    }

    public void setTasteLabel(String tasteLabel) {
        this.tasteLabel = tasteLabel;
    }

    public String getReferenceRetailPrice() {
        return referenceRetailPrice;
    }

    public void setReferenceRetailPrice(String referenceRetailPrice) {
        this.referenceRetailPrice = referenceRetailPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
