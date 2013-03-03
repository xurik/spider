package com.wine.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/25/12 Time: 8:51 PM To change this template use File | Settings |
 * File Templates.
 */
@Entity
@Table(name = "SPIDER_SEARCH")
public class SearchEntity extends BaseEntity {

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
    @JoinColumn(name = "SITE_ID")
    @JsonBackReference
    private SiteEntity       siteEntity;
    @Column(name = "URL", unique = true)
    private String           url;
    @Column(name = "SUCCESS")
    private Boolean          success;
    @JsonBackReference
    @Column(name = "HTML")
    @Lob
    private String           html;
    @Column(name = "SELECT_NAME")
    private String           selectName;
    @JsonBackReference
    @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "searchEntity")
    private List<ListEntity> listEntityList = new ArrayList<ListEntity>();

    public SiteEntity getSiteEntity() {
        return siteEntity;
    }

    public void setSiteEntity(SiteEntity siteEntity) {
        this.siteEntity = siteEntity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getSelectName() {
        return selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    public List<ListEntity> getListEntityList() {
        return listEntityList;
    }

    public void setListEntityList(List<ListEntity> listEntityList) {
        this.listEntityList = listEntityList;
    }

    public void addListEntityList(ListEntity listEntity) {
        listEntity.setSearchEntity(this);
        listEntityList.add(listEntity);
    }
}
