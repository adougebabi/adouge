package com.adouge.gateway.controller;

import com.adouge.core.tool.api.Result;
import com.adouge.core.tool.api.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Vinson
 * @date : 2020/5/24 3:48 下午
 */
@RestController
public class FallBackController {

    @GetMapping("/fallback")
    public Result<?> fallback(){
        return Result.fail(ResultCode.GATEWAY_TIMEOUT);
    }
}
