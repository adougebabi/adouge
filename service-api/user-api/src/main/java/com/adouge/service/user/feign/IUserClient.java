package com.adouge.service.user.feign;

import com.adouge.core.tool.api.Result;
import com.adouge.secure.AdougeUser;
import com.adouge.service.user.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Vinson
 * @date : 2020/5/16 2:59 下午
 */
@FeignClient(value = "adouge-service-user")
public interface IUserClient {
    String PREFIX = "/user";

    @GetMapping(PREFIX + "/userInfo")
    Result<UserInfo> userInfo(@RequestParam("tenantId") String tenantId, @RequestParam("account") String account, @RequestParam("password") String password);
    @GetMapping(PREFIX + "/userInfoById")
    Result<UserInfo> userInfo(@RequestParam("userId") Long userId);
    @GetMapping(PREFIX + "/info")
    Result<UserInfo> info(AdougeUser user);
}
