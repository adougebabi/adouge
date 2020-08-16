package com.adouge.service.user.mapper;

import com.adouge.service.user.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:20 下午
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 获取所有角色Id
     * @param userId 用户id
     * @return 角色id
     */
    List<Long> listRoleId(Long userId);

    /**
     * 获取所有角色Name
     * @param userId 用户id
     * @return 角色Name
     */
    List<String> listRoleName(Long userId);
}
