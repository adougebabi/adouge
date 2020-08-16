package com.adouge.secure.config;

import com.adouge.secure.client.ClientDetailsServiceImpl;
import com.adouge.secure.client.IClientDetailsService;
import com.adouge.secure.handler.ISecureHandler;
import com.adouge.secure.props.AuthSecure;
import com.adouge.secure.props.SecureProperties;
import com.adouge.secure.registry.SecureRegistry;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/15 1:33 下午
 */
@Order
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({SecureProperties.class})
public class SecureConfig implements WebMvcConfigurer {

    private final SecureProperties secureProperties;
    private final SecureRegistry secureRegistry;
    private final ISecureHandler secureHandler;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        if (this.secureRegistry.isEnabled() || this.secureProperties.isEnabled()) {
            registry.addInterceptor(this.secureHandler.tokenInterceptor()).excludePathPatterns(this.secureRegistry.getExcludePatterns()).excludePathPatterns(this.secureRegistry.getDefaultExcludePatterns()).excludePathPatterns(this.secureProperties.getSkipUrl());
        }

        if (this.secureRegistry.isAuthEnabled() || this.secureProperties.isAuthEnabled()) {
            List<AuthSecure> authSecures = this.secureRegistry.addAuthPatterns(this.secureProperties.getAuth()).getAuthSecures();
            if (authSecures.size() > 0) {
                registry.addInterceptor(this.secureHandler.authInterceptor(authSecures));
            }
        }

        if (this.secureRegistry.isClientEnabled() || this.secureProperties.isClientEnabled()) {
            this.secureProperties.getClient().forEach((clientSecure) ->
                    registry.addInterceptor(this.secureHandler.clientInterceptor(clientSecure.getClientId())).addPathPatterns(clientSecure.getPathPatterns())
            );
        }
    }

    @Bean
    @ConditionalOnMissingBean(IClientDetailsService.class)
    public IClientDetailsService clientDetailsService() {
        return new ClientDetailsServiceImpl(jdbcTemplate);
    }

}
