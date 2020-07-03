package com.adouge.service.system.service.impl;

import com.adouge.service.system.entity.Client;
import com.adouge.service.system.vo.ClientVO;
import com.adouge.service.system.mapper.ClientMapper;
import com.adouge.service.system.service.IClientService;
import com.adouge.core.mybatis.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 客户端表  服务实现类
 *
 * @author Vinson
 * @since 2020-06-16
 */
@Service
public class ClientServiceImpl extends BaseServiceImpl<ClientMapper, Client> implements IClientService {

}
