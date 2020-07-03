package com.adouge.service.system.controller;

import com.adouge.boot.controller.BaseController;
import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.mybatis.support.Query;
import com.adouge.core.tool.api.Result;
import com.adouge.secure.AdougeUser;
import com.adouge.service.system.dto.TopMenuMenuDTO;
import com.adouge.service.system.entity.TopMenu;
import com.adouge.service.system.service.ITopMenuService;
import com.adouge.service.system.vo.TopMenuVO;
import com.adouge.service.system.wrapper.TopMenuWrapper;
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
 * 顶部菜单表  控制器
 *
 * @author Vinson
 * @since 2020-07-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/topMenu")
@Api(value = "顶部菜单表 ", tags = "顶部菜单表 接口")
public class TopMenuController extends BaseController<TopMenu> {

	private final ITopMenuService topMenuService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入topMenu")
	public Result<TopMenuVO> detail(TopMenu topMenu) {
		return Result.data(TopMenuWrapper.build().entityVO(topMenuService.getOne(Condition.getQueryWrapper(topMenu))));
	}

	/**
	 * 分页 顶部菜单表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入topMenu")
	public Result<IPage<TopMenuVO>> list(TopMenu topMenu, Query query) {
		return Result.data(TopMenuWrapper.build().pageVO(topMenuService.page(Condition.getPage(query), Condition.getQueryWrapper(topMenu))));
	}


	/**
	 * 新增或修改 顶部菜单表
	 */
	@PostMapping("/")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增或修改", notes = "传入topMenu")
	public Result<?> submit(@Valid @RequestBody TopMenu topMenu) {
		return Result.status(topMenuService.saveOrUpdate(topMenu));
	}

	/**
	 * 删除 顶部菜单表
	 */
	@DeleteMapping("/{ids}")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
		return Result.status(topMenuService.removeByIds(ids));
	}

	@GetMapping("/top-menu")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "获取顶部菜单")
	public Result<List<TopMenuVO>> getTopMenu(AdougeUser adougeUser) {
		return Result.data(TopMenuWrapper.build().listVO(topMenuService.topMenu(adougeUser)));
	}
	@PostMapping("/grant")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
	public Result<?> grant(@RequestBody TopMenuMenuDTO dto) {
		return Result.status(topMenuService.grant(dto.getTopMenuIds(), dto.getMenuIds()));
	}
}
