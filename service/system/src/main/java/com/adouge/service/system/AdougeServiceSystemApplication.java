package com.adouge.service.system;

import com.adouge.AdougeApplication;
import com.adouge.core.launch.annotation.AdougeSpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@AdougeSpringBootApplication
public class AdougeServiceSystemApplication {

    public static void main(String[] args) {
        AdougeApplication.run("service-system", AdougeServiceSystemApplication.class, args);
    }

}
