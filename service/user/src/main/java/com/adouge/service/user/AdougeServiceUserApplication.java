package com.adouge.service.user;

import com.adouge.AdougeApplication;
import com.adouge.core.launch.constant.AppConstant;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
//@SpringCloudApplication
@SpringBootApplication(scanBasePackages = "com.adouge")
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class AdougeServiceUserApplication {

    public static void main(String[] args) {
//        SpringApplication.run(AdougeServiceUserApplication.class, args);
        AdougeApplication.run("adouge-service-user", AdougeServiceUserApplication.class, args);
    }

}
