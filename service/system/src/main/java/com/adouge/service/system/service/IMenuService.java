package com.adouge.service.system.service;

import com.adouge.core.mybatis.base.BaseService;
import com.adouge.system.entity.Menu;
import com.adouge.system.vo.MenuVO;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/3 11:25 上午
 */
public interface IMenuService extends BaseService<Menu> {

    List<MenuVO> routes();

    List<MenuVO> listByParentId(long id);

    List<MenuVO> tree();

}
