package com.hendisantika.controller;

import com.hendisantika.service.WatertankManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-concurrency-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/22
 * Time: 09.40
 */
@RestController
public class WatertankController {
    @Autowired
    private WatertankManager watertankManager;
}
