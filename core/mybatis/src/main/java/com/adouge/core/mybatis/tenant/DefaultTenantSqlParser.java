package com.adouge.core.mybatis.tenant;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.PlainSelect;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/16 3:13 下午
 */
@RequiredArgsConstructor
public class DefaultTenantSqlParser extends TenantSqlParser {
    private final TenantHandler tenantHandler;

    /**
     * 重写添加，防止重复添加租户id
     *
     * @param insert insert
     */
    @Override
    public void processInsert(Insert insert) {
        if (!this.tenantHandler.doTableFilter(insert.getTable().getName())) {
            boolean hasTenantColumn = this.hasTenantColumn(insert.getColumns());
            if (!hasTenantColumn) {
                insert.getColumns().add(new Column(this.tenantHandler.getTenantIdColumn()));
            }
            if (insert.getSelect() != null && !hasTenantColumn) {
                this.processPlainSelect((PlainSelect) insert.getSelect().getSelectBody(), true);
            } else if (insert.getItemsList() != null && !hasTenantColumn) {
                ItemsList itemsList = insert.getItemsList();
                if (itemsList instanceof MultiExpressionList) {
                    ((MultiExpressionList) itemsList).getExprList().forEach((el) ->
                            el.getExpressions().add(this.tenantHandler.getTenantId(false))
                    );
                } else {
                    ((ExpressionList) insert.getItemsList()).getExpressions().add(this.tenantHandler.getTenantId(false));
                }
            } else if (!hasTenantColumn) {
                throw ExceptionUtils.mpe("Failed to process multiple-table update, please exclude the tableName or statementId", new Object[0]);
            }
        }
    }

    public boolean hasTenantColumn(List<Column> columns) {
        String tenantIdColumn = this.tenantHandler.getTenantIdColumn();
        Column column = columns.stream().filter((c) -> StrUtil.equalsIgnoreCase(c.getColumnName(), tenantIdColumn))
                .findFirst().orElse(null);
        return column != null;
    }
}
