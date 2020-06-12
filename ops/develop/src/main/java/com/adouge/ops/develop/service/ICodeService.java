package com.adouge.ops.develop.service;

import com.adouge.ops.develop.entity.Code;
import com.adouge.core.mybatis.base.BaseService;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成表  服务类
 *
 * @author Vinson
 * @since 2020-06-10
 */
public interface ICodeService extends BaseService<Code> {

    void generator(Long id, HttpServletResponse response);
}
