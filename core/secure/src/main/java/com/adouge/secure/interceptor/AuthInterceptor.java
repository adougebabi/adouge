package com.adouge.secure.interceptor;

import cn.hutool.json.JSONUtil;
import com.adouge.core.tool.utils.WebUtil;
import com.adouge.secure.auth.AuthFun;
import com.adouge.secure.props.AuthSecure;
import com.adouge.secure.provider.HttpMethod;
import com.adouge.secure.provider.ResponseProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.NonNull;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/13 11:20 上午
 */
@Slf4j
@AllArgsConstructor
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
    private static final EvaluationContext EVALUATION_CONTEXT = new StandardEvaluationContext(new AuthFun());
    private final List<AuthSecure> authSecures;
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
//        SecureProperties secureProperties= SpringUtil.getBean(SecureProperties.class);
//        authSecures.addAll(secureProperties.getAuth());
//        FIXME 不能自动更新问题
        boolean check = this.authSecures.stream()
                .filter((authSecure) -> this.checkAuth(request, authSecure))
                .findFirst().map((authSecure) -> this.checkExpression(authSecure.getExpression()))
                .orElse(Boolean.TRUE);
        if (!check) {
            log.warn("授权认证失败，请求接口：{}，请求IP：{}，请求参数：{}", request.getRequestURI(), WebUtil.getIp(request), JSONUtil.toJsonStr(request.getParameterMap()));
            ResponseProvider.write(response);
            return false;
        } else {
            return true;
        }
    }

    private boolean checkAuth(HttpServletRequest request, AuthSecure authSecure) {
        return this.checkMethod(request, authSecure.getMethod()) && this.checkPath(request, authSecure.getPattern());
    }

    private boolean checkMethod(HttpServletRequest request, HttpMethod method) {
        return method == HttpMethod.ALL || method != null && method == HttpMethod.of(request.getMethod());
    }

    private boolean checkPath(HttpServletRequest request, String pattern) {
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 0) {
            servletPath = servletPath + pathInfo;
        }

        return PatternMatchUtils.simpleMatch(pattern, servletPath);
    }

    private boolean checkExpression(String expression) {
        Boolean result = EXPRESSION_PARSER.parseExpression(expression).getValue(EVALUATION_CONTEXT, Boolean.class);
        return result != null ? result : false;
    }
}
