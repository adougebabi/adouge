package com.adouge.service.system.wrapper;

import lombok.AllArgsConstructor;
import cn.hutool.core.bean.BeanUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.service.system.entity.Client;
import com.adouge.service.system.vo.ClientVO;

/**
 * 客户端表 包装类,返回视图层所需的字段
 *
 * @author Vinson
 * @since 2020-06-16
 */
public class ClientWrapper extends BaseEntityWrapper<Client, ClientVO>  {

	private ClientWrapper() {
	}

	private static ClientWrapper instance;

	public static ClientWrapper build() {
		if (instance == null) {
			synchronized (ClientWrapper.class) {
				if (instance == null) {
					instance = new ClientWrapper();
				}
			}
		}
		return instance;
	}
	
	@Override
	public ClientVO entityVO(Client client) {
		ClientVO clientVO = new ClientVO();
		BeanUtil.copyProperties(client, clientVO);
		return clientVO;
	}

}
