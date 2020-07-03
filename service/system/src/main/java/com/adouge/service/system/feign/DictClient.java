package com.adouge.service.system.feign;

import com.adouge.core.tool.api.Result;
import com.adouge.service.system.entity.Dict;
import com.adouge.service.system.service.IDictService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/7/3 2:30 下午
 */
@RestController
@AllArgsConstructor
public class DictClient implements IDictClient{
    private final IDictService dictService;

    @Override
    public Result<List<Dict>> dictionary(String code) {
        return Result.data(dictService.listByCode(code));
    }
}
