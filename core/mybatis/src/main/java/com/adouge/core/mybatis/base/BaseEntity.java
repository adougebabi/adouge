package com.adouge.core.mybatis.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author : Vinson
 * @date : 2020/5/15 9:54 上午
 */
@Data
public class BaseEntity {
    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;
    /**
     * 创建人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "创建人")
    @TableField(value="created_by",fill = FieldFill.INSERT)
    private Long createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value="created_time",fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "更新人")
    @TableField(value="updated_by",fill = FieldFill.INSERT_UPDATE)
    private Long updatedBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value="updated_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    /**
     * 状态[1:正常]
     */
    @ApiModelProperty(value = "业务状态")
    @TableField(value="status",fill = FieldFill.INSERT)
    private Integer status;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    @ApiModelProperty(value = "是否已删除")
    @TableField(value="is_deleted",fill = FieldFill.INSERT)
    private Integer isDeleted;

    @Version
    @ApiModelProperty(value = "乐观锁")
    @TableField(value="version",fill = FieldFill.INSERT)
    private Long version;

}
