package com.adouge.service.user.controller;

import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.mybatis.support.Query;
import com.adouge.core.tool.api.Result;
import com.adouge.service.user.dto.UserDTO;
import com.adouge.service.user.entity.User;
import com.adouge.service.user.service.IUserService;
import com.adouge.service.user.vo.UserVO;
import com.adouge.service.user.wrapper.UserWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/5/15 5:05 下午
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "用户表 ", tags = "用户表 接口")
public class UserController {

    private final IUserService userService;

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入user")
    public Result<UserVO> detail(User user) {
        UserVO userVO = UserWrapper.build().entityVO(userService.getOne(Condition.getQueryWrapper(user)));
        userVO.setPassword("");
        return Result.data(userVO);
    }

    /**
     * 分页 用户表
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入user")
    public Result<IPage<UserVO>> list(User user, Query query) {
        IPage<UserVO> page = UserWrapper.build().pageVO(userService.page(Condition.getPage(query), Condition.getQueryWrapper(user)));
        for (UserVO record : page.getRecords()) {
            record.setPassword("");
        }
        return Result.data(page);
    }


    /**
     * 新增或修改 用户表
     */
    @PostMapping("/")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "新增或修改", notes = "传入user")
    public Result<?> submit(@Valid @RequestBody UserDTO user) {
        return Result.status(userService.saveUser(user));
    }

    /**
     * 删除 用户表
     */
    @DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
        return Result.status(userService.removeByIds(ids));
    }
//    /user/logout
}
