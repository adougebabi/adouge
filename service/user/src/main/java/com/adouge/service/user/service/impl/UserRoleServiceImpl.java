package com.adouge.service.user.service.impl;

import com.adouge.service.user.entity.UserRole;
import com.adouge.service.user.mapper.UserRoleMapper;
import com.adouge.service.user.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:20 下午
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public List<Long> listRoleId(Long userId) {
        return baseMapper.listRoleId(userId);
    }
}
