package com.adouge.service.system.wrapper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.core.tool.constant.GlobalConstant;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.service.system.entity.Dept;
import com.adouge.service.system.service.IDeptService;
import com.adouge.service.system.vo.DeptVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门表 包装类,返回视图层所需的字段
 *
 * @author Vinson
 * @since 2020-06-11
 */
public class DeptWrapper extends BaseEntityWrapper<Dept, DeptVO>  {
	private final static IDeptService deptService;

	static {
		deptService = SpringUtil.getBean(IDeptService.class);
	}

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
		if (dept.getParentId().equals(GlobalConstant.TOP_PARENT_ID)) {
			deptVO.setParentName(GlobalConstant.TOP_PARENT_NAME);
		} else {
			Dept parent = deptService.getById(dept.getParentId());
			deptVO.setParentName(parent.getDeptName());
		}
		return deptVO;
	}
	public List<DeptVO> listNodeVO(List<Dept> list) {
		return ForestNodeMerger.merge(list.stream().map(role -> BeanUtil.copyProperties(role, DeptVO.class)).collect(Collectors.toList()));
	}

}
