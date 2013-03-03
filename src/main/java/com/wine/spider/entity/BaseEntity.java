package com.wine.spider.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/25/12 Time: 7:42 PM To change this template use File | Settings |
 * File Templates.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long   id;
    @Column(name = "XUUID", updatable = false, nullable = false, unique = true)
    protected String uuid;
    @Column(name = "GMT_CREATE", updatable = false, nullable = false)
    protected Date   gmtCreate;
    @Column(name = "GMT_MODIFY", nullable = false)
    protected Date   gmtModify;
    @Column(name = "CREATOR")
    protected String creator;
    @Column(name = "MODIFIED")
    protected String modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
