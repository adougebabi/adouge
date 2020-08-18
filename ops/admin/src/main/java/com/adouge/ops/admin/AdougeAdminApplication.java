package com.adouge.ops.admin;

import com.adouge.AdougeApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author vinson
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class AdougeAdminApplication {

    public static void main(String[] args) {
        AdougeApplication.run("ops-admin", AdougeAdminApplication.class, args);
    }

}
