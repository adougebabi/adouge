package com.adouge.core.mybatis.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : Vinson
 * @date : 2020/5/15 1:15 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantEntity extends BaseEntity{

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
}
