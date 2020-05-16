package com.adouge.service.user;

import com.adouge.AdougeApplication;
import com.adouge.core.launch.constant.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
//@SpringCloudApplication
@SpringBootApplication(scanBasePackages = "com.adouge")
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class AdougeServiceUserApplication {

    public static void main(String[] args) {
//        SpringApplication.run(AdougeServiceUserApplication.class, args);
        AdougeApplication.run("adouge-service-user",AdougeServiceUserApplication.class,args);
    }

}
