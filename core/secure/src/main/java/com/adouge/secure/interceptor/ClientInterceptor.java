package com.adouge.secure.interceptor;


import cn.hutool.json.JSONUtil;
import com.adouge.core.tool.utils.WebUtil;
import com.adouge.secure.AdougeUser;
import com.adouge.secure.provider.ResponseProvider;
import com.adouge.secure.utils.SecureUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Vinson
 * @date : 2020/6/15 1:03 下午
 */
@Slf4j
@AllArgsConstructor
public class ClientInterceptor extends HandlerInterceptorAdapter {
    private final String clientId;
    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request,@Nonnull HttpServletResponse response,@Nonnull Object handler) {
        AdougeUser user = SecureUtil.getUser();
        if (user != null &&clientId.equalsIgnoreCase(SecureUtil.getClientIdFromHeader()) && clientId.equalsIgnoreCase(user.getClientId())) {
            return true;
        } else {
            log.warn("客户端认证失败，请求接口：{}，请求IP：{}，请求参数：{}", request.getRequestURI(), WebUtil.getIp(request), JSONUtil.toJsonStr(request.getParameterMap()));
            ResponseProvider.write(response);
            return false;
        }
    }
}
