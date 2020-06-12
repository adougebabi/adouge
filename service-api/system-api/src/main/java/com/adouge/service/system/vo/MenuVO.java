package com.adouge.service.system.vo;

import cn.hutool.core.collection.CollUtil;
import com.adouge.core.tool.node.INode;
import com.adouge.service.system.entity.Menu;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/3 10:07 上午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends Menu implements INode {
    private static final long serialVersionUID = 1L;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<INode> children;

    @Override
    public List<INode> getChildren() {
        if (CollUtil.isEmpty(this.children)) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }
}
