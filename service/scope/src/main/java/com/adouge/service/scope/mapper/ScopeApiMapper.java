package com.adouge.service.scope.mapper;

import com.adouge.service.scope.entity.ScopeApi;
import com.adouge.service.scope.vo.ScopeApiVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 接口权限表  Mapper 接口
 *
 * @author Vinson
 * @since 2020-08-13
 */
public interface ScopeApiMapper extends BaseMapper<ScopeApi> {
    List<ScopeApiVO> tree();

    List<ScopeApiVO> treeByRole(List<Long> roleId);
}
