package com.hendisantika.service;

import com.hendisantika.config.WatertankInMemoryDB;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private WatertankInMemoryDB watertankInMemoryDB;

}
