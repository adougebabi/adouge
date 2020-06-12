package com.adouge.ops.develop.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.adouge.core.develop.support.CodeGenerator;
import com.adouge.ops.develop.entity.Code;
import com.adouge.ops.develop.mapper.CodeMapper;
import com.adouge.ops.develop.service.ICodeService;
import com.adouge.core.mybatis.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成表  服务实现类
 *
 * @author Vinson
 * @since 2020-06-10
 */
@Service
public class CodeServiceImpl extends BaseServiceImpl<CodeMapper, Code> implements ICodeService {
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;

    @Override
    public void generator(Long id, HttpServletResponse response) {
        Code byId = getById(id);
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.setCodeName(byId.getCodeName());
        codeGenerator.setPackageDir(byId.getApiPath());
        codeGenerator.setServiceName(byId.getServiceName());
        codeGenerator.setPackageName(byId.getPackageName());
        codeGenerator.setPackageWebDir(byId.getWebPath());
        codeGenerator.setTablePrefix(new String[]{byId.getTablePrefix()});
        codeGenerator.setIncludeTables(new String[]{byId.getTableName()});
        codeGenerator.setHasSuperEntity(true);
        codeGenerator.setHasWrapper(byId.getWrapMode());
        codeGenerator.setSuperEntityColumns(new String[]{"id", "created_by", "created_time", "updated_by", "updated_time", "status", "is_deleted", "version"});
        codeGenerator.setUrl(url);
        codeGenerator.setDriverName(driverClassName);
        codeGenerator.setUsername(username);
        codeGenerator.setPassword(password);
        codeGenerator.run();
    }
}
