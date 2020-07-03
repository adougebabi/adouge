package com.adouge.service.system.mapper;

import com.adouge.service.system.entity.Dept;
import com.adouge.service.system.vo.DeptVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 部门表  Mapper 接口
 *
 * @author Vinson
 * @since 2020-06-11
 */
public interface DeptMapper extends BaseMapper<Dept> {
    List<DeptVO> tree(String tenantId);
}
