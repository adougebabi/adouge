package com.adouge.service.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.secure.AdougeUser;
import com.adouge.service.system.entity.TopMenu;
import com.adouge.service.system.entity.TopMenuMenu;
import com.adouge.service.system.mapper.TopMenuMapper;
import com.adouge.service.system.service.ITopMenuMenuService;
import com.adouge.service.system.service.ITopMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 顶部菜单表  服务实现类
 *
 * @author Vinson
 * @since 2020-07-01
 */
@Service
@RequiredArgsConstructor
public class TopMenuServiceImpl extends BaseServiceImpl<TopMenuMapper, TopMenu> implements ITopMenuService {
    private final ITopMenuMenuService topMenuMenuService;

    @Override
    public List<TopMenu> topMenu(AdougeUser adougeUser) {
        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public boolean grant(List<Long> topMenuIds, List<Long> menuIds) {
        topMenuMenuService.remove(Wrappers.<TopMenuMenu>update().lambda().in(TopMenuMenu::getTopMenuId, topMenuIds));
        List<TopMenuMenu> data = CollUtil.newArrayList();
        topMenuIds.forEach(topMenuId ->
                menuIds.forEach(menuId -> {
                    TopMenuMenu topMenuMenu =new TopMenuMenu();
                    topMenuMenu.setTopMenuId(topMenuId);
                    topMenuMenu.setMenuId(menuId);
                    data.add(topMenuMenu);
                }));
        return topMenuMenuService.saveBatch(data);
    }
}
