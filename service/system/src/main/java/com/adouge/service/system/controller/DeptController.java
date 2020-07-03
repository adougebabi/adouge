package com.adouge.service.system.controller;

import cn.hutool.core.util.StrUtil;
import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.tool.api.Result;
import com.adouge.secure.AdougeUser;
import com.adouge.service.system.entity.Dept;
import com.adouge.service.system.service.IDeptService;
import com.adouge.service.system.vo.DeptVO;
import com.adouge.service.system.wrapper.DeptWrapper;
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
 * 部门表  控制器
 *
 * @author Vinson
 * @since 2020-06-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
@Api(value = "部门表 ", tags = "部门表 接口")
public class DeptController {

	private final IDeptService deptService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入dept")
	public Result<DeptVO> detail(Dept dept) {
		return Result.data(DeptWrapper.build().entityVO(deptService.getOne(Condition.getQueryWrapper(dept))));
	}

	/**
	 * 分页 部门表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入dept")
	public Result<List<DeptVO>> list(@ApiIgnore @RequestParam Map<String, Object> dept) {
		return Result.data(DeptWrapper.build().listNodeVO(deptService.list(Condition.getQueryWrapper(dept, Dept.class).lambda()
				.orderByAsc(Dept::getSort))));
	}


	/**
	 * 新增或修改 部门表
	 */
	@PostMapping("/")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增或修改", notes = "传入dept")
	public Result<?> submit(@Valid @RequestBody Dept dept) {
		return Result.status(deptService.saveOrUpdate(dept));
	}

	/**
	 * 删除 部门表
	 */
	@DeleteMapping("/{ids}")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
		return Result.status(deptService.removeByIds(ids));
	}
	@GetMapping("/tree")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "获取树")
	public Result<?> tree(String tenantId, AdougeUser user) {
		return Result.data(deptService.tree(StrUtil.emptyToDefault(tenantId,user.getTenantId())));
	}

}
