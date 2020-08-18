package com.adouge.ops.develop;


import com.adouge.AdougeApplication;
import com.adouge.core.launch.annotation.AdougeSpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author vinson
 */
@EnableDiscoveryClient
@AdougeSpringBootApplication
public class AdougeDevelopApplication {

    public static void main(String[] args) {
//        SpringApplication.run(AdougeDevelopApplication.class, args);
        AdougeApplication.run("develop", AdougeDevelopApplication.class, args);
    }

}
