package com.adouge.service.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.secure.utils.SecureUtil;
import com.adouge.service.user.entity.User;
import com.adouge.service.user.entity.UserInfo;
import com.adouge.service.user.mapper.UserMapper;
import com.adouge.service.user.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:20 下午
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public UserInfo userInfo(String tenantId, String account, String password) {
        UserInfo userInfo = new UserInfo();
        User user = baseMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getTenantId, tenantId)
                .eq(User::getAccount, SecureUtil.decodeAes(account))
                .eq(User::getPassword, SecureUtil.decodeAes(password))
                .eq(User::getIsDeleted, 0)
        );
        if (ObjectUtil.isNotNull(user)) {
            //TODO
            List<String> roles = new ArrayList<>();
            userInfo.setUser(user);
            userInfo.setRoles(roles);
        }
        return userInfo;
    }
}
