package com.adouge.core.cloud.feign;

import feign.Target;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author : Vinson
 * @date : 2020/7/3 9:09 上午
 */
@Slf4j
@AllArgsConstructor
public class CustomFallbackFactory<T> implements FallbackFactory<T> {

    private final Target<T> target;

    @Override
    @SuppressWarnings("unchecked")
    public T create(Throwable cause) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetType);
        enhancer.setUseCache(true);
        enhancer.setCallback(new CustomFallback<>(targetType, targetName, cause));
        return (T) enhancer.create();
    }
}
