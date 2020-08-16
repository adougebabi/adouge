package com.adouge.service.user.mapper;

import com.adouge.service.user.entity.UserDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:20 下午
 */
public interface UserDeptMapper extends BaseMapper<UserDept> {
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
