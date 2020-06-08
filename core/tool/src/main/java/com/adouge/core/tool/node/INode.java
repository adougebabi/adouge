package com.adouge.core.tool.node;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/3 9:57 上午
 */
public interface INode {
    /**
     * 主键
     *
     * @return Integer
     */
    Long getId();

    /**
     * 父主键
     *
     * @return Integer
     */
    Long getParentId();

    /**
     * 子孙节点
     *
     * @return List
     */
    List<INode> getChildren();
}
