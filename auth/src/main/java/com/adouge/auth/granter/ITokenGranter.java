package com.adouge.auth.granter;


import com.adouge.service.user.entity.UserInfo;

/**
 * @author : Vinson
 * @date : 2020/5/26 8:28 下午
 */
public interface ITokenGranter {
    UserInfo grant(TokenParameter parameter);
}
