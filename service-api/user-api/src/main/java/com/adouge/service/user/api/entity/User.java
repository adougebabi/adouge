package com.adouge.service.user.api.entity;

import com.adouge.core.mybatis.base.BaseEntity;
import com.adouge.core.mybatis.base.TenantEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:15 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("adouge_user")
public class User extends TenantEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Integer id ;
    @ApiModelProperty(value = "登陆名称")
    private String account ;
    @ApiModelProperty(value = "用户名")
    private String username ;
    @ApiModelProperty(value = "密码")
    private String password ;
    @ApiModelProperty(value = "邮箱")
    private String email ;
    @ApiModelProperty(value = "手机")
    private String phone ;
}
