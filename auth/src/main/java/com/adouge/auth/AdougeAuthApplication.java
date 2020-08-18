package com.adouge.auth;

import com.adouge.AdougeApplication;
import com.adouge.core.launch.annotation.AdougeSpringBootApplication;
import com.adouge.core.launch.constant.AppConstant;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@AdougeSpringBootApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class AdougeAuthApplication {

    public static void main(String[] args) {
        AdougeApplication.run("auth", AdougeAuthApplication.class, args);
    }

}
