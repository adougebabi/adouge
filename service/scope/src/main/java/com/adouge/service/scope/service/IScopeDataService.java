package com.adouge.service.scope.service;

import com.adouge.core.mybatis.base.BaseService;
import com.adouge.secure.AdougeUser;
import com.adouge.service.scope.entity.ScopeData;
import com.adouge.service.scope.vo.ScopeDataVO;

import java.util.List;

/**
 *   服务类
 *
 * @author Vinson
 * @since 2020-08-15
 */
public interface IScopeDataService extends BaseService<ScopeData> {
    /**
     * 获取权限分配树形结构
     * @param user adougeuser
     * @return 树
     */
    List<ScopeDataVO> grantTree(AdougeUser user);
}
