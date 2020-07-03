package com.adouge.service.system.dto;

import com.adouge.service.system.entity.RoleMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/16 3:31 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenuDTO extends RoleMenu {
    @ApiModelProperty(value = "角色ids")
    private List<Long> roleIds;
    @ApiModelProperty(value = "菜单ids")
    private List<Long> menuIds;
}
