package com.adouge.secure.config;

import com.adouge.secure.handler.IPermissionHandler;
import com.adouge.secure.handler.ISecureHandler;
import com.adouge.secure.handler.impl.PermissionHandler;
import com.adouge.secure.handler.impl.SecureHandlerHandler;
import com.adouge.secure.registry.SecureRegistry;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author : Vinson
 * @date : 2020/6/15 3:19 下午
 */
@Order
@Configuration
@AutoConfigureBefore(SecureConfig.class)
@AllArgsConstructor
public class RegistryConfig {

    private final JdbcTemplate jdbcTemplate;
    @Bean
    @ConditionalOnMissingBean(SecureRegistry.class)
    public SecureRegistry secureRegistry() {
        return new SecureRegistry();
    }

    @Bean
    @ConditionalOnMissingBean({ISecureHandler.class})
    public ISecureHandler secureHandler() {
        return new SecureHandlerHandler();
    }

    @Bean
    @ConditionalOnMissingBean({IPermissionHandler.class})
    public IPermissionHandler permissionHandler() {
        return new PermissionHandler(this.jdbcTemplate);
    }

}
