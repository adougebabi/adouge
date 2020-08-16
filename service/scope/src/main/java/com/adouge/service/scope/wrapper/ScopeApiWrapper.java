package com.adouge.service.scope.wrapper;


import cn.hutool.core.bean.BeanUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.service.scope.entity.ScopeApi;
import com.adouge.service.scope.vo.ScopeApiVO;


/**
 * 接口权限表 包装类,返回视图层所需的字段
 *
 * @author Vinson
 * @since 2020-08-13
 */
public class ScopeApiWrapper extends BaseEntityWrapper<ScopeApi, ScopeApiVO> {

	private ScopeApiWrapper() {
	}

	private static ScopeApiWrapper instance;

	public static ScopeApiWrapper build() {
		if (instance == null) {
			synchronized (ScopeApiWrapper.class) {
				if (instance == null) {
					instance = new ScopeApiWrapper();
				}
			}
		}
		return instance;
	}

	@Override
	public ScopeApiVO entityVO(ScopeApi scopeApi) {
		ScopeApiVO scopeApiVO = new ScopeApiVO();
		BeanUtil.copyProperties(scopeApi, scopeApiVO);
		return scopeApiVO;
	}

}
