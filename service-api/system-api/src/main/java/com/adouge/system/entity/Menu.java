package com.adouge.system.entity;

import com.adouge.core.mybatis.base.TenantEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author : Vinson
 * @date : 2020/6/2 10:10 下午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("adouge_menu")
public class Menu extends TenantEntity {
    @ApiModelProperty(value = "父菜单id")
    private Long parentId;
    @ApiModelProperty(value = "菜单编号")
    private String code;
    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "菜单请求地址")
    private String path;
    @ApiModelProperty(value = "菜单资源")
    private String component;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "类型")
    private Integer type;

}
