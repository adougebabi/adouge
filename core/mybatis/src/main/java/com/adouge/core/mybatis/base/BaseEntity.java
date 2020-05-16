package com.adouge.core.mybatis.base;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author : Vinson
 * @date : 2020/5/15 9:54 上午
 */
@Data
public class BaseEntity {

    /**
     * 创建人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "创建人")
    private Long createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "更新人")
    private Long updatedBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    /**
     * 状态[1:正常]
     */
    @ApiModelProperty(value = "业务状态")
    private Integer status;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    @ApiModelProperty(value = "是否已删除")
    private Integer isDeleted;

    @Version
    @ApiModelProperty(value = "乐观锁")
    private Long version;
}
