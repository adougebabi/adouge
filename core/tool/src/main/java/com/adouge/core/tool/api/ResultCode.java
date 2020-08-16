package com.adouge.core.tool.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author : Vinson
 * @date : 2020/5/15 10:18 上午
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {
    /**
     * 200 操作成功
     */
    SUCCESS(HttpServletResponse.SC_OK, "操作成功"),

    /**
     * 400 业务异常
     */
    FAILURE(HttpServletResponse.SC_BAD_REQUEST, "业务异常"),

    /**
     * 401 请求未授权
     */
    UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "请求未授权"),

    /**
     * 404 没找到请求
     */
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "没找到请求"),
    /**
     * 504 网关未能及时响应
     */
    GATEWAY_TIMEOUT(HttpServletResponse.SC_GATEWAY_TIMEOUT, "网关服务请求超时"),
    /**
     * 503 服务暂时不可用
     */
    SERVICE_UNAVAILABLE(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "服务暂时不可用"),
    /**
     * 500 服务器内部异常
     */
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器内部异常");


    final int code;
    final String message;

    public static ResultCode valueOf(HttpStatus status) {
        return valueOf(status.value());
    }

    public static ResultCode valueOf(int statusCode) {
        ResultCode series = resolve(statusCode);
        if (series == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        } else {
            return series;
        }
    }

    public static ResultCode resolve(int statusCode) {
        ResultCode[] temp = values();
        return Arrays.stream(temp).filter(s -> s.getCode() == statusCode).findFirst().orElse(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
