package com.adouge.service.user.controller;

import com.adouge.boot.controller.BaseController;
import com.adouge.core.tool.api.R;
import com.adouge.service.user.api.entity.User;
import com.adouge.service.user.service.IUserService;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="演示类",description="请求参数类" )
@RequiredArgsConstructor
public class UserController extends BaseController<User> {
    private final IUserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户")
    public R<User> get(@PathVariable String id) {
        return R.data(userService.getById(id));
    }

    @PostMapping("/")
    @ApiOperation(value = "添加用户")
    public R<User> save(User user) {
        return userService.save(user)?R.success("保存成功"):R.fail("保存失败");
    }
}
