package com.adouge.core.mybatis.config;

import com.adouge.core.launch.props.AdougePropertySource;
import com.adouge.core.mybatis.plugins.AdougePaginationInterceptor;
import com.adouge.core.tool.constant.WebConstant;
import com.adouge.secure.AdougeUser;
import com.adouge.secure.utils.SecureUtil;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public static List<String> ignoreTables = new ArrayList<>();

    static {
        ignoreTables.add("adouge_top_menu_menu");
        ignoreTables.add("adouge_role_menu");
        ignoreTables.add("adouge_menu");
        ignoreTables.add("adouge_user_role");
        ignoreTables.add("adouge_user_dept");
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        AdougePaginationInterceptor paginationInterceptor = new AdougePaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));

        List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId(boolean select) {
                // select since: 3.3.2，参数 true 表示为 select 下的 where 条件,false 表示 insert/update/delete 下的条件
                // 只有 select 下才允许多参(ValueListExpression),否则只支持单参
                AdougeUser user = SecureUtil.getUser();
                if (user != null) {
                    return new LongValue(user.getTenantId());
                }
                return new LongValue("000000");
            }

            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                AdougeUser user = SecureUtil.getUser();
                if (user != null) {
                    if (user.getUserId().equals(WebConstant.ADMIN_USER_ID)) {
                        return true;
                    }
                }
                return ignoreTables.contains(tableName);
            }
        });
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        /*paginationInterceptor.setSqlParserFilter(metaObject -> {
            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
            // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
            return "com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId());
        });*/
        return paginationInterceptor;
    }
}
