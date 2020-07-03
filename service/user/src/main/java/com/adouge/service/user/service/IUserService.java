package com.adouge.service.user.service;

import com.adouge.core.mybatis.base.BaseService;
import com.adouge.service.user.dto.UserDTO;
import com.adouge.service.user.entity.User;
import com.adouge.service.user.entity.UserInfo;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:19 下午
 */
public interface IUserService extends BaseService<User> {
    /**
     * 查询用户信息
     * @param tenantId 租户id
     * @param account 账号
     * @param password 密码
     * @return 用户信息
     */
    UserInfo userInfo(String tenantId, String account, String password);
    /**
     * 查询用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    UserInfo userInfo(Long userId);

    /**
     * 保存用户
     * @param user 用户信息
     * @return 是否成功
     */
    boolean saveUser(UserDTO user);
}
