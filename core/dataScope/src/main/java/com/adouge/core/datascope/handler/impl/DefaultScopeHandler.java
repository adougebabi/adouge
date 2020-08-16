package com.adouge.core.datascope.handler.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.adouge.core.datascope.enums.DataScopeEnum;
import com.adouge.core.datascope.handler.IDataScopeHandler;
import com.adouge.core.datascope.handler.IScopeModelHandler;
import com.adouge.core.datascope.model.DataScopeModel;
import com.adouge.core.tool.constant.GlobalConstant;
import com.adouge.core.tool.constant.StringConstant;
import com.adouge.core.tool.utils.PlaceholderUtil;
import com.adouge.secure.AdougeUser;
import lombok.AllArgsConstructor;
import org.apache.ibatis.plugin.Invocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : Vinson
 * @date : 2020/8/13 10:29 下午
 */
@AllArgsConstructor
public class DefaultScopeHandler implements IDataScopeHandler {
    private final IScopeModelHandler scopeModelHandler;

    @Override
    public String sqlCondition(Invocation invocation, String mapperId, DataScopeModel dataScope, AdougeUser user, String originalSql) {
        String code = dataScope.getResourceCode();
        DataScopeModel dataScopeDb = this.scopeModelHandler.getDataScopeByMapper(mapperId, user.getRoleId());
        if (dataScopeDb == null && StrUtil.isNotBlank(code)) {
            dataScopeDb = this.scopeModelHandler.getDataScopeByCode(code);
        }

        dataScope = dataScopeDb != null ? dataScopeDb : dataScope;
        Integer scopeRule = (Objects.requireNonNull(dataScope)).getScopeType();
        DataScopeEnum scopeTypeEnum = DataScopeEnum.of(scopeRule);
        List<Long> ids = new ArrayList<>();
        String whereSql = "where scope.{} in ({})";
        if (scopeTypeEnum != null && DataScopeEnum.ALL != scopeTypeEnum && !user.getRoleName().contains(GlobalConstant.ADMIN)) {
            if (DataScopeEnum.CUSTOM == scopeTypeEnum) {
                whereSql = PlaceholderUtil.getDefaultResolver().resolveByMap(dataScope.getScopeValue(), BeanUtil.beanToMap(user));
            } else if (DataScopeEnum.OWN == scopeTypeEnum) {
                ids.add(user.getUserId());
            } else if (DataScopeEnum.OWN_DEPT == scopeTypeEnum) {
                ids.addAll(user.getDeptId());
            } else if (DataScopeEnum.OWN_DEPT_CHILD == scopeTypeEnum) {
                ids.addAll(user.getDeptId());
                user.getDeptId().forEach((deptId) -> {
                    List<Long> deptIdList = this.scopeModelHandler.getDeptAncestors(deptId);
                    ids.addAll(deptIdList);
                });
            }
            return StrUtil.format(" select {} from ({}) scope " + whereSql, StrUtil.emptyToDefault(dataScope.getScopeField(), StringConstant.ASTERISK), originalSql, dataScope.getScopeColumn(), StrUtil.join(StringConstant.COMMA, ids));
        } else {
            return null;
        }
    }

}
