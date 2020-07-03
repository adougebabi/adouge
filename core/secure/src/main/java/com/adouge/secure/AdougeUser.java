package com.adouge.secure;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/15 9:12 上午
 */
@Data
public class AdougeUser implements Serializable {
    /**
     * 客户端id
     */
    @ApiModelProperty(hidden = true)
    private String clientId;

    /**
     * 用户id
     */
    @ApiModelProperty(hidden = true)
    private Long userId;
    /**
     * 租户ID
     */
    @ApiModelProperty(hidden = true)
    private String tenantId;
    /**
     * 昵称
     */
    @ApiModelProperty(hidden = true)
    private String userName;
    /**
     * 账号
     */
    @ApiModelProperty(hidden = true)
    private String account;
    /**
     * 角色id
     */
    @ApiModelProperty(hidden = true)
    private List<String> roleId;
    /**
     * 角色名
     */
    @ApiModelProperty(hidden = true)
    private List<String> roleName;
}
