package com.adouge.service.user.service.impl;

import com.adouge.service.user.entity.UserDept;
import com.adouge.service.user.mapper.UserDeptMapper;
import com.adouge.service.user.service.IUserDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:20 下午
 */
@Service
public class UserDeptServiceImpl extends ServiceImpl<UserDeptMapper, UserDept> implements IUserDeptService {
    @Override
    public List<Long> listDeptId(Long userId) {
        return baseMapper.listDeptId(userId);
    }

    @Override
    public List<String> listDeptName(Long userId) {
        return baseMapper.listDeptName(userId);
    }
}
