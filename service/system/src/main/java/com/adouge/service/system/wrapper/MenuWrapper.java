package com.adouge.service.system.wrapper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.service.system.service.IMenuService;
import com.adouge.service.system.entity.Menu;
import com.adouge.service.system.vo.MenuVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Vinson
 * @date : 2020/6/4 9:19 上午
 */
public class MenuWrapper extends BaseEntityWrapper<Menu, MenuVO> {
    private static final IMenuService menuService;

    static {
        menuService = SpringUtil.getBean(IMenuService.class);
    }

    private MenuWrapper() {
    }

    private static MenuWrapper instance;

    public static MenuWrapper build() {
        if (instance == null) {
            synchronized (MenuWrapper.class) {
                if (instance == null) {
                    instance = new MenuWrapper();
                }
            }
        }
        return instance;
    }

    @Override
    public MenuVO entityVO(Menu entity) {
//        TODO 基础版本
        MenuVO menuVO = new MenuVO();
        BeanUtil.copyProperties(entity, menuVO);
        return menuVO;
    }
    public List<MenuVO> listNodeVO(List<Menu> list) {
        List<MenuVO> collect = list.stream().map(menu -> BeanUtil.copyProperties(menu, MenuVO.class)).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }
}
