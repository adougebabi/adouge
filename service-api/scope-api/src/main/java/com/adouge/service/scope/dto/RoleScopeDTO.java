package com.adouge.service.scope.dto;

import com.adouge.service.scope.entity.RoleScope;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 接口权限表 数据传输对象实体类
 *
 * @author Vinson
 * @since 2020-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleScopeDTO extends RoleScope {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "角色ids")
	private List<Long> roleIds;
	@ApiModelProperty(value = "权限Id")
	private List<Long> scopeIds;
}
