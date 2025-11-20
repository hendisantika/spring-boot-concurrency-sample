package com.hendisantika.service;

import com.hendisantika.config.WatertankInMemoryDB;
import com.hendisantika.model.Watertank;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-concurrency-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/22
 * Time: 09.29
 */
@Service
@Slf4j
public class WatertankService {
    private static final Logger loger = LoggerFactory.getLogger(WatertankService.class);

    /**
     * Spring Dependency Injection
     */
    @Autowired
    @Lazy
    private WatertankInMemoryDB watertankInMemoryDB;

    /**
     * Get all Cached waterTanks
     */
    public Map<Integer, Watertank> getAllWatertanks() {
        return watertankInMemoryDB.getWatertanks();
    }

    /**
     * Get a specific Cached waterTanks
     */
    public Watertank getWatertankById(Integer watertankId) {
        synchronized (watertankId) {
            Watertank watertank = watertankInMemoryDB.getWatertanks().get(watertankId);
            if (watertank != null)
                return watertank;
            else
                loger.error("An invalid request for a non existed water-tank with id# {}",watertankId);
            throw new RuntimeException("Coudent find whattenk with id: " + watertankId);
        }
    }
}
