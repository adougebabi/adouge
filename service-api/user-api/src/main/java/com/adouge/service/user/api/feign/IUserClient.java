package com.adouge.service.user.api.feign;

import com.adouge.core.tool.api.R;
import com.adouge.service.user.api.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : Vinson
 * @date : 2020/5/16 2:59 下午
 */
@FeignClient(fallback = UserClientFallBack.class,value = "user-service")
public interface IUserClient {
    String PREFIX="/user";

    /**
     * 根据id获取用户
     * @param id id
     * @return R
     */
    @GetMapping(PREFIX+"/{id}")
    R<User> getById(@PathVariable long id);

}
