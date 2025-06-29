package com.campanha.rpg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpgApplication {

    private static final Logger log = LoggerFactory.getLogger(RpgApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RpgApplication.class, args);
    }
}
