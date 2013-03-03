package com.wine.spider.repository.impl;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.repository.ItemDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/9/12 Time: 10:48 AM To change this template use File | Settings |
 * File Templates.
 */
@Repository
public class ItemDaoImpl implements ItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ItemEntity save(ItemEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public ItemEntity get(Long id) {
        String qlString = "select t from ItemEntity t where t.id = :id";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("id", id);
        return (ItemEntity) query.getSingleResult();
    }

    public ItemEntity findByUrl(String url) {
        String qlString = "select t from ItemEntity t where t.url = :url";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("url", url);
        List<ItemEntity> list = query.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<ItemEntity> failedList() {
        String qlString = "select t from ItemEntity t where success = false and success is not  null order by t.id";
        return entityManager.createQuery(qlString).getResultList();
    }

    public List<ItemEntity> list() {
        String qlString = "select t from ItemEntity t order by t.id";
        return entityManager.createQuery(qlString).getResultList();
    }
}
