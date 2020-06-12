package com.adouge.service.user.vo;

import com.adouge.service.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 用户表 视图实体类
 *
 * @author Vinson
 * @since 2020-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserVO对象", description = "用户表 ")
public class UserVO extends User {
	private static final long serialVersionUID = 1L;

}
