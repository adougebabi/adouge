package com.adouge.service.scope.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.adouge.core.mybatis.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 接口权限表 实体类
 *
 * @author Vinson
 * @since 2020-08-13
 */
@Data
@TableName("adouge_scope_api")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ScopeApi对象", description = "接口权限表 ")
public class ScopeApi extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单Id
     */
    @ApiModelProperty(value = "菜单Id")
    private Long menuId;
    /**
     * 资源编号
     */
    @ApiModelProperty(value = "资源编号")
    private String resourceCode;
    /**
     * 接口权限名
     */
    @ApiModelProperty(value = "接口权限名")
    private String scopeName;
    /**
     * 接口权限地址
     */
    @ApiModelProperty(value = "接口权限地址")
    private String scopePath;
    /**
     * 接口权限类型
     */
    @ApiModelProperty(value = "接口权限类型")
    private Integer scopeType;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


}
