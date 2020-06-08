package com.adouge.gateway;

import com.adouge.AdougeApplication;
import com.adouge.core.launch.annotation.AdougeSpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@AdougeSpringBootApplication
public class AdougeGatewayApplication {

    public static void main(String[] args) {
        AdougeApplication.run("adouge-gateway", AdougeGatewayApplication.class, args);
    }

}
