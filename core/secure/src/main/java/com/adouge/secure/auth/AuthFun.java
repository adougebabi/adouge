package com.adouge.secure.auth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.adouge.secure.AdougeUser;
import com.adouge.secure.handler.IPermissionHandler;
import com.adouge.secure.utils.SecureUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/13 11:26 上午
 */
public class AuthFun {
    private static IPermissionHandler permissionHandler;

    private static IPermissionHandler getPermissionHandler() {
        if (permissionHandler == null) {
            permissionHandler = SpringUtil.getBean(IPermissionHandler.class);
        }

        return permissionHandler;
    }

    public boolean permissionAll() {
        return getPermissionHandler().permissionAll();
    }

    public boolean hasPermission(String permission) {
        return getPermissionHandler().hasPermission(permission);
    }

    public boolean permitAll() {
        return true;
    }

    public boolean denyAll() {
        return this.hasRole("admin");
    }

    public boolean hasAuth() {
        return ObjectUtil.isNotNull(SecureUtil.getUser());
    }

    public boolean hasTimeAuth(Integer start, Integer end) {
        int hour = DateUtil.hour(new Date(), false);
        return hour >= start && hour <= end;
    }

    public boolean hasRole(String role) {
        return this.hasAnyRole(role);
    }

    public boolean hasAllRole(String... role) {
        return Arrays.stream(role).filter(this::hasRole).count() == role.length;
    }

    public boolean hasAnyRole(String... role) {
        AdougeUser user = SecureUtil.getUser();
        if (user == null) {
            return false;
        } else {
            List<String> roleName = user.getRoleName();
            if (CollUtil.isEmpty(roleName)) {
                return false;
            } else {
                return Arrays.stream(role).anyMatch(roleName::contains);
            }
        }
    }
}
