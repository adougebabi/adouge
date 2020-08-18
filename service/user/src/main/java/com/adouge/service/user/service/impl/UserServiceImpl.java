package com.adouge.service.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.service.user.dto.UserDTO;
import com.adouge.service.user.entity.User;
import com.adouge.service.user.entity.UserDept;
import com.adouge.service.user.entity.UserInfo;
import com.adouge.service.user.entity.UserRole;
import com.adouge.service.user.mapper.UserMapper;
import com.adouge.service.user.service.IUserDeptService;
import com.adouge.service.user.service.IUserRoleService;
import com.adouge.service.user.service.IUserService;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:20 下午
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {
    private final IUserRoleService userRoleService;
    private final IUserDeptService userDeptService;

    @Override
    public UserInfo userInfo(String tenantId, String account, String password) {
        UserInfo userInfo = new UserInfo();
//        account = SecureUtil.decodeAes(account);
//        password = SecureUtil.decodeAes(password);
        User user = baseMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getTenantId, tenantId)
                .eq(User::getAccount, account)
                .eq(User::getPassword, password)
                .eq(User::getIsDeleted, 0)
        );
        if (ObjectUtil.isNotNull(user)) {
            userInfo.setUser(user);
            userInfo.setRoleNames(userRoleService.listRoleName(user.getId()));
            userInfo.setRoleIds(userRoleService.listRoleId(user.getId()));
            userInfo.setDeptIds(userDeptService.listDeptId(user.getId()));
            userInfo.setDeptNames(userDeptService.listDeptName(user.getId()));
        }
        return userInfo;
    }

    @Override
    public UserInfo userInfo(Long userId) {
//        FIXME 查询权限
        UserInfo userInfo = new UserInfo();
        User user = baseMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getId, userId)
                .eq(User::getIsDeleted, 0)
        );
        if (ObjectUtil.isNotNull(user)) {
            userInfo.setUser(user);
            userInfo.setTenantId(user.getTenantId());
            userInfo.setRoleNames(userRoleService.listRoleName(user.getId()));
            userInfo.setRoleIds(userRoleService.listRoleId(user.getId()));
            userInfo.setDeptIds(userDeptService.listDeptId(user.getId()));
            userInfo.setDeptNames(userDeptService.listDeptName(user.getId()));
            userInfo.setPermissions(CollUtil.newArrayList());
        }
        return userInfo;
    }

    @Override
    public boolean saveUser(UserDTO user) {
        Long userId;
        if (ObjectUtil.isNull(user.getId())) {
            DefaultIdentifierGenerator defaultIdentifierGenerator = new DefaultIdentifierGenerator();
            userId = defaultIdentifierGenerator.nextId(null);
            if (log.isDebugEnabled()) {
                log.debug("缺少id，主动生成-->{}", userId);
            }
            user.setId(userId);
        } else {
            userId = user.getId();
            userRoleService.remove(Wrappers.<UserRole>update().lambda().in(UserRole::getUserId, userId));
            userDeptService.remove(Wrappers.<UserDept>update().lambda().in(UserDept::getUserId, userId));
            User byId = getById(userId);
            user.setPassword(byId.getPassword());
        }

        List<UserRole> roles = CollUtil.newArrayList();
        List<UserDept> depts = CollUtil.newArrayList();

        user.getRoleIds().forEach(role -> roles.add(UserRole.builder().userId(userId).roleId(role).build()));
        user.getDeptIds().forEach(dept -> depts.add(UserDept.builder().userId(userId).deptId(dept).build()));

        boolean b1 = userRoleService.saveBatch(roles);
        boolean b2 = userDeptService.saveBatch(depts);
        boolean b3 = saveOrUpdate(user);
        return b1 && b2 && b3;
    }
}
