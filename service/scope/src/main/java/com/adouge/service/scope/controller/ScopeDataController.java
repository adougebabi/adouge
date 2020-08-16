package com.adouge.service.scope.controller;

import com.adouge.boot.controller.BaseController;
import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.mybatis.support.Query;
import com.adouge.core.tool.api.Result;
import com.adouge.secure.AdougeUser;
import com.adouge.service.scope.entity.ScopeData;
import com.adouge.service.scope.service.IRoleScopeService;
import com.adouge.service.scope.service.IScopeDataService;
import com.adouge.service.scope.vo.ScopeDataVO;
import com.adouge.service.scope.wrapper.ScopeDataWrapper;
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
 *   控制器
 *
 * @author Vinson
 * @since 2020-08-15
 */
@RestController
@AllArgsConstructor
@RequestMapping("/scopeData")
@Api(value = " ", tags = " 接口")
public class ScopeDataController extends BaseController<IScopeDataService> {

	private final IScopeDataService scopeDataService;
	private final IRoleScopeService roleScopeService;

	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入scopeData")
	public Result<ScopeDataVO> detail(ScopeData scopeData) {
		return Result.data(ScopeDataWrapper.build().entityVO(scopeDataService.getOne(Condition.getQueryWrapper(scopeData))));
	}

	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入scopeData")
	public Result<IPage<ScopeDataVO>> list(ScopeData scopeData, Query query) {
		return Result.data(ScopeDataWrapper.build().pageVO(scopeDataService.page(Condition.getPage(query), Condition.getQueryWrapper(scopeData))));
	}



	@PostMapping("/")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增或修改", notes = "传入scopeData")
	public Result<?> submit(@Valid @RequestBody ScopeData scopeData) {
		return Result.status(scopeDataService.saveOrUpdate(scopeData));
	}

	@DeleteMapping("/{ids}")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
		return Result.status(scopeDataService.removeByIds(ids));
	}
	@GetMapping("/grant-tree")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "权限分配树形结构", notes = "权限分配树形结构")
	public Result<List<ScopeDataVO>> grantTree(AdougeUser user) {
		return Result.data(scopeDataService.grantTree(user));
	}
}
