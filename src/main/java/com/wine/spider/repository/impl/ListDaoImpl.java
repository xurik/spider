package com.wine.spider.repository.impl;

import com.wine.spider.entity.ListEntity;
import com.wine.spider.repository.ListDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/9/12 Time: 10:11 AM To change this template use File | Settings |
 * File Templates.
 */
@Repository
public class ListDaoImpl implements ListDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListEntity save(ListEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public ListEntity get(Long id) {
        String qlString = "select t from ListEntity t where t.id = :id";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("id", id);
        return (ListEntity) query.getSingleResult();
    }

    public ListEntity findByUrl(String url) {
        String qlString = "select t from ListEntity t where t.url = :url";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("url", url);
        List<ListEntity> list = query.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<ListEntity> list() {
        String qlString = "select t from ListEntity t order by t.id";
        return entityManager.createQuery(qlString).getResultList();
    }
}
