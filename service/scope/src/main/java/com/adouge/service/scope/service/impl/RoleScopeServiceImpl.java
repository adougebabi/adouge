package com.adouge.service.scope.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.adouge.core.tool.constant.StringConstant;
import com.adouge.service.scope.dto.RoleScopeDTO;
import com.adouge.service.scope.entity.RoleScope;
import com.adouge.service.scope.mapper.RoleScopeMapper;
import com.adouge.service.scope.service.IRoleScopeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口权限表  服务实现类
 *
 * @author Vinson
 * @since 2020-08-13
 */
@Service
public class RoleScopeServiceImpl extends ServiceImpl<RoleScopeMapper, RoleScope> implements IRoleScopeService {

    @Override
    public List<String> roleTreeKeys(String roleIds, int type) {
        List<RoleScope> roleMenus = list(Wrappers.<RoleScope>query().lambda()
                .in(RoleScope::getRoleId, CollUtil.newArrayList(roleIds.split(StringConstant.COMMA)))
                .eq(RoleScope::getScopeCategory, type));
        return roleMenus.stream().map(roleMenu -> StrUtil.toString(roleMenu.getScopeId())).collect(Collectors.toList());
    }

    @Override
    public boolean grant(RoleScopeDTO dto) {
        remove(Wrappers.<RoleScope>update().lambda().in(RoleScope::getRoleId, dto.getRoleIds()));
        List<RoleScope> data = CollUtil.newArrayList();
        dto.getRoleIds().forEach(roleId ->
                dto.getScopeIds().forEach(scopeId -> {
                    RoleScope scope = new RoleScope();
                    scope.setRoleId(roleId);
                    scope.setScopeId(scopeId);
                    scope.setScopeCategory(dto.getScopeCategory());
                    data.add(scope);
                }));
        return saveBatch(data);
    }
}
