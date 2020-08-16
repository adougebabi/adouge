package com.adouge.core.datascope.conig;

import com.adouge.core.datascope.handler.IDataScopeHandler;
import com.adouge.core.datascope.handler.IScopeModelHandler;
import com.adouge.core.datascope.handler.impl.DefaultScopeHandler;
import com.adouge.core.datascope.handler.impl.DefaultScopeModelHandler;
import com.adouge.core.datascope.interceptor.DataScopeInterceptor;
import com.adouge.core.datascope.props.DataScopeProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author : Vinson
 * @date : 2020/8/13 10:27 下午
 */
@Configuration
@EnableConfigurationProperties({DataScopeProperties.class})
@AllArgsConstructor
public class DataScopeConfig {
    private final JdbcTemplate jdbcTemplate;

    @Bean
    @ConditionalOnMissingBean({IScopeModelHandler.class})
    public IScopeModelHandler scopeModelHandler() {
        return new DefaultScopeModelHandler(this.jdbcTemplate);
    }

    @Bean
    @ConditionalOnBean({IScopeModelHandler.class})
    @ConditionalOnMissingBean({IDataScopeHandler.class})
    public IDataScopeHandler dataScopeHandler(IScopeModelHandler scopeModelHandler) {
        return new DefaultScopeHandler(scopeModelHandler);
    }

    @Bean
    @ConditionalOnBean({IDataScopeHandler.class})
    @ConditionalOnMissingBean({DataScopeInterceptor.class})
    public DataScopeInterceptor interceptor(IDataScopeHandler dataScopeHandler, DataScopeProperties dataScopeProperties) {
        return new DataScopeInterceptor(dataScopeHandler, dataScopeProperties);
    }
}
