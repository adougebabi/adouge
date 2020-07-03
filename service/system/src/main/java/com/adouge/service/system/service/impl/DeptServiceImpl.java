package com.adouge.service.system.service.impl;

import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.service.system.entity.Dept;
import com.adouge.service.system.mapper.DeptMapper;
import com.adouge.service.system.service.IDeptService;
import com.adouge.service.system.vo.DeptVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门表  服务实现类
 *
 * @author Vinson
 * @since 2020-06-11
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {
    @Override
    public List<DeptVO> tree(String tenantId) {
        return ForestNodeMerger.merge(baseMapper.tree(tenantId));
    }
}
