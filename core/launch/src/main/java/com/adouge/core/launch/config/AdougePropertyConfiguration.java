package com.adouge.core.launch.config;

import com.adouge.core.launch.props.PropertySourcePostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author : Vinson
 * @date : 2020/6/12 11:22 上午
 */
@Configuration
@Order(Integer.MIN_VALUE)
//@EnableConfigurationProperties({BladeProperties.class})
public class AdougePropertyConfiguration {
    @Bean
    public PropertySourcePostProcessor bladePropertySourcePostProcessor() {
        return new PropertySourcePostProcessor();
    }
}
