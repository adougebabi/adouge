package com.adouge.auth.granter.impl;

import com.adouge.auth.granter.ITokenGranter;
import com.adouge.auth.granter.TokenParameter;
import com.adouge.core.tool.api.Result;
import com.adouge.service.user.entity.UserInfo;
import com.adouge.service.user.feign.IUserClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : Vinson
 * @date : 2020/5/26 8:43 下午
 */
@Component
@AllArgsConstructor
public class PasswordTokenGranter implements ITokenGranter {

    public static final String GRANT_TYPE = "password";
    private final IUserClient userClient;

    @Override
    public UserInfo grant(TokenParameter parameter) {
        String tenantId = parameter.getArgs().getStr("tenantId");
        String account = parameter.getArgs().getStr("account");
        String password = parameter.getArgs().getStr("password");
        String userType = parameter.getArgs().getStr("userType");

        Result<UserInfo> userInfo;
        switch (userType) {
            case "api":
                userInfo = userClient.userInfo(tenantId, account, "123456");
                break;
            default:
                userInfo = userClient.userInfo(tenantId, account, password);
                break;
        }

        return userInfo.getData();
    }
}
