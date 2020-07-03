package com.adouge.boot.config;

import com.adouge.boot.resolver.ArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/15 4:02 下午
 */
@Slf4j
@Configuration
@EnableCaching
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ArgumentResolver());
    }
}
