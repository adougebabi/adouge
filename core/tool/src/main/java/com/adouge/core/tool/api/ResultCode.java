package com.adouge.core.tool.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

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
     *  500 服务器内部异常
     */
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器内部异常");



    final int code;
    final String message;
}
