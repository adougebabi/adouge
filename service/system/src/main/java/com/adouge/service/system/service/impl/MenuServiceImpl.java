package com.adouge.service.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.core.tool.constant.StringConstant;
import com.adouge.core.tool.constant.WebConstant;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.secure.AdougeUser;
import com.adouge.service.system.entity.Menu;
import com.adouge.service.system.entity.RoleMenu;
import com.adouge.service.system.entity.TopMenuMenu;
import com.adouge.service.system.mapper.MenuMapper;
import com.adouge.service.system.service.IMenuService;
import com.adouge.service.system.service.IRoleMenuService;
import com.adouge.service.system.service.ITopMenuMenuService;
import com.adouge.service.system.vo.MenuVO;
import com.adouge.service.system.wrapper.MenuWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Vinson
 * @date : 2020/6/3 11:25 上午
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    private final IRoleMenuService roleMenuService;
    private final ITopMenuMenuService topMenuMenuService;

    @Override
    public List<MenuVO> routes(AdougeUser adougeUser, Long topMenuId) {
        return MenuWrapper.build().listNodeVO(baseMapper.roleMenu(adougeUser.getRoleId(),topMenuId));
    }

    @Override
    public List<MenuVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }

    @Override
    public List<MenuVO> grantTree(AdougeUser user) {
        return ForestNodeMerger.merge(user.getTenantId().equals(WebConstant.ADMIN_TENANT_ID) ? baseMapper.tree() : baseMapper.treeByRole(user.getRoleId()));
    }

    @Override
    public List<String> roleTreeKeys(String roleIds) {
        List<RoleMenu> roleMenus = roleMenuService.list(Wrappers.<RoleMenu>lambdaQuery().in(RoleMenu::getRoleId, CollUtil.newArrayList(roleIds.split(StringConstant.COMMA))));
        return roleMenus.stream().map(roleMenu -> StrUtil.toString(roleMenu.getMenuId())).collect(Collectors.toList());
    }

    @Override
    public List<String> topMenuTreeKeys(String topMenuIds) {
        List<TopMenuMenu> topMenuMenus = topMenuMenuService.list(Wrappers.<TopMenuMenu>lambdaQuery().in(TopMenuMenu::getTopMenuId, CollUtil.newArrayList(topMenuIds.split(StringConstant.COMMA))));
        return topMenuMenus.stream().map(topMenus -> StrUtil.toString(topMenus.getMenuId())).collect(Collectors.toList());
    }
}
