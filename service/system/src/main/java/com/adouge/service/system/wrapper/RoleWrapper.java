package com.adouge.service.system.wrapper;


import cn.hutool.core.bean.BeanUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.service.system.entity.Role;
import com.adouge.service.system.vo.RoleVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色表 包装类,返回视图层所需的字段
 *
 * @author AdougeBabi
 * @since 2020-06-09
 */
public class RoleWrapper extends BaseEntityWrapper<Role, RoleVO> {

    public static RoleWrapper build() {
        return new RoleWrapper();
    }

	@Override
	public RoleVO entityVO(Role role) {
		RoleVO roleVO = new RoleVO();
		BeanUtil.copyProperties(role, roleVO);
		return roleVO;
	}
	public List<RoleVO> listNodeVO(List<Role> list) {
		return ForestNodeMerger.merge(list.stream().map(role -> BeanUtil.copyProperties(role, RoleVO.class)).collect(Collectors.toList()));
	}

}
