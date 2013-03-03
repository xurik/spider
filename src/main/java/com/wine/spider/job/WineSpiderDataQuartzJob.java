package com.wine.spider.job;

import com.wine.spider.service.ImportSpiderDataService;
import com.wine.spider.service.SpiderDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryAccessor;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 13-1-5 Time: 下午11:36 To change this template use File | Settings |
 * File Templates.
 */
public class WineSpiderDataQuartzJob  extends EntityManagerFactoryAccessor {

    private static final Logger     logger = LoggerFactory.getLogger(WineSpiderDataQuartzJob.class);
    @Autowired
    private SpiderDataService       spiderDataService;
    public void execute() {
        logger.info("=============start spider job===========");
        EntityManager em = createEntityManager();
        TransactionSynchronizationManager.bindResource(getEntityManagerFactory(), new EntityManagerHolder(em));
        spiderDataService.build();
        EntityManagerHolder emHolder = (EntityManagerHolder)
        TransactionSynchronizationManager.unbindResource(getEntityManagerFactory());
        logger.debug("Closing JPA EntityManager in OpenEntityManagerInViewInterceptor");
        EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
        logger.info("=============end spiderjob===========");
    }

}
