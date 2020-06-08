package com.adouge.service.system.mapper;

import com.adouge.system.entity.Menu;
import com.adouge.system.vo.MenuVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/3 11:23 上午
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> listByParentId(@Param("parent_id") long parentId);

    List<MenuVO> tree();
}
