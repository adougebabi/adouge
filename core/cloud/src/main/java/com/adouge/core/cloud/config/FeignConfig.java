package com.adouge.core.cloud.config;

import com.adouge.core.cloud.feign.FeignRequestHeaderInterceptor;
import com.adouge.core.cloud.http.RestTemplateHeaderInterceptor;
import com.adouge.core.cloud.props.HystrixHeadersProperties;
import com.alibaba.cloud.sentinel.feign.CustomSentinelFeign;
import com.alibaba.csp.sentinel.SphU;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import feign.Feign;
import feign.RequestInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/17 4:43 下午
 */
@Configuration
@EnableConfigurationProperties(HystrixHeadersProperties.class)
@AllArgsConstructor
public class FeignConfig {
    private final ObjectMapper objectMapper;

    @Bean
    public RestTemplateHeaderInterceptor requestHeaderInterceptor(HystrixHeadersProperties properties) {
        return new RestTemplateHeaderInterceptor(properties);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestHeaderInterceptor();
    }

    /*
     */

    /**
     * 负载均衡的RestTemplate
     *
     * @param interceptor 头拦截器
     * @return RestTemplate
     *//*

    @Bean
    @LoadBalanced
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate lbRestTemplate(RestTemplateHeaderInterceptor interceptor) {
        RestTemplate lbRestTemplate = new RestTemplate();
        lbRestTemplate.setInterceptors(Collections.singletonList(interceptor));
        configMessageConverters(lbRestTemplate.getMessageConverters());
        return lbRestTemplate;
    }
*/
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateHeaderInterceptor interceptor) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(interceptor));
        return restTemplate;
    }

    private void configMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(x -> x instanceof StringHttpMessageConverter || x instanceof MappingJackson2HttpMessageConverter);
        converters.add(new StringHttpMessageConverter(Charsets.UTF_8));
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }
    @Bean
    @Scope("prototype")
    @ConditionalOnClass({SphU.class, Feign.class})
    @ConditionalOnProperty(name = "feign.sentinel.enabled")
    @Primary
    public Feign.Builder feignSentinelBuilder() {
        return CustomSentinelFeign.builder();
    }
}
