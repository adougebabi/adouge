package com.adouge.service.system.service;

import com.adouge.core.mybatis.base.BaseService;
import com.adouge.service.system.entity.Menu;
import com.adouge.service.system.vo.MenuVO;

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
     * 树形列表
     * @return 树
     */
    List<MenuVO> tree();

}
