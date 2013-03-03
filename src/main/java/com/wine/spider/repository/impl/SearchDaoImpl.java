package com.wine.spider.repository.impl;

import com.wine.spider.entity.SearchEntity;
import com.wine.spider.repository.SearchDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/4/12 Time: 3:51 PM To change this template use File | Settings |
 * File Templates.
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SearchEntity save(SearchEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public SearchEntity get(Long id) {
        String qlString = "select t from SearchEntity t where t.id = :id";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("id", id);
        return (SearchEntity) query.getSingleResult();
    }

    public SearchEntity findByUrl(String url) {
        String qlString = "select t from SearchEntity t where t.url = :url";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("url", url);
        List<SearchEntity> list = query.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<SearchEntity> list() {
        String qlString = "select t from SearchEntity t order by t.id";
        return entityManager.createQuery(qlString).getResultList();
    }
}
