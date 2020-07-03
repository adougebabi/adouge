package com.adouge.secure.client;

/**
 * @author : Vinson
 * @date : 2020/6/15 5:05 下午
 */
public interface IClientDetailsService {

    /**
     * 根据clientId获取Client详情
     *
     * @param clientId 客户端id
     * @return IClientDetails
     */
    IClientDetails loadClientByClientId(String clientId);
}
