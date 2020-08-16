package com.adouge.secure.handler;

/**
 * @author : Vinson
 * @date : 2020/8/13 1:04 下午
 */
public interface IPermissionHandler {
    boolean permissionAll();

    boolean hasPermission(String permission);
}
