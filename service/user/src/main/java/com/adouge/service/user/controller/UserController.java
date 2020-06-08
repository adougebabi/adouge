package com.adouge.service.user.controller;

import com.adouge.boot.controller.BaseController;
import com.adouge.core.tool.api.Result;
import com.adouge.user.entity.User;
import com.adouge.service.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:05 下午
 */
@RestController
@Api(tags = "用户模块")
@RequiredArgsConstructor
public class UserController extends BaseController<User> {
    private final IUserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户")
    public Result<User> get(@PathVariable String id) {
        return Result.data(userService.getById(id));
    }

    @PostMapping("/")
    @ApiOperation(value = "添加用户")
    public Result<User> save(User user) {
        return userService.save(user)? Result.success("保存成功"): Result.fail("保存失败");
    }
}
