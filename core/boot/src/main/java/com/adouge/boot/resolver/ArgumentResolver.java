package com.adouge.boot.resolver;

import com.adouge.secure.AdougeUser;
import com.adouge.secure.utils.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author : Vinson
 * @date : 2020/6/15 4:01 下午
 */
@Slf4j
public class ArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(AdougeUser.class);
    }

    /**
     * 出参设置
     *
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(@NonNull MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  @NonNull NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        return SecureUtil.getUser();
    }
}
