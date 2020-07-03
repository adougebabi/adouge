package com.adouge.core.tool.constant;

/**
 * @author : Vinson
 * @date : 2020/6/15 1:15 下午
 */
public interface WebConstant {
    /**
     * 编码
     */
    String UTF_8 = "UTF-8";

    /**
     * contentType
     */
    String CONTENT_TYPE_NAME = "Content-type";

    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json;charset=utf-8";

    /**
     * 角色前缀
     */
    String SECURITY_ROLE_PREFIX = "ROLE_";

    /**
     * 主键字段名
     */
    String DB_PRIMARY_KEY = "id";

    /**
     * 业务状态[1:正常]
     */
    int DB_STATUS_NORMAL = 1;


    /**
     * 删除状态[0:正常,1:删除]
     */
    int DB_NOT_DELETED = 0;
    int DB_IS_DELETED = 1;

    /**
     * 用户锁定状态
     */
    int DB_ADMIN_NON_LOCKED = 0;
    int DB_ADMIN_LOCKED = 1;

    /**
     * 管理员对应的租户ID
     */
    String ADMIN_TENANT_ID = "000000";

    /**
     * 超级管理员的id
     */
    long ADMIN_USER_ID=1;

    /**
     * 日志默认状态
     */
    String LOG_NORMAL_TYPE = "1";
}
