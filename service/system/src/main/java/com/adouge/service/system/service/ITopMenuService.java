package com.adouge.service.system.service;

import com.adouge.core.mybatis.base.BaseService;
import com.adouge.secure.AdougeUser;
import com.adouge.service.system.entity.TopMenu;

import java.util.List;

/**
 * 顶部菜单表  服务类
 *
 * @author Vinson
 * @since 2020-07-01
 */
public interface ITopMenuService extends BaseService<TopMenu> {

    /**
     * 获取头部菜单
     * @param adougeUser 用户权限
     * @return 菜单列表
     */
    List<TopMenu> topMenu(AdougeUser adougeUser);

    /**
     * 授权菜单树
     * @param topMenuIds 角色ids
     * @param menuIds 菜单ids
     * @return 是否成功
     */
    boolean grant(List<Long> topMenuIds, List<Long> menuIds);
}
