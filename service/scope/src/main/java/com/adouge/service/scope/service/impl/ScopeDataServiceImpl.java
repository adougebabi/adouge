package com.adouge.service.scope.service.impl;

import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.secure.AdougeUser;
import com.adouge.service.scope.entity.ScopeData;
import com.adouge.service.scope.mapper.ScopeDataMapper;
import com.adouge.service.scope.service.IScopeDataService;
import com.adouge.service.scope.vo.ScopeDataVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author Vinson
 * @since 2020-08-15
 */
@Service
@RequiredArgsConstructor
public class ScopeDataServiceImpl extends BaseServiceImpl<ScopeDataMapper, ScopeData> implements IScopeDataService {
    @Override
    public List<ScopeDataVO> grantTree(AdougeUser user) {
//        return ForestNodeMerger.merge(user.getTenantId().equals(WebConstant.ADMIN_TENANT_ID) ? baseMapper.tree() : baseMapper.treeByRole(user.getRoleId()));
        return ForestNodeMerger.merge(baseMapper.tree());
    }
}
