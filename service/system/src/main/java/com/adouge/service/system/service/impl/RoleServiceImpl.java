package com.adouge.service.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.core.tool.constant.GlobalConstant;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.secure.utils.SecureUtil;
import com.adouge.service.system.entity.Role;
import com.adouge.service.system.entity.RoleMenu;
import com.adouge.service.system.mapper.RoleMapper;
import com.adouge.service.system.service.IRoleMenuService;
import com.adouge.service.system.service.IRoleService;
import com.adouge.service.system.vo.RoleVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色表  服务实现类
 *
 * @author AdougeBabi
 * @since 2020-06-09
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {
    private final IRoleMenuService roleMenuService;

    @Override
    public List<RoleVO> tree(String tenantId) {

        List<String> userRole = SecureUtil.getUserRole();
        String excludeRole = null;
        if (!CollectionUtil.contains(userRole, GlobalConstant.ADMIN)) {
            excludeRole = GlobalConstant.ADMIN;
        }
        return ForestNodeMerger.merge(baseMapper.tree(tenantId, excludeRole));
    }

    @Override
    public boolean grant(List<Long> roleIds, List<Long> menuIds) {
        roleMenuService.remove(Wrappers.<RoleMenu>update().lambda().in(RoleMenu::getRoleId, roleIds));
        List<RoleMenu> data = CollUtil.newArrayList();
        roleIds.forEach(roleId ->
                menuIds.forEach(menuId -> {
                    RoleMenu menu = new RoleMenu();
                    menu.setRoleId(roleId);
                    menu.setMenuId(menuId);
                    data.add(menu);
                }));
        return roleMenuService.saveBatch(data);
    }
}
