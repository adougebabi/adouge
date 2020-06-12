package com.adouge.ops.develop.entity;

import com.adouge.core.mybatis.base.TenantEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 代码生成表 实体类
 *
 * @author Vinson
 * @since 2020-06-10
 */
@Data
@TableName("adouge_code")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Code对象", description = "代码生成表 ")
public class Code extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 服务名称
     */
    @ApiModelProperty(value = "服务名称")
    private String serviceName;
    /**
     * 模块名称
     */
    @ApiModelProperty(value = "模块名称")
    private String codeName;
    /**
     * 表名
     */
    @ApiModelProperty(value = "表名")
    private String tableName;
    /**
     * 表前缀
     */
    @ApiModelProperty(value = "表前缀")
    private String tablePrefix;
    /**
     * 包名
     */
    @ApiModelProperty(value = "包名")
    private String packageName;
    /**
     * 包装器模式
     */
    @ApiModelProperty(value = "包装器模式")
    private Boolean wrapMode;
    /**
     * 后端路径
     */
    @ApiModelProperty(value = "后端路径")
    private String apiPath;
    /**
     * 前端路径
     */
    @ApiModelProperty(value = "前端路径")
    private String webPath;


}
