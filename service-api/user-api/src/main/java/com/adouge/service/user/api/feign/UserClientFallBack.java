package com.adouge.service.user.api.feign;

import com.adouge.core.tool.api.R;
import com.adouge.service.user.api.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author : Vinson
 * @date : 2020/5/16 3:00 下午
 */
@Component
public class UserClientFallBack implements IUserClient{
    @Override
    public R<User> getById(long id) {
        return R.fail("找不到对应的用户");
    }
}
