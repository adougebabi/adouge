package com.adouge.service.user.service.impl;

import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.service.user.api.entity.User;
import com.adouge.service.user.mapper.UserMapper;
import com.adouge.service.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:20 下午
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements IUserService {
}
