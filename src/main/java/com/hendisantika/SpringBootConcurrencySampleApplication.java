package com.hendisantika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ServletComponentScan
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringBootConcurrencySampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConcurrencySampleApplication.class, args);
    }

}
