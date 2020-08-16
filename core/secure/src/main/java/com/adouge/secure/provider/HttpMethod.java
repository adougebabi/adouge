package com.adouge.secure.provider;

/**
 * @author : Vinson
 * @date : 2020/8/13 11:22 上午
 */
public enum HttpMethod {
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE,
    ALL;

    private HttpMethod() {
    }

    public static HttpMethod of(String method) {
        try {
            return valueOf(method);
        } catch (Exception var2) {
            return null;
        }
    }
}
