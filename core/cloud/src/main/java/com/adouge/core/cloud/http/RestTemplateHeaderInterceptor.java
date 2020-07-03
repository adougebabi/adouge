package com.adouge.core.cloud.http;

import com.adouge.core.cloud.hystrix.HttpHeadersContextHolder;
import com.adouge.core.cloud.props.HystrixHeadersProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

import java.io.IOException;

/**
 * http头拦截器，转发到下一个
 * @author : Vinson
 * @date : 2020/6/17 4:57 下午
 */
@AllArgsConstructor
public class RestTemplateHeaderInterceptor implements ClientHttpRequestInterceptor {
    private final HystrixHeadersProperties properties;

    @Override
    @NonNull
    public ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] bytes, @NonNull ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = HttpHeadersContextHolder.get();
        if (headers == null) {
            headers = HttpHeadersContextHolder.toHeaders(properties);
        }
        if (headers != null && !headers.isEmpty()) {
            HttpHeaders httpHeaders = request.getHeaders();
            headers.forEach((key, values) -> values.forEach(value -> httpHeaders.add(key, value)));
        }
        return execution.execute(request, bytes);
    }
}
