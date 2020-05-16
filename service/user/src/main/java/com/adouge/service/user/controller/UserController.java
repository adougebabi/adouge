package com.adouge.service.user.controller;

import com.adouge.boot.controller.BaseController;
import com.adouge.core.tool.api.R;
import com.adouge.service.user.api.entity.User;
import com.adouge.service.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:05 下午
 */
@RestController
@RequiredArgsConstructor
public class UserController extends BaseController<User> {
    private final IUserService userService;

    @GetMapping("/{id}")
    public R<User> get(@PathVariable String id) {
        return R.data(userService.getById(id));
    }
}
