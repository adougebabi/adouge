package com.adouge.service.scope.service.impl;

import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.secure.AdougeUser;
import com.adouge.service.scope.entity.ScopeApi;
import com.adouge.service.scope.mapper.ScopeApiMapper;
import com.adouge.service.scope.service.IScopeApiService;
import com.adouge.service.scope.vo.ScopeApiVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 接口权限表  服务实现类
 *
 * @author Vinson
 * @since 2020-08-13
 */
@Service
@RequiredArgsConstructor
public class ScopeApiServiceImpl extends BaseServiceImpl<ScopeApiMapper, ScopeApi> implements IScopeApiService {

    @Override
    public List<ScopeApiVO> grantTree(AdougeUser user) {
//        return ForestNodeMerger.merge(user.getTenantId().equals(WebConstant.ADMIN_TENANT_ID) ? baseMapper.tree() : baseMapper.treeByRole(user.getRoleId()));
        return ForestNodeMerger.merge(baseMapper.tree());
    }

}
