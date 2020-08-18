package com.adouge.core.mybatis.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.adouge.secure.utils.SecureUtil;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/15 11:05 下午
 */
@RequiredArgsConstructor
public class DefaultTenantHandler implements TenantHandler {
    public List<String> ignoreTables = CollUtil.newArrayList("adouge_top_menu_menu", "adouge_role_menu", "adouge_role_menu", "adouge_menu", "adouge_top_menu", "adouge_user_role", "adouge_user_dept");

    @Override
    public Expression getTenantId(boolean select) {
        return new StringValue(StrUtil.nullToDefault(SecureUtil.getTenantId(), "000000"));
    }

    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

    @Override
    public boolean doTableFilter(String tableName) {
        return SecureUtil.isAdministrator() || ignoreTables.contains(tableName) || !StrUtil.isNotBlank(SecureUtil.getTenantId());
    }
}
