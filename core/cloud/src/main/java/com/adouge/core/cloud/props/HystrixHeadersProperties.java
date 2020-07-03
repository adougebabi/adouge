package com.adouge.core.cloud.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/17 4:29 下午
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties("adouge.hystrix.headers")
public class HystrixHeadersProperties {
    /**
     * 用于 聚合层 向调用层传递用户信息 的请求头，默认：X-Adouge-Account
     */
    private String account = "X-Adouge-Account";

    /**
     * RestTemplate 和 Fegin 透传到下层的 Headers 名称表达式
     */
    private String pattern = "Adouge*";

    /**
     * RestTemplate 和 Fegin 透传到下层的 Headers 名称列表
     */
    private List<String> allowed = Arrays.asList("X-Real-IP", "x-forwarded-for", "authorization", "adouge-auth", "Authorization", "Adouge-Auth");

}
