package com.gyeom.nettymonolithic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {
    private Logger log = LoggerFactory.getLogger(AppApplication.class.getSimpleName());

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
