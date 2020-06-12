package com.adouge.service.system.entity;

import com.adouge.core.mybatis.base.TenantEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色表 实体类
 *
 * @author AdougeBabi
 * @since 2020-06-09
 */
@Data
@TableName("adouge_role")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Role对象", description = "角色表 ")
public class Role extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Long parentId;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 角色别名
     */
    @ApiModelProperty(value = "角色别名")
    private String roleAlias;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;


}
