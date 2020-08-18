package com.adouge.service.scope.controller;

import com.adouge.core.tool.api.Result;
import com.adouge.service.scope.dto.RoleScopeDTO;
import com.adouge.service.scope.service.IRoleScopeService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/8/16 7:28 下午
 */
@RestController
@AllArgsConstructor
@RequestMapping("/scope")
public class ScopeController {
    private final IRoleScopeService roleScopeService;

    @GetMapping("/role-tree-keys/{type}/{roleIds}")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "角色所分配的树", notes = "角色所分配的树")
    public Result<List<String>> roleTreeKeys(@PathVariable String roleIds, @PathVariable int type) {
        return Result.data(roleScopeService.roleTreeKeys(roleIds,type));
    }

    @PostMapping("/grant")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
    public Result<?> grant(@RequestBody RoleScopeDTO dto) {
        return Result.status(roleScopeService.grant(dto));
    }

}
