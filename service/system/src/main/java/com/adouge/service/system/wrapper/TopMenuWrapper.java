package com.adouge.service.system.wrapper;

import lombok.AllArgsConstructor;
import cn.hutool.core.bean.BeanUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.service.system.entity.TopMenu;
import com.adouge.service.system.vo.TopMenuVO;

/**
 * 顶部菜单表 包装类,返回视图层所需的字段
 *
 * @author Vinson
 * @since 2020-07-01
 */
public class TopMenuWrapper extends BaseEntityWrapper<TopMenu, TopMenuVO>  {

	private TopMenuWrapper() {
	}

	private static TopMenuWrapper instance;

	public static TopMenuWrapper build() {
		if (instance == null) {
			synchronized (TopMenuWrapper.class) {
				if (instance == null) {
					instance = new TopMenuWrapper();
				}
			}
		}
		return instance;
	}
	
	@Override
	public TopMenuVO entityVO(TopMenu topMenu) {
		TopMenuVO topMenuVO = new TopMenuVO();
		BeanUtil.copyProperties(topMenu, topMenuVO);
		return topMenuVO;
	}

}
