package com.adouge.service.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联表 实体类
 *
 * @author Vinson
 * @since 2020-06-16
 */
@Data
@TableName("adouge_user_role")
@Builder
@ApiModel(value = "UserRole对象", description = "UserRole对象")
public class UserRole implements Serializable {
	@ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	@ApiModelProperty(value = "用户id")
	private Long userId;
	@ApiModelProperty(value = "角色id")
	private Long roleId;


}
