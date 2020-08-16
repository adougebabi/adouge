package com.adouge.service.scope.dto;

import com.adouge.service.scope.entity.ScopeApi;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 接口权限表 数据传输对象实体类
 *
 * @author Vinson
 * @since 2020-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScopeApiDTO extends ScopeApi {
	private static final long serialVersionUID = 1L;

}
