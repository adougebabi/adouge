package com.adouge.secure.config;

import com.adouge.secure.registry.SecureRegistry;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author : Vinson
 * @date : 2020/6/15 3:19 下午
 */
@Order
@Configuration
@AutoConfigureBefore(SecureConfig.class)
public class RegistryConfig {

    @Bean
    @ConditionalOnMissingBean(SecureRegistry.class)
    public SecureRegistry secureRegistry() {
        return new SecureRegistry();
    }

}
