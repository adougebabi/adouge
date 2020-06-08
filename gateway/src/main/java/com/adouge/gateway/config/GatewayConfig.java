package com.adouge.gateway.config;


import com.adouge.core.tool.api.Result;
import com.adouge.gateway.sentinel.RestfulUrlCleaner;
import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.WebFluxCallbackManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author : Vinson
 * @date : 2020/5/27 10:53 下午
 */
@Configuration
public class GatewayConfig {
    @Bean
    public void urlCleaner() {
        WebFluxCallbackManager.setUrlCleaner((w, url) -> RestfulUrlCleaner.create(FlowRuleManager.getRules()).clean(url));
        WebFluxCallbackManager.setBlockHandler((serverWebExchange, throwable) -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(Result.fail(429, "请求次数过多，稍后再试"))));
    }
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
