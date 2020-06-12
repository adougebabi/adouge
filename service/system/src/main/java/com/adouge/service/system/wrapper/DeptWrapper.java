package com.adouge.service.system.wrapper;

import lombok.AllArgsConstructor;
import cn.hutool.core.bean.BeanUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.service.system.entity.Dept;
import com.adouge.service.system.vo.DeptVO;

/**
 * 部门表 包装类,返回视图层所需的字段
 *
 * @author Vinson
 * @since 2020-06-11
 */
public class DeptWrapper extends BaseEntityWrapper<Dept, DeptVO>  {

	private DeptWrapper() {
	}

	private static DeptWrapper instance;

	public static DeptWrapper build() {
		if (instance == null) {
			synchronized (DeptWrapper.class) {
				if (instance == null) {
					instance = new DeptWrapper();
				}
			}
		}
		return instance;
	}
	
	@Override
	public DeptVO entityVO(Dept dept) {
		DeptVO deptVO = new DeptVO();
		BeanUtil.copyProperties(dept, deptVO);
		return deptVO;
	}

}
