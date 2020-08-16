package com.adouge.core.datascope.handler;

import com.adouge.core.datascope.model.DataScopeModel;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/13 10:05 下午
 */
public interface IScopeModelHandler {

    DataScopeModel getDataScopeByMapper(String mapperId, List<Long> roleIds);

    DataScopeModel getDataScopeByCode(String code);

    List<Long> getDeptAncestors(Long deptId);
}
