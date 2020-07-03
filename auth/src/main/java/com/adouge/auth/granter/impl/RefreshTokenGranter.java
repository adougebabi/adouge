package com.adouge.auth.granter.impl;

import cn.hutool.core.util.StrUtil;
import com.adouge.auth.granter.ITokenGranter;
import com.adouge.auth.granter.TokenParameter;
import com.adouge.core.launch.constant.TokenConstant;
import com.adouge.core.tool.api.Result;
import com.adouge.secure.utils.SecureUtil;
import com.adouge.service.user.entity.UserInfo;
import com.adouge.service.user.feign.IUserClient;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author : Vinson
 * @date : 2020/6/17 3:29 下午
 */
@Slf4j
@Component
@AllArgsConstructor
public class RefreshTokenGranter implements ITokenGranter {
    public static final String GRANT_TYPE = "refresh_token";

    private final IUserClient userClient;

    @Override
    public UserInfo grant(TokenParameter tokenParameter) {
        String grantType = tokenParameter.getArgs().getStr("grantType");
        String refreshToken = tokenParameter.getArgs().getStr("refreshToken");
        UserInfo userInfo = null;
        if (StrUtil.isNotBlank(grantType)&&StrUtil.isNotBlank(refreshToken) && grantType.equals(TokenConstant.REFRESH_TOKEN)) {
            Claims claims = SecureUtil.parseJWT(refreshToken);
            String tokenType = Objects.requireNonNull(claims).get(TokenConstant.TOKEN_TYPE).toString();
            if (tokenType.equals(TokenConstant.REFRESH_TOKEN)) {
                Result<UserInfo> result = userClient.userInfo(Long.valueOf(claims.get(TokenConstant.USER_ID).toString()));
                userInfo = result.isSuccess() ? result.getData() : null;
            }
        }
        return userInfo;
    }
}
