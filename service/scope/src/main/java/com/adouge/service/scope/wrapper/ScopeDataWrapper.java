package com.adouge.service.scope.wrapper;

import lombok.AllArgsConstructor;
import cn.hutool.core.bean.BeanUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.service.scope.entity.ScopeData;
import com.adouge.service.scope.vo.ScopeDataVO;

/**
 *  包装类,返回视图层所需的字段
 *
 * @author Vinson
 * @since 2020-08-15
 */
public class ScopeDataWrapper extends BaseEntityWrapper<ScopeData, ScopeDataVO>  {

	private ScopeDataWrapper() {
	}

	private static ScopeDataWrapper instance;

	public static ScopeDataWrapper build() {
		if (instance == null) {
			synchronized (ScopeDataWrapper.class) {
				if (instance == null) {
					instance = new ScopeDataWrapper();
				}
			}
		}
		return instance;
	}
	
	@Override
	public ScopeDataVO entityVO(ScopeData scopeData) {
		ScopeDataVO scopeDataVO = new ScopeDataVO();
		BeanUtil.copyProperties(scopeData, scopeDataVO);
		return scopeDataVO;
	}

}
