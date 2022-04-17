package com.hendisantika.config;

import com.hendisantika.model.Watertank;
import com.hendisantika.service.WatertankManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-concurrency-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/22
 * Time: 09.30
 */
@Component
@ConfigurationProperties(prefix = "app")
public class WatertankInMemoryDB {
    private static final Logger log = LoggerFactory.getLogger(WatertankInMemoryDB.class);
    private final Set<Watertank> watertanks = new HashSet<>();
    private ConcurrentHashMap<Integer, Watertank> watertanksStorage;

    /**
     * Spring Dependency Injection
     */
    @Autowired
    private WatertankManager watertankManager;


    /**
     * Init method will create all {@link Watertank} instance and will save it in an in-memory {@link Map} cache
     * Will initialize 'create leak operation' in a new thread @see {@link WatertankManager#createALeak(double, long, Integer)}
     * Will initialize 'renew Water Tank operation' in a new thread @see {@link WatertankManager#renewWaterTank()}
     */
    @PostConstruct
    public void init() {
        watertanksStorage = new ConcurrentHashMap<>();
        for (Watertank watertank : watertanks) {
            log.info("Initialized a new Watertank with id: " + watertank.getId());
            watertanksStorage.put(watertank.getId(), watertank);
            watertankManager.createALeak(watertank.getLiterOfLeak(), watertank.getWaterLeakRate(), watertank.getId());
        }
        watertanks = new HashSet<>();
        watertankManager.renewWaterTank() ;
    }
}
