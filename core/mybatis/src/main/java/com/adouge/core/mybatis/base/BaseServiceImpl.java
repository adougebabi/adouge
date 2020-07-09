package com.adouge.core.mybatis.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 1:19 下午
 */
@Validated
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public boolean updateById(T entity) {
//        这个要设置空不然mybatisplus 不会自动填充
        entity.setUpdatedTime(null);
        entity.setUpdatedBy(null);
        return super.updateById(entity);
    }

    @Override
    public boolean deleteLogic(List<Long> ids) {
        return super.removeByIds(ids);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(this::removeById);
        return true;
    }
}
