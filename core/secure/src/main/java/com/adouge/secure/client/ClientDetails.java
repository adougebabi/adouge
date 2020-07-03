package com.adouge.secure.client;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : Vinson
 * @date : 2020/6/15 5:04 下午
 */
@Data
public class ClientDetails implements IClientDetails{
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
     * 令牌过期秒数
     */
    @ApiModelProperty(value = "令牌过期秒数")
    private Integer accessTokenTime;
    /**
     * 刷新令牌过期秒数
     */
    @ApiModelProperty(value = "刷新令牌过期秒数")
    private Integer refreshTokenTime;

}
