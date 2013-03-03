package com.wine.spider.repository.impl;

import com.wine.spider.entity.SiteEntity;
import com.wine.spider.repository.SiteDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 11/26/12 Time: 7:51 PM To change this template use File | Settings |
 * File Templates.
 */
@Repository
public class SiteDaoImpl implements SiteDao {

    @PersistenceContext
    private EntityManager entityManager;

    public SiteEntity save(SiteEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public List<SiteEntity> list() {
        String qlString = "select t from SiteEntity t order by t.id";
        return entityManager.createQuery(qlString).getResultList();
    }

    public List<SiteEntity> enableList() {
        String qlString = "select t from SiteEntity t where status != 'N' order by t.id";
        return entityManager.createQuery(qlString).getResultList();
    }


    public SiteEntity get(Long id) {
        String qlString = "select t from SiteEntity t where t.id = :id";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("id", id);
        return (SiteEntity) query.getSingleResult();
    }

}
