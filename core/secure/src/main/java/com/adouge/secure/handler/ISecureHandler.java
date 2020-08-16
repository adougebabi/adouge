package com.adouge.secure.handler;


import com.adouge.secure.props.AuthSecure;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/13 1:22 下午
 */
public interface ISecureHandler {
    HandlerInterceptorAdapter tokenInterceptor();

    HandlerInterceptorAdapter authInterceptor(List<AuthSecure> authSecures);

    HandlerInterceptorAdapter clientInterceptor(String clientId);
}
