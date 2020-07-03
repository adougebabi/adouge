package com.adouge.service.system.service;

import com.adouge.core.mybatis.base.BaseService;
import com.adouge.secure.AdougeUser;
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
    List<MenuVO> routes(AdougeUser adougeUser,Long topMenuId);

    /**
     * 树形列表
     * @return 树
     */
    List<MenuVO> tree();

    /**
     * 获取权限分配树形结构
     * @param user adougeuser
     * @return 树
     */
    List<MenuVO> grantTree(AdougeUser user);

    /**
     * 角色所分配的树
     * @param roleIds 角色ids
     * @return 菜单ids
     */
    List<String> roleTreeKeys(String roleIds);

    /**
     * 角色所分配的树
     * @param topMenuIds 顶部菜单ids
     * @return 菜单ids
     */
    List<String> topMenuTreeKeys(String topMenuIds);
}
