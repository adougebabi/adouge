package com.adouge.service.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.adouge.core.mybatis.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 租户表 实体类
 *
 * @author Vinson
 * @since 2020-06-16
 */
@Data
@TableName("adouge_tenant")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Tenant对象", description = "租户表 ")
public class Tenant extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户名称
     */
    @ApiModelProperty(value = "租户名称")
    private String tenantName;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String contactMan;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String contactPhone;
    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址")
    private String contactAddress;


}
