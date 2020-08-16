package com.adouge.secure.interceptor;

import cn.hutool.json.JSONUtil;
import com.adouge.core.tool.utils.WebUtil;
import com.adouge.secure.provider.ResponseProvider;
import com.adouge.secure.utils.SecureUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Vinson
 * @date : 2020/6/15 1:38 下午
 */
@Slf4j
@AllArgsConstructor
public class SecureInterceptor extends HandlerInterceptorAdapter {
    @Override

    public boolean preHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler) {
        if (null != SecureUtil.getUser()) {
            return true;
        } else {
            log.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}", request.getRequestURI(), WebUtil.getIp(request), JSONUtil.toJsonStr(request.getParameterMap()));
            ResponseProvider.write(response);
            return false;
        }
    }
}
