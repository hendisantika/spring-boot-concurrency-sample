package com.hendisantika.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-concurrency-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/22
 * Time: 09.27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Watertank {
    private Integer id;
    private double maxCapacity;
    private double currentCapacity;
    private int waterLeakRate;
    private double literOfLeak;
}
