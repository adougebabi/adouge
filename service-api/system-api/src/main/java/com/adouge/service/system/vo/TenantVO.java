package com.adouge.service.system.vo;

import com.adouge.service.system.entity.Tenant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 租户表 视图实体类
 *
 * @author Vinson
 * @since 2020-06-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantVO对象", description = "租户表 ")
public class TenantVO extends Tenant {
	private static final long serialVersionUID = 1L;

}
