package com.adouge.core.mybatis.base;

import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 1:17 下午
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 逻辑上删除
     * @param ids ids
     * @return 是否成功
     */
    boolean deleteLogic(@NotEmpty List<Long> ids);
}
