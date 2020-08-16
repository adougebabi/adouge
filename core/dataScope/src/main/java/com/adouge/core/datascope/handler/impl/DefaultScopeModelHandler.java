package com.adouge.core.datascope.handler.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.adouge.core.datascope.constant.DataScopeConstant;
import com.adouge.core.datascope.handler.IScopeModelHandler;
import com.adouge.core.datascope.model.DataScopeModel;
import com.adouge.core.tool.constant.StringConstant;
import com.adouge.secure.utils.CacheUtil;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/13 10:47 下午
 */
@AllArgsConstructor
//FIXME 缓存
public class DefaultScopeModelHandler implements IScopeModelHandler {
    private final String SCOPE_CACHE_CODE = "dataScope:code:";
    private final String SCOPE_CACHE_CLASS = "dataScope:class:";
    private final String DEPT_CACHE_ANCESTORS = "dept:ancestors:";
    private final String CACHE_PREFIX = "adouge:sys";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public DataScopeModel getDataScopeByMapper(String mapperId, List<Long> roleIds) {
        List<Object> args = CollUtil.newArrayList(mapperId);
        args.addAll(roleIds);
        DataScopeModel dataScope = CacheUtil.get(CACHE_PREFIX, SCOPE_CACHE_CODE, mapperId + StringConstant.COLON+ StrUtil.join(StringConstant.COMMA, roleIds), DataScopeModel.class);
        if (dataScope == null) {
            List<DataScopeModel> list = this.jdbcTemplate.query(DataScopeConstant.dataByMapper(roleIds.size()), args.toArray(), new BeanPropertyRowMapper<>(DataScopeModel.class));
            if (CollectionUtil.isNotEmpty(list)) {
                dataScope = list.iterator().next();
                CacheUtil.put(CACHE_PREFIX, SCOPE_CACHE_CODE, mapperId + StringConstant.COLON + StrUtil.join(StringConstant.COMMA, roleIds), dataScope);
            }
        }

        return dataScope;
    }

    @Override
    public DataScopeModel getDataScopeByCode(String code) {
        DataScopeModel dataScope = CacheUtil.get(CACHE_PREFIX, SCOPE_CACHE_CLASS, code, DataScopeModel.class);
        if (dataScope == null) {
            List<DataScopeModel> list = this.jdbcTemplate.query(DataScopeConstant.DATA_BY_CODE, new BeanPropertyRowMapper<>(DataScopeModel.class), code);
            if (CollectionUtil.isNotEmpty(list)) {
                dataScope = list.iterator().next();
                CacheUtil.put(CACHE_PREFIX, SCOPE_CACHE_CLASS, code, dataScope);
            }
        }

        return dataScope;
    }

    @Override
    public List<Long> getDeptAncestors(Long deptId) {
        List<Long> ancestors = CacheUtil.get(CACHE_PREFIX, DEPT_CACHE_ANCESTORS, deptId, List.class);
        if (CollectionUtil.isEmpty(ancestors)) {
            ancestors = this.jdbcTemplate.queryForList(DataScopeConstant.DATA_BY_DEPT, Long.class, deptId, deptId);
            CacheUtil.put(CACHE_PREFIX, DEPT_CACHE_ANCESTORS, deptId, ancestors);
        }

        return ancestors;
    }
}
