package com.adouge.secure.constant;

import cn.hutool.core.util.StrUtil;

/**
 * @author : Vinson
 * @date : 2020/8/13 2:26 下午
 */
public interface PermissionConstant {
    static String permissionAllStatement(int size) {
        return "select scope_path as path from adouge_scope_api where id in (select scope_id from blade_role_scope where scope_category = 2 and role_id in (" + buildHolder(size) + "))";
    }

    static String permissionStatement(int size) {
        return "select resource_code as code from adouge_scope_api where resource_code = ? and id in (select scope_id from blade_role_scope where scope_category = 2 and role_id in (" + buildHolder(size) + "))";
    }

    static String buildHolder(int size) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < size; ++i) {
            builder.append("?,");
        }
        return StrUtil.removeSuffix(builder.toString(), ",");
    }
}
