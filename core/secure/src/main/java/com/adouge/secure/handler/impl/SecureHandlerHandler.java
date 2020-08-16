package com.adouge.secure.handler.impl;

import com.adouge.secure.handler.ISecureHandler;
import com.adouge.secure.interceptor.AuthInterceptor;
import com.adouge.secure.interceptor.ClientInterceptor;
import com.adouge.secure.interceptor.SecureInterceptor;
import com.adouge.secure.props.AuthSecure;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/13 1:23 下午
 */
public class SecureHandlerHandler implements ISecureHandler {
    @Override
    public HandlerInterceptorAdapter tokenInterceptor() {
        return new SecureInterceptor();
    }

    @Override
    public HandlerInterceptorAdapter authInterceptor(List<AuthSecure> authSecures) {
        return new AuthInterceptor(authSecures);
    }

    @Override
    public HandlerInterceptorAdapter clientInterceptor(String clientId) {
        return new ClientInterceptor(clientId);
    }
}
