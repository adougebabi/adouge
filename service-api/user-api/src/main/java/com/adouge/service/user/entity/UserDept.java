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
@Builder
@TableName("adouge_user_dept")
@ApiModel(value = "UserDept对象", description = "UserDept对象")
public class UserDept implements Serializable {
	@ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	@ApiModelProperty(value = "用户id")
	private Long userId;
	@ApiModelProperty(value = "部门id")
	private Long deptId;


}
