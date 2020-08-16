package com.adouge.service.scope.mapper;

import com.adouge.service.scope.entity.ScopeData;
import com.adouge.service.scope.vo.ScopeDataVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 *   Mapper 接口
 *
 * @author Vinson
 * @since 2020-08-15
 */
public interface ScopeDataMapper extends BaseMapper<ScopeData> {
    List<ScopeDataVO> tree();

    List<ScopeDataVO> treeByRole(List<Long> roleId);
}
