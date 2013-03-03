package com.wine.spider.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/25/12 Time: 8:51 PM To change this template use File | Settings |
 * File Templates.
 */
@Entity
@Table(name = "SPIDER_ITEM")
public class ItemEntity extends BaseEntity {

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
    @JoinColumn(name = "LIST_ID")
    @JsonBackReference
    private ListEntity listEntity;
    @Column(name = "NAME")
    private String     name;
    @Column(name = "URL")
    private String     url;
    @Column(name = "SUCCESS")
    private Boolean    success;
    @JsonBackReference
    @Column(name = "HTML")
    @Lob
    private String     html;

    public ListEntity getListEntity() {
        return listEntity;
    }

    public void setListEntity(ListEntity listEntity) {
        this.listEntity = listEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
