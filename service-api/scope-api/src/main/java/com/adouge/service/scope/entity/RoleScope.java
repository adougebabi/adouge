package com.adouge.service.scope.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色菜单关联表 实体类
 *
 * @author Vinson
 * @since 2020-06-16
 */
@Data
@TableName("adouge_role_scope")
@ApiModel(value = "RoleMenu对象", description = "RoleMenu对象")
public class RoleScope implements Serializable {
	@ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	@ApiModelProperty(value = "权限id")
	private Long scopeId;
	@ApiModelProperty(value = "权限类型")
	private Integer scopeCategory;
	@ApiModelProperty(value = "角色id")
	private Long roleId;


}
