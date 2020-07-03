package com.adouge.service.system.vo;

import cn.hutool.core.collection.CollUtil;
import com.adouge.core.tool.node.INode;
import com.adouge.service.system.entity.Dict;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典表 视图实体类
 *
 * @author Vinson
 * @since 2020-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DictVO对象", description = "字典表 ")
public class DictVO extends Dict implements INode {
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
