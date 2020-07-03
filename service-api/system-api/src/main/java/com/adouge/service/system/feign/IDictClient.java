package com.adouge.service.system.feign;

import com.adouge.core.tool.api.Result;
import com.adouge.service.system.entity.Dict;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/7/3 2:27 下午
 */
@FeignClient(value = "adouge-service-system")
public interface IDictClient {
    String PREFIX = "/dict";

    /**
     * 获取字典值
     * @param code 字典码
     * @return 字典值
     */
    @GetMapping(PREFIX + "/dictionary")
    Result<List<Dict>> dictionary(String code);
}
