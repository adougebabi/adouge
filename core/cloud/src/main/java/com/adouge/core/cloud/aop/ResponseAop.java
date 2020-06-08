package com.adouge.core.cloud.aop;

import com.adouge.core.tool.api.Result;
import com.adouge.core.tool.utils.WebUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author : Vinson
 * @date : 2020/5/31 8:05 下午
 */
@Aspect
@Component
public class ResponseAop {

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)||"+
            "@annotation(org.springframework.web.bind.annotation.PutMapping)||"+
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)"
    )
    public Object afterReturn(ProceedingJoinPoint pjp){
        Object proceed= Result.fail("服务请求失败");
        try {
            proceed = pjp.proceed();
            if(proceed instanceof Result){
                Objects.requireNonNull(WebUtil.getResponse()).setStatus(((Result) proceed).getCode());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }
}
