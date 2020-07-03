package com.adouge.core.launch.constant;

/**
 * @author : Vinson
 * @date : 2020/5/16 2:47 下午
 */
public interface AppConstant {
    String BASE_PACKAGES="com.adouge";
    /**
     * 开发环境
     */
    String DEV_CODE = "dev";
    /**
     * 生产环境
     */
    String PROD_CODE = "prod";
    /**
     * 测试环境
     */
    String TEST_CODE = "test";

    /**
     * 代码部署于 linux 上，工作默认为 mac
     */
    String OS_NAME_LINUX = "LINUX";

    String APPLICATION_NAME_PREFIX = "adouge-";

    /**
     * 顶层id
     */
    Long TOP_PARENT_ID=0L;

    /**
     * 顶层名称
     */
    String TOP_PARENT_NAME = "顶级";

}
