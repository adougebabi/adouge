package com.adouge.core.mybatis.plugins;

import com.adouge.core.mybatis.intercept.QueryInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;

/**
 * @author : Vinson
 * @date : 2020/6/12 1:24 下午
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)})
public class AdougePaginationInterceptor extends PaginationInterceptor {
    private QueryInterceptor[] queryInterceptors;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        QueryInterceptorExecutor.exec(this.queryInterceptors, invocation);
        return super.intercept(invocation);
    }

    public AdougePaginationInterceptor setQueryInterceptors(final QueryInterceptor[] queryInterceptors) {
        this.queryInterceptors = queryInterceptors;
        return this;
    }
}
