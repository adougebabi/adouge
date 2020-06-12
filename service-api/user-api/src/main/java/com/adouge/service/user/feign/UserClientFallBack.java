package com.adouge.service.user.feign;

import com.adouge.core.tool.api.Result;
import com.adouge.service.user.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : Vinson
 * @date : 2020/5/16 3:00 下午
 */
@Slf4j
@Component
public class UserClientFallBack implements IUserClient {
    @Override
    public String userInfo() {
        return Result.fail("找不到对应的用户").toString();
    }

    @Override
    public String getTopMenu() {
        return Result.fail("找不到对应的用户").toString();
    }

    @Override
    public Result<UserInfo> userInfo(String tenantId, String account, String password) {
        if (log.isDebugEnabled()) {
            log.debug("tenantId:{},account:{},password:{}===>登陆失败", tenantId, account, password);
        }
        return Result.fail("找不到对应的用户,登陆失败");
    }

}
