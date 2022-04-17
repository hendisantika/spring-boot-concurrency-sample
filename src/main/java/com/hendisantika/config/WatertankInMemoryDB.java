package com.hendisantika.config;

import com.hendisantika.model.Watertank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
}
