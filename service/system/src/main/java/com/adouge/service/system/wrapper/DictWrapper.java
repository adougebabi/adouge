package com.adouge.service.system.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.core.tool.node.ForestNodeMerger;
import com.adouge.service.system.entity.Dict;
import com.adouge.service.system.vo.DictVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典表 包装类,返回视图层所需的字段
 *
 * @author Vinson
 * @since 2020-07-03
 */
public class DictWrapper extends BaseEntityWrapper<Dict, DictVO>  {

	private DictWrapper() {
	}

	private static DictWrapper instance;

	public static DictWrapper build() {
		if (instance == null) {
			synchronized (DictWrapper.class) {
				if (instance == null) {
					instance = new DictWrapper();
				}
			}
		}
		return instance;
	}

	@Override
	public DictVO entityVO(Dict dict) {
		DictVO dictVO = new DictVO();
		BeanUtil.copyProperties(dict, dictVO);
		return dictVO;
	}
	public List<DictVO> listNodeVO(List<Dict> list) {
		return ForestNodeMerger.merge(list.stream().map(dict -> BeanUtil.copyProperties(dict, DictVO.class)).collect(Collectors.toList()));
	}

}
