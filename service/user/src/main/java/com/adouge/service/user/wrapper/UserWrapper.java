package com.adouge.service.user.wrapper;

import lombok.AllArgsConstructor;
import cn.hutool.core.bean.BeanUtil;
import com.adouge.core.mybatis.support.BaseEntityWrapper;
import com.adouge.service.user.entity.User;
import com.adouge.service.user.vo.UserVO;

/**
 * 用户表 包装类,返回视图层所需的字段
 *
 * @author Vinson
 * @since 2020-06-11
 */
public class UserWrapper extends BaseEntityWrapper<User, UserVO>  {

	private UserWrapper() {
	}

	private static UserWrapper instance;

	public static UserWrapper build() {
		if (instance == null) {
			synchronized (UserWrapper.class) {
				if (instance == null) {
					instance = new UserWrapper();
				}
			}
		}
		return instance;
	}
	
	@Override
	public UserVO entityVO(User user) {
		UserVO userVO = new UserVO();
		BeanUtil.copyProperties(user, userVO);
		return userVO;
	}

}
