package com.adouge.core.datascope.constant;

import cn.hutool.core.util.StrUtil;

/**
 * @author : Vinson
 * @date : 2020/8/13 10:56 下午
 */
public interface DataScopeConstant {
    String DEFAULT_COLUMN = "created_dept";
    String DATA_BY_DEPT = "select id from adouge_dept where id=? or parent_id=? and is_deleted = 0";
    String DATA_BY_CODE = "select resource_code, scope_column, scope_field, scope_type, scope_value from adouge_scope_data where resource_code = ?";

    static String dataByMapper(int size) {
        return "select resource_code, scope_column, scope_field, scope_type, scope_value from adouge_scope_data where scope_class = ? and id in (select scope_id from adouge_role_scope where scope_category = 1 and role_id in (" + buildHolder(size) + "))";
    }

    static String buildHolder(int size) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < size; ++i) {
            builder.append("?,");
        }

        return StrUtil.removeSuffix(builder.toString(), ",");
    }
}
