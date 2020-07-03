package com.adouge.core.mybatis.intercept;

import org.apache.ibatis.plugin.Invocation;

/**
 * @author : Vinson
 * @date : 2020/6/12 1:22 下午
 */
public interface QueryInterceptor {
    Object intercept(Invocation invocation) throws Throwable;

    default int getOrder() {
        return Integer.MAX_VALUE;
    }
}
