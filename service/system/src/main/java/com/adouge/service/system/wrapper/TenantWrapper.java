package com.adouge.service.system.wrapper;

import lombok.AllArgsConstructor;
import cn.hutool.core.bean.BeanUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.service.system.entity.Tenant;
import com.adouge.service.system.vo.TenantVO;

/**
 * 租户表 包装类,返回视图层所需的字段
 *
 * @author Vinson
 * @since 2020-06-16
 */
public class TenantWrapper extends BaseEntityWrapper<Tenant, TenantVO>  {

	private TenantWrapper() {
	}

	private static TenantWrapper instance;

	public static TenantWrapper build() {
		if (instance == null) {
			synchronized (TenantWrapper.class) {
				if (instance == null) {
					instance = new TenantWrapper();
				}
			}
		}
		return instance;
	}
	
	@Override
	public TenantVO entityVO(Tenant tenant) {
		TenantVO tenantVO = new TenantVO();
		BeanUtil.copyProperties(tenant, tenantVO);
		return tenantVO;
	}

}
