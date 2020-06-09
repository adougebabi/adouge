package com.adouge.auth.granter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.adouge.auth.granter.impl.PasswordTokenGranter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Vinson
 * @date : 2020/5/26 8:29 下午
 */
public class TokenBuilder {
    private static final Map<String, ITokenGranter> GRANTER_POOL = new ConcurrentHashMap<>();
    static {
        GRANTER_POOL.put(PasswordTokenGranter.GRANT_TYPE, SpringUtil.getBean(PasswordTokenGranter.class));
    }
    public static ITokenGranter getGranter(String grantType) {
        ITokenGranter tokenGranter = GRANTER_POOL.get(StrUtil.blankToDefault(grantType, PasswordTokenGranter.GRANT_TYPE));
        if (tokenGranter == null) {
//            throw new Exception("no grantType was found");
            return null;
        } else {
            return tokenGranter;
        }
    }

}
