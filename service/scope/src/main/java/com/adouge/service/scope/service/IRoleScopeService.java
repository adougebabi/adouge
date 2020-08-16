package com.adouge.service.scope.service;

import com.adouge.service.scope.dto.RoleScopeDTO;
import com.adouge.service.scope.entity.RoleScope;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色菜单表  服务类
 *
 * @author VinsonMenuServiceImpl
 * @since 2020-06-16
 */
public interface IRoleScopeService extends IService<RoleScope> {
    /**
     * 角色所分配的树
     * @param roleIds 角色ids
     * @return 菜单ids
     */
    List<String> roleTreeKeys(String roleIds, int type) ;

    /**
     * 授权菜单树
     */
    boolean grant(RoleScopeDTO dto);
}
