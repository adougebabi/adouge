package com.adouge.service.system.service.impl;

import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.service.system.mapper.MenuMapper;
import com.adouge.service.system.service.IMenuService;
import com.adouge.service.system.wrapper.MenuWrapper;
import com.adouge.service.system.entity.Menu;
import com.adouge.service.system.vo.MenuVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/3 11:25 上午
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Override
    public List<MenuVO> routes() {
        return MenuWrapper.build().listNodeVO(baseMapper.selectList(Wrappers.<Menu>lambdaQuery().eq(Menu::getType, "1").orderByAsc(Menu::getSort)));
    }

    @Override
    public List<MenuVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }
}
