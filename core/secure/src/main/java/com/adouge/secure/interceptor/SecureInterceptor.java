package com.adouge.secure.interceptor;

import cn.hutool.json.JSONUtil;
import com.adouge.core.tool.api.Result;
import com.adouge.core.tool.api.ResultCode;
import com.adouge.core.tool.constant.WebConstant;
import com.adouge.core.tool.utils.WebUtil;
import com.adouge.secure.utils.SecureUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

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
            Result<?> result = Result.fail(ResultCode.UN_AUTHORIZED);
            response.setCharacterEncoding(WebConstant.UTF_8);
            response.setHeader(WebConstant.CONTENT_TYPE_NAME, MediaType.APPLICATION_JSON_VALUE);
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
