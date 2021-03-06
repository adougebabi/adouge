package com.adouge.core.tool.constant;

/**
 * 常量
 * @author : Vinson
 * @date : 2020/5/15 4:55 下午
 */
public interface GlobalConstant {
    /**
     * 删除状态[0:正常,1:删除]
     */
    int DB_NOT_DELETED = 0;
    int DB_IS_DELETED = 1;

    /**
     * 默认为空消息
     */
    String DEFAULT_NULL_MESSAGE = "暂无数据";
    /**
     * 默认成功消息
     */
    String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    /**
     * 默认失败消息
     */
    String DEFAULT_FAILURE_MESSAGE = "操作失败";
    /**
     * 默认未授权消息
     */
    String DEFAULT_UNAUTHORIZED_MESSAGE = "签名认证失败";

    /**
     * 角色
     */
    String ADMIN = "administrator";
    /**
     * 顶层id
     */
    Long TOP_PARENT_ID=0L;

    /**
     * 顶层名称
     */
    String TOP_PARENT_NAME = "顶级";

}
