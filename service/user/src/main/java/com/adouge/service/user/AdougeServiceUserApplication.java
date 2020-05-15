package com.adouge.service.user;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class AdougeServiceUserApplication {

//    @Value("${test.a}")
//    String a;

    @Autowired
    Environment env;

    @PostConstruct
    public void init() {
       new Thread(() -> {
           while (true) {
               log.error("a===>{}", env.getProperty("test.a"));
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();
    }

    public static void main(String[] args) {

        SpringApplication.run(AdougeServiceUserApplication.class, args);
//        Logger elkLogger = LoggerFactory.getLogger("elk_logger");
//        elkLogger.info("6666666666");
//        Logger log = LoggerFactory.getLogger("elk_logger");

        log.info("66666666666666666666666666");
        log.info("66666666666666666666666666");
        log.info("66666666666666666666666666");
        log.info("66666666666666666666666666");
        log.info("66666666666666666666666666");
        log.info("66666666666666666666666666");
        log.info("66666666666666666666666666");
        log.info("66666666666666666666666666");
        log.debug("77777777777777777777777777");
        log.warn("88888888888888888888888888");


    }

}
