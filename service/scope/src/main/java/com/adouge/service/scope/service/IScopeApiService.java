package com.adouge.service.scope.service;


import com.adouge.core.mybatis.base.BaseService;
import com.adouge.secure.AdougeUser;
import com.adouge.service.scope.entity.ScopeApi;
import com.adouge.service.scope.vo.ScopeApiVO;

import java.util.List;

/**
 * 接口权限表  服务类
 *
 * @author Vinson
 * @since 2020-08-13
 */
public interface IScopeApiService extends BaseService<ScopeApi> {

    /**
     * 获取权限分配树形结构
     * @param user adougeuser
     * @return 树
     */
    List<ScopeApiVO> grantTree(AdougeUser user);

}
