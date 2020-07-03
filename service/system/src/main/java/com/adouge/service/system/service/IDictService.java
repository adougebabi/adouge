package com.adouge.service.system.service;

import com.adouge.core.mybatis.base.BaseService;
import com.adouge.service.system.entity.Dict;
import com.adouge.service.system.vo.DictVO;

import java.util.List;

/**
 * 字典表  服务类
 *
 * @author Vinson
 * @since 2020-07-03
 */
public interface IDictService extends BaseService<Dict> {

    /**
     * 获取字典树
     * @return 字典树
     */
    List<DictVO> tree();

    /**
     * 通过字典码获取字典
     * @param code 字典码
     * @return 字典
     */
    List<Dict> listByCode(String code);
}
