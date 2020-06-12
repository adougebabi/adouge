package com.adouge.service.system.controller;

import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.tool.api.Result;
import com.adouge.service.system.entity.Role;
import com.adouge.service.system.service.IRoleService;
import com.adouge.service.system.vo.RoleVO;
import com.adouge.service.system.wrapper.RoleWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 角色表  控制器
 *
 * @author AdougeBabi
 * @since 2020-06-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Api(value = "角色表 ", tags = "角色表 接口")
public class RoleController {

	private final IRoleService roleService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入role")
	public Result<RoleVO> detail(Role role) {
		return Result.data(RoleWrapper.build().entityVO(roleService.getOne(Condition.getQueryWrapper(role))));
	}

	/**
	 * 分页 角色表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入role")
	public Result<List<RoleVO>> list(@ApiIgnore @RequestParam Map<String, Object> role) {
		return Result.data(RoleWrapper.build().listNodeVO(roleService.list(Condition.getQueryWrapper(role, Role.class).lambda()
				.orderByAsc(Role::getSort))));
	}


	/**
	 * 新增或修改 角色表
	 */
	@PostMapping("/")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增或修改", notes = "传入role")
	public Result<?> submit(@Valid @RequestBody Role role) {
		return Result.status(roleService.saveOrUpdate(role));
	}

	/**
	 * 删除 角色表
	 */
	@DeleteMapping("/{ids}")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
		return Result.status(roleService.removeByIds(ids));
	}
	@GetMapping("/tree")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "获取树")
	public Result<?> tree() {
		return Result.data(roleService.tree());
	}

}
