package com.adouge.secure.handler.impl;

import cn.hutool.core.util.ArrayUtil;
import com.adouge.core.tool.utils.WebUtil;
import com.adouge.secure.AdougeUser;
import com.adouge.secure.constant.PermissionConstant;
import com.adouge.secure.handler.IPermissionHandler;
import com.adouge.secure.utils.CacheUtil;
import com.adouge.secure.utils.SecureUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/13 1:33 下午
 */
@AllArgsConstructor
@Slf4j
public class PermissionHandler implements IPermissionHandler {
    private  final String SCOPE_CACHE_CODE = "apiScope:code:";
    private final String CACHE_PREFIX = "adouge:sys";
    private final JdbcTemplate jdbcTemplate;
    @Override
    public boolean permissionAll() {
        HttpServletRequest request = WebUtil.getRequest();
        AdougeUser user = SecureUtil.getUser();
        if (request != null && user != null) {
            String uri = request.getRequestURI();
            List<String> paths = (List<String>) this.permissionPath(user.getRoleId());
            if (paths.size() == 0) {
                return false;
            } else {
                return paths.stream().anyMatch(uri::contains);
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean hasPermission(String permission) {
        return false;
    }

    private List<?> permissionPath(List<Long> roleIds) {
        List<?> permissions = CacheUtil.get(CACHE_PREFIX, SCOPE_CACHE_CODE, ArrayUtil.toString(roleIds), List.class);
        if (permissions == null) {
            permissions = this.jdbcTemplate.queryForList(PermissionConstant.permissionAllStatement(roleIds.size()), roleIds.toArray(), String.class);
            CacheUtil.put(CACHE_PREFIX, SCOPE_CACHE_CODE, ArrayUtil.toString(roleIds), permissions);
        }

        return permissions;
    }
}
