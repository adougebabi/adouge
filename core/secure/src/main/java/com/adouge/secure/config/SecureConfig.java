package com.adouge.secure.config;

import com.adouge.secure.client.ClientDetailsServiceImpl;
import com.adouge.secure.client.IClientDetailsService;
import com.adouge.secure.interceptor.ClientInterceptor;
import com.adouge.secure.interceptor.SecureInterceptor;
import com.adouge.secure.props.SecureProperties;
import com.adouge.secure.registry.SecureRegistry;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        secureProperties.getClient().forEach(cs -> registry.addInterceptor(new ClientInterceptor(cs.getClientId())).addPathPatterns(cs.getPathPatterns()));

        if (secureRegistry.isEnabled()) {
            registry.addInterceptor(new SecureInterceptor())
                    .excludePathPatterns(secureRegistry.getExcludePatterns())
                    .excludePathPatterns(secureRegistry.getDefaultExcludePatterns())
                    .excludePathPatterns(secureProperties.getSkipUrl());
        }
    }

    /*@Bean
    public AuthAspect authAspect() {
        return new AuthAspect();
    }
*/
    @Bean
    @ConditionalOnMissingBean(IClientDetailsService.class)
    public IClientDetailsService clientDetailsService() {
        return new ClientDetailsServiceImpl(jdbcTemplate);
    }

}
