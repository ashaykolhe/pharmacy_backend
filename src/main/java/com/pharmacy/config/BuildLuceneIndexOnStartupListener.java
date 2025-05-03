package com.pharmacy.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Slf4j
public class BuildLuceneIndexOnStartupListener {

    @PersistenceContext
    private EntityManager entityManager;

    public void index() {

        log.info("Started Initializing Indexes");
        MassIndexer massIndexer = Search.session(entityManager).massIndexer();

        massIndexer.idFetchSize(1000)
                .batchSizeToLoadObjects(250)
                .threadsToLoadObjects(8);

        try {
            massIndexer.startAndWait();
        } catch (InterruptedException e) {
            log.warn("Failed to load data from database");
            Thread.currentThread().interrupt();
        }

        log.info("Completed Indexing");
    }
}