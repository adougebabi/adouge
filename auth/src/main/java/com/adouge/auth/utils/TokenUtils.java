package com.adouge.auth.utils;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;

import com.adouge.secure.AuthInfo;
import com.adouge.secure.TokenInfo;
import com.adouge.core.launch.constant.TokenConstant;
import com.adouge.secure.utils.SecureUtil;
import com.adouge.service.user.entity.User;
import com.adouge.service.user.entity.UserInfo;

/**
 * @author : Vinson
 * @date : 2020/5/28 10:45 下午
 */
public class TokenUtils {


    public static AuthInfo createAuthInfo(UserInfo userInfo) {
        User user = userInfo.getUser();
//TODO
        Dict param=Dict.create()
                .set(TokenConstant.TOKEN_TYPE, TokenConstant.ACCESS_TOKEN)
                .set(TokenConstant.TENANT_ID, user.getTenantId())
                .set(TokenConstant.USER_ID, user.getId())
                .set(TokenConstant.ROLE_ID, userInfo.getRoles())
                .set(TokenConstant.ACCOUNT, user.getAccount())
                .set(TokenConstant.USER_NAME, user.getUsername())
                .set(TokenConstant.ROLE_NAME, userInfo.getRoles());
        TokenInfo jwtToken = SecureUtil.createJwtToken(param, "audience", "issuer", TokenConstant.ACCESS_TOKEN);

        return AuthInfo.builder()
                .account(user.getAccount())
                .userName(user.getUsername())
                .authority(ArrayUtil.toString(userInfo.getRoles()))
                .accessToken(jwtToken.getToken())
                .expiresIn(jwtToken.getExpire())
                .refreshToken(createRefreshToken(userInfo).getToken())
                .tokenType(TokenConstant.BEARER)
                .license(TokenConstant.LICENSE_NAME)
                .build();
    }
    /**
     * 创建refreshToken
     *
     * @param userInfo 用户信息
     * @return refreshToken
     */
    private static TokenInfo createRefreshToken(UserInfo userInfo) {
        User user = userInfo.getUser();
        Dict param = Dict.create()
                .set(TokenConstant.TOKEN_TYPE, TokenConstant.REFRESH_TOKEN)
                .set(TokenConstant.USER_ID, user.getId());
        return SecureUtil.createJwtToken(param, "audience", "issuser", TokenConstant.REFRESH_TOKEN);
    }
}
