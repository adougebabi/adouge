package com.adouge.core.mybatis.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : Vinson
 * @date : 2020/6/7 3:05 下午
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "查询条件")
public class Query {

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer current = 1;

    /**
     * 每页的数量
     */
    @ApiModelProperty(value = "每页的数量")
    private Integer size = 10;

    /**
     * 排序的字段名
     */
    @ApiModelProperty(hidden = true)
    private String ascs;

    /**
     * 排序方式
     */
    @ApiModelProperty(hidden = true)
    private String descs;
}
