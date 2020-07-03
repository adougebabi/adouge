package com.adouge.secure.interceptor;


import cn.hutool.json.JSONUtil;
import com.adouge.core.tool.api.Result;
import com.adouge.core.tool.api.ResultCode;
import com.adouge.core.tool.constant.WebConstant;
import com.adouge.core.tool.utils.WebUtil;
import com.adouge.secure.AdougeUser;
import com.adouge.secure.utils.SecureUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author : Vinson
 * @date : 2020/6/15 1:03 下午
 */
@Slf4j
@AllArgsConstructor
public class ClientInterceptor extends HandlerInterceptorAdapter {
    private final String clientId;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        AdougeUser user = SecureUtil.getUser();
        if (user != null &&clientId.equalsIgnoreCase(SecureUtil.getClientIdFromHeader()) && clientId.equalsIgnoreCase(user.getClientId())) {
            return true;
        } else {
            log.warn("客户端认证失败，请求接口：{}，请求IP：{}，请求参数：{}", request.getRequestURI(), WebUtil.getIp(request), JSONUtil.toJsonStr(request.getParameterMap()));
            Result<?> result = Result.fail(ResultCode.UN_AUTHORIZED);
            response.setHeader(WebConstant.CONTENT_TYPE_NAME, MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(WebConstant.UTF_8);
            response.setStatus(HttpServletResponse.SC_OK);
            try {
                response.getWriter().write(Objects.requireNonNull(JSONUtil.toJsonStr(result)));
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
            return false;
        }
    }
}
