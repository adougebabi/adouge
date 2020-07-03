package com.adouge.service.system.service.impl;

import com.adouge.service.system.entity.Tenant;
import com.adouge.service.system.vo.TenantVO;
import com.adouge.service.system.mapper.TenantMapper;
import com.adouge.service.system.service.ITenantService;
import com.adouge.core.mybatis.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 租户表  服务实现类
 *
 * @author Vinson
 * @since 2020-06-16
 */
@Service
public class TenantServiceImpl extends BaseServiceImpl<TenantMapper, Tenant> implements ITenantService {

}
