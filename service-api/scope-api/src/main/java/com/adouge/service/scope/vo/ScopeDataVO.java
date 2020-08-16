package com.adouge.service.scope.vo;

import cn.hutool.core.collection.CollUtil;
import com.adouge.core.tool.node.INode;
import com.adouge.service.scope.entity.ScopeData;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 *  视图实体类
 *
 * @author Vinson
 * @since 2020-08-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ScopeDataVO对象", description = " ")
public class ScopeDataVO extends ScopeData implements INode {
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
	/**
	 * 上级菜单
	 */
	private String parentName;
}
