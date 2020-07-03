package com.adouge.service.system.service.impl;

import com.adouge.core.mybatis.base.BaseServiceImpl;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.service.system.entity.Dict;
import com.adouge.service.system.mapper.DictMapper;
import com.adouge.service.system.service.IDictService;
import com.adouge.service.system.vo.DictVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典表  服务实现类
 *
 * @author Vinson
 * @since 2020-07-03
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements IDictService {

    @Override
    public List<DictVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }

    @Override
    public List<Dict> listByCode(String code) {
        return baseMapper.listByCode(code);
    }
}
