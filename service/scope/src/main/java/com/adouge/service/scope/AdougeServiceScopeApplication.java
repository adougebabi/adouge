package com.adouge.service.scope;

import com.adouge.AdougeApplication;
import com.adouge.core.launch.annotation.AdougeSpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@AdougeSpringBootApplication
public class AdougeServiceScopeApplication {

    public static void main(String[] args) {
        AdougeApplication.run("adouge-service-scope", AdougeServiceScopeApplication.class, args);
    }

}
