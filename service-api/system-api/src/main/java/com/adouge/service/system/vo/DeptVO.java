package com.adouge.service.system.vo;

import cn.hutool.core.collection.CollUtil;
import com.adouge.core.tool.node.INode;
import com.adouge.service.system.entity.Dept;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门表 视图实体类
 *
 * @author Vinson
 * @since 2020-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DeptVO对象", description = "部门表 ")
public class DeptVO extends Dept implements INode {
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
	 * 上级部门
	 */
	private String parentName;
}
