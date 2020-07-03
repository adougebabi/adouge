package com.adouge.service.system.vo;

import com.adouge.service.system.entity.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 客户端表 视图实体类
 *
 * @author Vinson
 * @since 2020-06-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ClientVO对象", description = "客户端表 ")
public class ClientVO extends Client {
	private static final long serialVersionUID = 1L;

}
