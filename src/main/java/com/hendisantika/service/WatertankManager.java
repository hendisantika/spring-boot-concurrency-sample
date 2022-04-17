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
}
