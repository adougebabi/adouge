package com.adouge.service.system.service;

import com.adouge.core.mybatis.base.BaseService;
import com.adouge.system.entity.Menu;
import com.adouge.system.vo.MenuVO;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/3 11:25 上午
 */
public interface IMenuService extends BaseService<Menu> {

    /**
     * 获取路由
     * @return 路由list
     */
    List<MenuVO> routes();

    /**
     * 根据父id获取子菜单
     * @param id 父id
     * @return 菜单列表
     */
    List<MenuVO> listByParentId(long id);

    /**
     * 树形列表
     * @return 树
     */
    List<MenuVO> tree();

}
