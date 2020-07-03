package com.adouge.service.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.adouge.core.mybatis.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 字典表 实体类
 *
 * @author Vinson
 * @since 2020-07-03
 */
@Data
@TableName("adouge_dict")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Dict对象", description = "字典表 ")
public class Dict extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典码
     */
    @ApiModelProperty(value = "字典码")
    private String code;
    /**
     * 字典key
     */
    @ApiModelProperty(value = "字典key")
    private String dictKey;
    /**
     * 字段value
     */
    @ApiModelProperty(value = "字段value")
    private String dictValue;
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
    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Long parentId;


}
