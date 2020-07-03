package com.adouge.service.system.entity;

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
@TableName("adouge_top_menu_menu")
@ApiModel(value = "topMenuMenu对象", description = "topMenuMenu对象")
public class TopMenuMenu implements Serializable {
	@ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	@ApiModelProperty(value = "菜单id")
	private Long menuId;
	@ApiModelProperty(value = "顶部菜单id")
	private Long topMenuId;


}
