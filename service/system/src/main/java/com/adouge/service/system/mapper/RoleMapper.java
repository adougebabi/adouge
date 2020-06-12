package com.adouge.service.system.mapper;

import com.adouge.service.system.entity.Role;
import com.adouge.service.system.vo.RoleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 角色表  Mapper 接口
 *
 * @author AdougeBabi
 * @since 2020-06-09
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<RoleVO> tree();
}
