package com.adouge.service.user.dto;

import com.adouge.service.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/17 11:26 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends User {
    @ApiModelProperty(value = "角色ids")
    private List<Long> roleIds;
    @ApiModelProperty(value = "部门ids")
    private List<Long> deptIds;
}
