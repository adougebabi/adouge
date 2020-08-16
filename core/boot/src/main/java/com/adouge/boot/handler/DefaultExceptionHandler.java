package com.adouge.boot.handler;

import com.adouge.core.tool.api.IResultCode;
import com.adouge.core.tool.api.Result;
import com.adouge.core.tool.api.ResultCode;
import com.adouge.secure.exception.SecureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : Vinson
 * @date : 2020/8/13 5:13 下午
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {
    /**
     * 异常捕获
     * @param e 捕获的异常
     * @return 封装的返回对象
     **/
    @ExceptionHandler(Exception.class)
    public Result<?> handlerException(Exception e, HttpServletResponse response) {
        log.error("出现错误-->{}",e.getMessage());
        if(e instanceof SecureException){
            IResultCode resultCode = ((SecureException) e).getResultCode();
            response.setStatus(resultCode.getCode());
            return Result.fail(resultCode);
        }else if(e instanceof NullPointerException){
            log.error("空指针->",e);
        }
        response.setStatus(ResultCode.INTERNAL_SERVER_ERROR.getCode());
        return Result.fail(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
