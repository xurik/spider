package com.wine.spider.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/26/12 Time: 12:53 AM To change this template use File | Settings |
 * File Templates.
 */
@Entity
@Table(name = "SPIDER_LIST")
public class ListEntity extends BaseEntity {

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
    @JoinColumn(name = "SEARCH_ID")
    @JsonBackReference
    private SearchEntity     searchEntity;
    @Column(name = "URL", unique = true)
    private String           url;
    @Column(name = "SUCCESS")
    private Boolean          success;
    @JsonBackReference
    @Column(name = "HTML")
    @Lob
    private String           html;
    @JsonBackReference
    @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "listEntity")
    private List<ItemEntity> itemEntityList = new ArrayList<ItemEntity>();

    public SearchEntity getSearchEntity() {
        return searchEntity;
    }

    public void setSearchEntity(SearchEntity searchEntity) {
        this.searchEntity = searchEntity;
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

    public List<ItemEntity> getItemEntityList() {
        return itemEntityList;
    }

    public void setItemEntityList(List<ItemEntity> itemEntityList) {
        this.itemEntityList = itemEntityList;
    }

    public void addItemEntityList(ItemEntity itemEntity) {
        itemEntity.setListEntity(this);
        itemEntityList.add(itemEntity);
    }
}
