package com.adouge.secure.aspect;


import cn.hutool.core.util.StrUtil;
import com.adouge.core.tool.api.ResultCode;
import com.adouge.core.tool.utils.ClassUtil;
import com.adouge.secure.annotation.PreAuth;
import com.adouge.secure.auth.AuthFun;
import com.adouge.secure.exception.SecureException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.MethodParameter;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author : Vinson
 * @date : 2020/8/13 11:19 下午
 */
@Aspect
@Component
public class AuthAspect implements ApplicationContextAware {
    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Around("@annotation(com.adouge.secure.annotation.PreAuth) || @within(com.adouge.secure.annotation.PreAuth)")
    public Object preAuth(ProceedingJoinPoint point) throws Throwable {
        if (this.handleAuth(point)) {
            return point.proceed();
        } else {
            throw new SecureException(ResultCode.UN_AUTHORIZED);
        }
    }

    private boolean handleAuth(ProceedingJoinPoint point) {
        MethodSignature ms = (MethodSignature)point.getSignature();
        Method method = ms.getMethod();
        PreAuth preAuth = ClassUtil.getAnnotation(method, PreAuth.class);
        String condition = preAuth.value();
        if (StrUtil.isNotBlank(condition)) {
            Expression expression = EXPRESSION_PARSER.parseExpression(condition);
            Object[] args = point.getArgs();
            StandardEvaluationContext context = this.getEvaluationContext(method, args);
            return expression.getValue(context, Boolean.class);
        } else {
            return false;
        }
    }

    private StandardEvaluationContext getEvaluationContext(Method method, Object[] args) {
        StandardEvaluationContext context = new StandardEvaluationContext(new AuthFun());
        context.setBeanResolver(new BeanFactoryResolver(this.applicationContext));

        for(int i = 0; i < args.length; ++i) {
            MethodParameter methodParam = ClassUtil.getMethodParameter(method, i);
            context.setVariable(methodParam.getParameterName(), args[i]);
        }

        return context;
    }
}
