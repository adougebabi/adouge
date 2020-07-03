package com.adouge.core.mybatis.plugins;

import cn.hutool.core.util.ObjectUtil;
import com.adouge.core.mybatis.intercept.QueryInterceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Arrays;

/**
 * @author : Vinson
 * @date : 2020/6/12 1:25 下午
 */
public class QueryInterceptorExecutor {

    static void exec(QueryInterceptor[] interceptors, Invocation invocation) throws Throwable {
        if (!ObjectUtil.isEmpty(interceptors)) {
            for (QueryInterceptor interceptor : interceptors) {
                interceptor.intercept(invocation);
            }
        }
    }
}
