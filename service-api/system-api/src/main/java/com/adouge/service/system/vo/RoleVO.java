package com.adouge.service.system.vo;

import cn.hutool.core.collection.CollUtil;
import com.adouge.core.tool.node.INode;
import com.adouge.service.system.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色表 视图实体类
 *
 * @author AdougeBabi
 * @since 2020-06-09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RoleVO对象", description = "角色表 ")
public class RoleVO extends Role implements INode {
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
	 * 上级角色
	 */
	private String parentName;
}
