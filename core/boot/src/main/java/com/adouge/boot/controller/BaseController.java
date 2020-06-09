package com.adouge.boot.controller;

import com.adouge.core.tool.api.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Vinson
 * @date : 2020/5/15 4:38 下午
 */
public class BaseController<T> {
    @Autowired
    private HttpServletRequest request;
    /**
     * 获取request
     *
     * @return request
     */
    protected HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 获取当前用户
     * @return user
     */
    protected Object getUser() {
// TODO 返回user
        return null;
    }

    /**
     * 返回Result
     *
     * @param data 数据
     * @return Result
     */
    protected Result<T> data(T data) {
        return Result.data(data);
    }

    /**
     * 返回Result
     *
     * @param data 数据
     * @param msg  消息
     * @return Result
     */
    protected Result<T> data(T data, String msg) {
        return Result.data(data, msg);
    }

    /**
     * 返回Result
     *
     * @param data 数据
     * @param msg  消息
     * @param code 状态码
     * @param <T>  T 泛型标记
     * @return Result
     */
    protected Result<T> data(T data, String msg, int code) {
        return Result.data(code, data, msg);
    }

    /**
     * 返回Result
     *
     * @param msg 消息
     * @return Result
     */
    protected Result<?> success(String msg) {
        return Result.success(msg);
    }

    /**
     * 返回Result
     *
     * @param msg 消息
     * @return Result
     */
    protected Result<?> fail(String msg) {
        return Result.fail(msg);
    }

    /**
     * 返回Result
     *
     * @param flag 是否成功
     * @return Result
     */
    protected Result<?> status(boolean flag) {
        return Result.status(flag);
    }


}
