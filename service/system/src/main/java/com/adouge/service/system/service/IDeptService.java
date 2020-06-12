package com.adouge.service.system.service;

import com.adouge.service.system.entity.Dept;
import com.adouge.core.mybatis.base.BaseService;
import com.adouge.service.system.vo.DeptVO;
import com.adouge.service.system.vo.MenuVO;

import java.util.List;

/**
 * 部门表  服务类
 *
 * @author Vinson
 * @since 2020-06-11
 */
public interface IDeptService extends BaseService<Dept> {
    /**
     * 树形列表
     * @return 树
     */
    List<DeptVO> tree();
}
