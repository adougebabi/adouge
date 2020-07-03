package com.adouge.service.user.feign;

import com.adouge.core.tool.api.Result;
import com.adouge.secure.AdougeUser;
import com.adouge.service.user.entity.UserInfo;
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
    public Result<UserInfo> userInfo(String tenantId, String account, String password) {
        return Result.data(userService.userInfo(tenantId, account, password));
    }

    @Override
    public Result<UserInfo> userInfo(Long userId) {
        return Result.data(userService.userInfo(userId));
    }

    @Override
    public Result<UserInfo> info(AdougeUser user) {
        return userInfo(user.getUserId());
    }
}
