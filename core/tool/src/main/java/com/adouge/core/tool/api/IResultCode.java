package com.adouge.core.tool.api;

import java.io.Serializable;

/**
 * @author : Vinson
 * @date : 2020/5/15 10:09 上午
 */
public interface IResultCode extends Serializable {

    /**
     * 获取状态码
     *
     * @return code
     */
    int getCode();

    /**
     * 获取状态信息
     *
     * @return message
     */
    String getMessage();
}
