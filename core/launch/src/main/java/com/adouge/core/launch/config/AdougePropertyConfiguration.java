package com.adouge.core.launch.config;

import com.adouge.core.launch.props.PropertySourcePostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author : Vinson
 * @date : 2020/6/12 11:22 上午
 */
@Configuration
@Order(Integer.MIN_VALUE)
public class AdougePropertyConfiguration {
    @Bean
    public PropertySourcePostProcessor propertySourcePostProcessor() {
        return new PropertySourcePostProcessor();
    }
}
