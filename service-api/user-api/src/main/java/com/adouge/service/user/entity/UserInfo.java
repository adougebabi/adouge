package com.adouge.service.user.entity;

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
    @ApiModelProperty(value = "租户id")
    private String tenantId;
    @ApiModelProperty(value = "权限列表")
    private List<String> permissions;
    @ApiModelProperty(value = "角色ids")
    private List<Long> roleIds;
    @ApiModelProperty(value = "角色Names")
    private List<String> roleNames;
    @ApiModelProperty(value = "部门ids")
    private List<Long> deptIds;
    @ApiModelProperty(value = "部门Names")
    private List<String> deptNames;
}
