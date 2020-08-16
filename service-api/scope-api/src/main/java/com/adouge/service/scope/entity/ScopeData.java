package com.adouge.service.scope.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.adouge.core.mybatis.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  实体类
 *
 * @author Vinson
 * @since 2020-08-15
 */
@Data
@TableName("adouge_scope_data")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ScopeData对象", description = " ")
public class ScopeData extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @ApiModelProperty(value = "菜单id")
    private Long menuId;
    /**
     * 资源编号
     */
    @ApiModelProperty(value = "资源编号")
    private String resourceCode;
    /**
     * 数据权限名称
     */
    @ApiModelProperty(value = "数据权限名称")
    private String scopeName;
    /**
     * 数据权限字段
     */
    @ApiModelProperty(value = "数据权限字段")
    private String scopeField;
    /**
     * 数据权限类名
     */
    @ApiModelProperty(value = "数据权限类名")
    private String scopeClass;
    /**
     * 数据权限行
     */
    @ApiModelProperty(value = "数据权限行")
    private String scopeColumn;
    /**
     * 数据权限类型
     */
    @ApiModelProperty(value = "数据权限类型")
    private Integer scopeType;
    /**
     * 数据权限值
     */
    @ApiModelProperty(value = "数据权限值")
    private String scopeValue;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
