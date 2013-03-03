package com.wine.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/25/12 Time: 8:08 PM To change this template use File | Settings |
 * File Templates.
 */
@Entity
@Table(name = "SPIDER_SITE")
public class SiteEntity extends BaseEntity {

    @Column(name = "NAME")
    private String             name;
    @Column(name = "DOMAIN")
    private String             domain;
    @Column(name = "STATUS")
    private String             status;
    @Column(name = "RATE")
    private Integer            rate;
    @Column(name = "RANDOM")
    private Integer            random;
    @Column(name = "NOTE")
    private String             note;
    @JsonBackReference
    @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "siteEntity")
    // 这里配置关系，并且确定关系维护端和被维护端。mappBy表示关系被维护端，只有关系端有权去更新外键。这里还有注意OneToMany默认的加载方式是赖加载。当看到设置关系中最后一个单词是Many，那么该加载默认为懒加载
    private List<SearchEntity> searchEntityList = new ArrayList<SearchEntity>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getRandom() {
        return random;
    }

    public void setRandom(Integer random) {
        this.random = random;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<SearchEntity> getSearchEntityList() {
        return searchEntityList;
    }

    public void setSearchEntityList(List<SearchEntity> searchEntityList) {
        this.searchEntityList = searchEntityList;
    }

    public void addSearchEntity(SearchEntity searchEntity) {
        searchEntity.setSiteEntity(this); // 用关系维护端来维护关系
        this.searchEntityList.add(searchEntity);
    }
}
