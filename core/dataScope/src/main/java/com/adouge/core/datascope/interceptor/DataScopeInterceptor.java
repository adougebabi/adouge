package com.adouge.core.datascope.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.adouge.core.datascope.annotation.DataAuth;
import com.adouge.core.datascope.handler.IDataScopeHandler;
import com.adouge.core.datascope.model.DataScopeModel;
import com.adouge.core.datascope.props.DataScopeProperties;
import com.adouge.core.mybatis.intercept.QueryInterceptor;
import com.adouge.secure.AdougeUser;
import com.adouge.secure.utils.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author : Vinson
 * @date : 2020/8/13 9:54 下午
 */
@Slf4j
@AllArgsConstructor
public class DataScopeInterceptor implements QueryInterceptor {
    private final ConcurrentMap<String, DataAuth> dataAuthMap = new ConcurrentHashMap<>(8);
    private final IDataScopeHandler dataScopeHandler;
    private final DataScopeProperties dataScopeProperties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (!this.dataScopeProperties.getEnabled()) {
            return invocation.proceed();
        } else {
            AdougeUser user = SecureUtil.getUser();
            if (user == null) {
                return invocation.proceed();
            } else {
                StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
                MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
                MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
                if (SqlCommandType.SELECT == mappedStatement.getSqlCommandType() && StatementType.CALLABLE != mappedStatement.getStatementType()) {
                    boolean mapperSkip = true;
                    BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
                    String originalSql = boundSql.getSql();
                    DataAuth dataAuth = this.findDataAuthAnnotation(mappedStatement);
                    String mapperId = mappedStatement.getId();
                    String className = mapperId.substring(0, mapperId.lastIndexOf("."));
                    String mapperName = ClassUtils.getShortName(className);
                    String methodName = mapperId.substring(mapperId.lastIndexOf(".") + 1);

                    if (this.dataScopeProperties.getMapperKey().stream().anyMatch(methodName::contains)) {
                        if (this.dataScopeProperties.getMapperExclude().stream().noneMatch(mapperName::contains)) {
                            mapperSkip = false;
                        }
                    }
                    if (dataAuth != null || !mapperSkip) {
                        DataScopeModel dataScope = new DataScopeModel();
                        if (dataAuth != null) {
                            dataScope.setResourceCode(dataAuth.code());
                            dataScope.setScopeColumn(dataAuth.column());
                            dataScope.setScopeType(dataAuth.type().getType());
                            dataScope.setScopeField(dataAuth.field());
                            dataScope.setScopeValue(dataAuth.value());
                        }

                        String sqlCondition = this.dataScopeHandler.sqlCondition(invocation, mapperId, dataScope, user, originalSql);
                        if (!StrUtil.isBlank(sqlCondition)) {
                            metaObject.setValue("delegate.boundSql.sql", sqlCondition);
                        }
                    }
                    return invocation.proceed();
                } else {
                    return invocation.proceed();
                }
            }
        }
    }

    private DataAuth findDataAuthAnnotation(MappedStatement mappedStatement) {
        String id = mappedStatement.getId();
        return this.dataAuthMap.computeIfAbsent(id, (key) -> {
            String className = key.substring(0, key.lastIndexOf("."));
            String mapperBean = ClassUtils.getShortName(className);
            mapperBean=mapperBean.substring(0,1).toLowerCase()+mapperBean.substring(1);
            Object mapper = SpringUtil.getBean(mapperBean);
            String methodName = key.substring(key.lastIndexOf(".") + 1);
            Class<?>[] interfaces = ClassUtils.getAllInterfaces(mapper);
            for (Class<?> anInterface : interfaces) {
                for (Method method : anInterface.getDeclaredMethods()) {
                    if (methodName.equals(method.getName()) && method.isAnnotationPresent(DataAuth.class)) {
                        return method.getAnnotation(DataAuth.class);
                    }
                }
            }

            return null;
        });
    }
}
