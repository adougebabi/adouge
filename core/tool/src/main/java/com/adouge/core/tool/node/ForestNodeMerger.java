package com.adouge.core.tool.node;

import com.adouge.core.launch.constant.CommonConstant;

import java.util.List;

/**
 * 森林节点归并类
 *
 * @author : Vinson
 * @date : 2020/6/8 8:49 下午
 */
public class ForestNodeMerger {

    public static <T extends INode> List<T> merge(List<T> items) {
        ForestNodeManager<T> forestNodeManager = new ForestNodeManager<>(items);
        items.forEach(item -> {
            if (!item.getParentId().equals(CommonConstant.TOP_PARENT_ID)) {
                INode node = forestNodeManager.getTreeNodeAT(item.getParentId());
                if (node != null) {
                    node.getChildren().add(item);
                } else {
                    forestNodeManager.addParentId(item.getId());
                }
            }
        });
        return forestNodeManager.getRoot();
    }
}
