package com.adouge.service.system.controller;

import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.tool.api.Result;
import com.adouge.service.system.service.IMenuService;
import com.adouge.service.system.wrapper.MenuWrapper;
import com.adouge.service.system.entity.Menu;
import com.adouge.service.system.vo.MenuVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author : Vinson
 * @date : 2020/6/2 10:21 下午
 */
@Slf4j
@RestController
@Api(tags = "菜单模块")
@AllArgsConstructor
@RequestMapping("menu")
public class MenuController {

    private final IMenuService service;



    @GetMapping("/routes")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取路由")
    public Result<List<MenuVO>> routes() {
        return Result.data(service.routes());
    }

    @GetMapping("/detail")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "详情", notes = "传入menu")
    public Result<MenuVO> detail(Menu menu) {
        return Result.data(MenuWrapper.build().entityVO(service.getOne(Condition.getQueryWrapper(menu))));
    }
    @GetMapping("/list")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取列表数据")
    public Result<List<MenuVO>> list(@ApiIgnore @RequestParam Map<String, Object> menu) {
        return Result.data(MenuWrapper.build().listNodeVO(service.list(Condition.getQueryWrapper(menu, Menu.class).lambda()
                .orderByAsc(Menu::getSort))));
    }

    @PostMapping("/")
//    @PreAuth(RoleConstant.HAS_ROLE_ADMIN)
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增或修改", notes = "传入menu")
    public Result<?> submit(@Valid @RequestBody Menu menu) {
        return Result.status(service.saveOrUpdate(menu));
    }


    @DeleteMapping("/{ids}")
//    @PreAuth(RoleConstant.HAS_ROLE_ADMIN)
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入ids")
    public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
        return Result.status(service.removeByIds(ids));
    }

    @GetMapping("/tree")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "获取树")
    public Result<?> tree() {
        return Result.data(service.tree());
    }
}
