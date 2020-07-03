package com.adouge.service.system.wrapper;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.core.tool.constant.GlobalConstant;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.service.system.entity.Role;
import com.adouge.service.system.service.IRoleService;
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
	private final static IRoleService roleService;

	static {
		roleService = SpringUtil.getBean(IRoleService.class);
	}

    public static RoleWrapper build() {
        return new RoleWrapper();
    }

	@Override
	public RoleVO entityVO(Role role) {
		RoleVO roleVO = new RoleVO();
		BeanUtil.copyProperties(role, roleVO);
		if (role.getParentId().equals(GlobalConstant.TOP_PARENT_ID)) {
			roleVO.setParentName(GlobalConstant.TOP_PARENT_NAME);
		} else {
			Role parent = roleService.getById(role.getParentId());
			roleVO.setParentName(parent.getRoleName());
		}
		return roleVO;
	}
	public List<RoleVO> listNodeVO(List<Role> list) {
		return ForestNodeMerger.merge(list.stream().map(role -> BeanUtil.copyProperties(role, RoleVO.class)).collect(Collectors.toList()));
	}

}
