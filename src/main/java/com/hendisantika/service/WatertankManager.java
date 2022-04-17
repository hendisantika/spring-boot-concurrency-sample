package com.hendisantika.service;

import com.hendisantika.model.Watertank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-concurrency-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/22
 * Time: 09.33
 */
@Service
public class WatertankManager {
    private static final Logger log = LoggerFactory.getLogger(WatertankManager.class);

    /**
     * Spring Dependency Injection
     */
    @Autowired
    private WatertankService watertankServiceStub;

    /**
     * Get All the Water-tanks
     *
     * @return
     */
    public Map<Integer, Watertank> getAllWatertanks() {
        return watertankServiceStub.getAllWatertanks();
    }

    /**
     * Get the maximum capacity amount of a specific water tank container
     *
     * @param watertankId
     * @return {@link Double}
     */
    public double getMaxCapacity(Integer watertankId) {
        return watertankServiceStub.getWatertankById(watertankId).getMaxCapacity();
    }

    /**
     * Get the current capacity amount of a specific water tank container
     *
     * @param watertankId
     * @return {@link Double}
     */
    public double getCurrentCapacity(Integer watertankId) {
        return watertankServiceStub.getWatertankById(watertankId).getCurrentCapacity();
    }
}
