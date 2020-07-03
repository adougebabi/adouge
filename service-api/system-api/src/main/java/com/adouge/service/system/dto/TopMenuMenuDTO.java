package com.adouge.service.system.dto;

import com.adouge.service.system.entity.TopMenuMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/16 3:31 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TopMenuMenuDTO extends TopMenuMenu {
    @ApiModelProperty(value = "顶部菜单ids")
    private List<Long> topMenuIds;
    @ApiModelProperty(value = "菜单ids")
    private List<Long> menuIds;
}
