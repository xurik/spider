package com.wine.spider.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wine.spider.entity.WineDataEntity;
import com.wine.spider.repository.WineDataDao;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-27 Time: 上午7:17 To change this template use File | Settings |
 * File Templates.
 */
@Repository
public class WineDataDaoImpl implements WineDataDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WineDataEntity save(WineDataEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public WineDataEntity get(Long id) {
        String qlString = "select t from WineDataEntity t where t.id = :id";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("id", id);
        return (WineDataEntity) query.getSingleResult();
    }

    public WineDataEntity findByUrl(String url) {
        String qlString = "select t from WineDataEntity t where t.url = :url";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("url", url);
        List<WineDataEntity> list = query.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
