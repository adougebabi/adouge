package com.adouge.service.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.adouge.core.mybatis.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 部门表 实体类
 *
 * @author Vinson
 * @since 2020-06-11
 */
@Data
@TableName("adouge_dept")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Dept对象", description = "部门表 ")
public class Dept extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Long parentId;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    /**
     * 部门全名
     */
    @ApiModelProperty(value = "部门全名")
    private String fullName;
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
