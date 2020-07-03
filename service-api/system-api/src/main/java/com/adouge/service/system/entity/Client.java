package com.adouge.service.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.adouge.core.mybatis.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户端表 实体类
 *
 * @author Vinson
 * @since 2020-06-16
 */
@Data
@TableName("adouge_client")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Client对象", description = "客户端表 ")
public class Client extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    @ApiModelProperty(value = "客户端id")
    private String clientId;
    /**
     * 客户端密钥
     */
    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;
    /**
     * 令牌刷新时间
     */
    @ApiModelProperty(value = "令牌刷新时间")
    private Integer refreshTokenTime;
    /**
     * 令牌过期时间
     */
    @ApiModelProperty(value = "令牌过期时间")
    private Integer accessTokenTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


}
