package com.adouge.service.user.feign;

import com.adouge.core.tool.api.R;
import com.adouge.service.user.api.entity.User;
import com.adouge.service.user.api.feign.IUserClient;
import com.adouge.service.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Vinson
 * @date : 2020/5/16 3:34 下午
 */
@RequiredArgsConstructor
@RestController
public class UserClient implements IUserClient {
    private final IUserService userService;
    @Override
    public R<User> getById(long id) {
        return R.data(userService.getById(id));
    }
}
