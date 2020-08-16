package com.adouge.core.datascope.handler;

import com.adouge.core.datascope.model.DataScopeModel;
import com.adouge.secure.AdougeUser;
import org.apache.ibatis.plugin.Invocation;

/**
 * @author : Vinson
 * @date : 2020/8/13 10:04 下午
 */
public interface IDataScopeHandler {
    /**
     * 拼接权限sql
     */
    String sqlCondition(Invocation invocation, String mapperId, DataScopeModel dataScope, AdougeUser User, String originalSql);
}
