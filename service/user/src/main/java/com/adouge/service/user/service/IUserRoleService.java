package com.adouge.service.user.service;

import com.adouge.service.user.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:19 下午
 */
public interface IUserRoleService extends IService<UserRole> {
    /**
     * 获取所有角色Id
     * @param userId 用户id
     * @return 角色id
     */
    List<Long> listRoleId(Long userId);
}
