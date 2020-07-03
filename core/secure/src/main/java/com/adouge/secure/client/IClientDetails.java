package com.adouge.secure.client;

import java.io.Serializable;

/**
 * @author : Vinson
 * @date : 2020/6/15 5:03 下午
 */
public interface IClientDetails extends Serializable {
    /**
     * 客户端id.
     *
     * @return String.
     */
    String getClientId();

    /**
     * 客户端密钥.
     *
     * @return String.
     */
    String getClientSecret();

    /**
     * 客户端token过期时间
     *
     * @return Integer
     */
    Integer getAccessTokenTime();

    /**
     * 客户端刷新token过期时间
     *
     * @return Integer
     */
    Integer getRefreshTokenTime();
}
