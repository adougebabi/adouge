package com.adouge.service.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.adouge.core.mybatis.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 顶部菜单表 实体类
 *
 * @author Vinson
 * @since 2020-07-01
 */
@Data
@TableName("adouge_top_menu")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TopMenu对象", description = "顶部菜单表 ")
public class TopMenu extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单编号 i18n
     */
    @ApiModelProperty(value = "菜单编号 i18n")
    private String code;
    /**
     * 菜单名称 label
     */
    @ApiModelProperty(value = "菜单名称 label")
    private String name;
    /**
     * 菜单请求地址 path
     */
    @ApiModelProperty(value = "菜单请求地址 path")
    private String path;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


}
