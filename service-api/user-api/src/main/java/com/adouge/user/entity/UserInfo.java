package com.adouge.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/26 8:30 下午
 */
@Data
@ApiModel("用户信息")
public class UserInfo implements Serializable {

    @ApiModelProperty(value = "用户")
    private User user;
    @ApiModelProperty(value = "权限列表")
    private List<String> permissions;
    @ApiModelProperty(value = "角色列表")
    private List<String> roles;
}
