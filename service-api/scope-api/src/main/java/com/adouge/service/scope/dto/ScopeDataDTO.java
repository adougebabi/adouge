package com.adouge.service.scope.dto;

import com.adouge.service.scope.entity.ScopeData;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  数据传输对象实体类
 *
 * @author Vinson
 * @since 2020-08-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScopeDataDTO extends ScopeData {
	private static final long serialVersionUID = 1L;

}
