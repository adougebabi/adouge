package com.adouge.service.user.service;

import com.adouge.service.user.entity.UserDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:19 下午
 */
public interface IUserDeptService extends IService<UserDept> {
    /**
     * 获取所有部门Id
     * @param userId 用户id
     * @return 部门id
     */
    List<Long> listDeptId(Long userId);

    /**
     * 获取所有部门Name
     * @param userId 用户id
     * @return 部门Name
     */
    List<String> listDeptName(Long userId);
}
