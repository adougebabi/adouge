package com.adouge.core.mybatis.config;

import cn.hutool.core.util.ObjectUtil;
import com.adouge.core.launch.props.AdougePropertySource;
import com.adouge.core.mybatis.handler.DefaultTenantHandler;
import com.adouge.core.mybatis.intercept.QueryInterceptor;
import com.adouge.core.mybatis.plugins.AdougePaginationInterceptor;
import com.adouge.core.mybatis.tenant.DefaultTenantSqlParser;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 1:42 下午
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.adouge.**.mapper")
@EnableConfigurationProperties({MybatisPlusProperties.class})
@AdougePropertySource("classpath:/mybatis.yml")
public class MyBatisConfig {


    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean({TenantHandler.class})
    public TenantHandler tenantHandler() {
        return new DefaultTenantHandler();
    }

    @Bean
    @ConditionalOnMissingBean({DefaultTenantSqlParser.class})
    public DefaultTenantSqlParser tenantSqlParser(TenantHandler tenantHandler) {
        return new DefaultTenantSqlParser(tenantHandler);
    }


    @Bean
    @ConditionalOnBean(DefaultTenantSqlParser.class)
    public PaginationInterceptor paginationInterceptor(ObjectProvider<QueryInterceptor[]> queryInterceptors, ObjectProvider<ISqlParserFilter> sqlParserFilter, TenantHandler tenantHandler,TenantSqlParser tenantSqlParser) {
        AdougePaginationInterceptor paginationInterceptor = new AdougePaginationInterceptor();
        QueryInterceptor[] queryInterceptorArray = queryInterceptors.getIfAvailable();

        if (ObjectUtil.isNotEmpty(queryInterceptorArray)) {
            assert queryInterceptorArray != null;
            AnnotationAwareOrderComparator.sort(queryInterceptorArray);
            paginationInterceptor.setQueryInterceptors(queryInterceptorArray);
        }
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));

        List<ISqlParser> sqlParserList = new ArrayList<>();
        tenantSqlParser.setTenantHandler(tenantHandler);
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setSqlParserFilter(sqlParserFilter.getIfAvailable());
        return paginationInterceptor;
    }
}
