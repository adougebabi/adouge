package com.adouge.service.system.mapper;

import com.adouge.service.system.entity.Menu;
import com.adouge.service.system.vo.MenuVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/3 11:23 上午
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuVO> tree();

    List<MenuVO> treeByRole(List<Long> roleId);

    List<Menu> roleMenu(List<Long> roleIds,Long menuIds);
}
