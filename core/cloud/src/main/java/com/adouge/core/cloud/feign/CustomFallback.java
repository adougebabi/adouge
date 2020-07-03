package com.adouge.core.cloud.feign;

import cn.hutool.core.util.ObjectUtil;
import com.adouge.core.tool.api.Result;
import com.adouge.core.tool.api.ResultCode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * @author : Vinson
 * @date : 2020/7/3 9:11 上午
 */
@AllArgsConstructor
@Slf4j
public class CustomFallback<T> implements MethodInterceptor {
    private final Class<T> targetType;
    private final String targetName;
    private final Throwable cause;
    private final String CODE = "code";

    @Nullable
    @SneakyThrows
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
        String errorMessage = cause.getMessage();
        log.error("CustomFeignFallback:[{}.{}] serviceId:[{}] message:[{}]", targetType.getName(), method.getName(), targetName, errorMessage);
        Class<?> returnType = method.getReturnType();
        // 暂时不支持 flux，rx，异步等，返回值不是 R，直接返回 null。
        if (Result.class != returnType) {
            return null;
        }
        // 非 FeignException
        if (!(cause instanceof FeignException)) {
            return Result.fail(ResultCode.INTERNAL_SERVER_ERROR, errorMessage);
        }
        FeignException exception = (FeignException) cause;
        String content = exception.contentUTF8();
        // 如果返回的数据为空
        if (ObjectUtil.isEmpty(content)) {
            return Result.fail(ResultCode.INTERNAL_SERVER_ERROR, errorMessage);
        }
        JSONObject jsonObject = JSON.parseObject(content);
        // 判断是否 R 格式 返回体
        if (jsonObject.containsKey(CODE)) {
            return Result.data(jsonObject.getInteger("code"),jsonObject.get("data"),jsonObject.getString("msg"));
        }
        return Result.fail(jsonObject.toString());
    }
}
