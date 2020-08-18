package com.adouge.gateway;

import com.adouge.AdougeApplication;
import com.adouge.core.launch.annotation.AdougeSpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author : Vinson
 * @date : 2020/6/3 11:25 上午
 */
@EnableDiscoveryClient
@AdougeSpringBootApplication
public class AdougeGatewayApplication {

    public static void main(String[] args) {
        AdougeApplication.run("gateway", AdougeGatewayApplication.class, args);
    }

}
