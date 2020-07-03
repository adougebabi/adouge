package com.adouge.service.system.mapper;

import com.adouge.service.system.entity.Dict;
import com.adouge.service.system.vo.DictVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 字典表  Mapper 接口
 *
 * @author Vinson
 * @since 2020-07-03
 */
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 生成树
     * @return 树
     */
    List<DictVO> tree();

    List<Dict> listByCode(String code);
}
