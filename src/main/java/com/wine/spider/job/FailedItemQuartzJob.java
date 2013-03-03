package com.wine.spider.job;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.service.ItemService;
import com.wine.spider.service.SpiderDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryAccessor;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

/**
 * com.wine.spider.job类说明
 *
 * @author ri.xur 1/29/13 9:30 PM
 */
public class FailedItemQuartzJob extends EntityManagerFactoryAccessor {
    private static final Logger logger = LoggerFactory.getLogger(FailedItemQuartzJob.class);
    @Autowired
    private ItemService itemService;
    @Autowired
    private SpiderDataService spiderDataService;
    public void execute() {
        logger.info("=============start spider job===========");
        EntityManager em = createEntityManager();
        TransactionSynchronizationManager.bindResource(getEntityManagerFactory(), new EntityManagerHolder(em));
        List<ItemEntity> itemEntityList = itemService.failedList();
        if (itemEntityList == null || itemEntityList.isEmpty()){
            for (ItemEntity itemEntity : itemEntityList){
                try {
                    spiderDataService.build(itemEntity);
                } catch (IOException e) {
                    logger.error("item error!",e);
                }
            }
        }
        EntityManagerHolder emHolder = (EntityManagerHolder)
                TransactionSynchronizationManager.unbindResource(getEntityManagerFactory());
        logger.debug("Closing JPA EntityManager in OpenEntityManagerInViewInterceptor");
        EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
        logger.info("=============end spiderjob===========");
    }
}
