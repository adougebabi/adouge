package com.adouge.core.tool.node;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : Vinson
 * @date : 2020/6/5 11:06 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TreeNode extends BaseNode {

    private static final long serialVersionUID = 1L;

    private String title;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long key;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long value;

}
