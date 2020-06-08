package com.adouge.user.feign;

import com.adouge.core.tool.api.Result;
import com.adouge.user.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Vinson
 * @date : 2020/5/16 2:59 下午
 */
@FeignClient(fallback = UserClientFallBack.class,value = "adouge-service-user")
public interface IUserClient {
    String PREFIX = "/user";

    @Deprecated
    @GetMapping(PREFIX + "/userInfo")
    String userInfo();
    @Deprecated
    @GetMapping(PREFIX + "/getTopMenu")
    String getTopMenu();
    @GetMapping(PREFIX + "/user-info")
    Result<UserInfo> userInfo(@RequestParam("tenantId") String tenantId, @RequestParam("account") String account, @RequestParam("password") String password);
}
