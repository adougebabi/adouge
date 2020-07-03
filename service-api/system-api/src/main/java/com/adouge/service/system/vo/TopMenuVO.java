package com.adouge.service.system.vo;

import com.adouge.service.system.entity.TopMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 顶部菜单表 视图实体类
 *
 * @author Vinson
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TopMenuVO对象", description = "顶部菜单表 ")
public class TopMenuVO extends TopMenu {
	private static final long serialVersionUID = 1L;

}
