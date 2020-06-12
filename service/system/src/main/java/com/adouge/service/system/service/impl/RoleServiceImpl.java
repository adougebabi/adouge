package com.adouge.service.system.service.impl;

import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.service.system.entity.Role;
import com.adouge.service.system.mapper.RoleMapper;
import com.adouge.service.system.service.IRoleService;
import com.adouge.service.system.vo.RoleVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色表  服务实现类
 *
 * @author AdougeBabi
 * @since 2020-06-09
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public List<RoleVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }
}
