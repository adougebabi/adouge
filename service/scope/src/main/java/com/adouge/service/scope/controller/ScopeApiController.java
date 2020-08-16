package com.adouge.service.scope.controller;

import com.adouge.boot.controller.BaseController;
import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.mybatis.support.Query;
import com.adouge.core.tool.api.Result;
import com.adouge.secure.AdougeUser;
import com.adouge.service.scope.entity.ScopeApi;
import com.adouge.service.scope.service.IScopeApiService;
import com.adouge.service.scope.vo.ScopeApiVO;
import com.adouge.service.scope.wrapper.ScopeApiWrapper;
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
 * 接口权限表  控制器
 *
 * @author Vinson
 * @since 2020-08-13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/scopeApi")
@Api(value = "接口权限表 ", tags = "接口权限表 接口")
public class ScopeApiController extends BaseController<IScopeApiService> {

	private final IScopeApiService scopeApiService;



	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入scopeApi")
	public Result<ScopeApiVO> detail(ScopeApi scopeApi) {
		return Result.data(ScopeApiWrapper.build().entityVO(scopeApiService.getOne(Condition.getQueryWrapper(scopeApi))));
	}

	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入scopeApi")
	public Result<IPage<ScopeApiVO>> list(ScopeApi scopeApi, Query query) {
		return Result.data(ScopeApiWrapper.build().pageVO(scopeApiService.page(Condition.getPage(query), Condition.getQueryWrapper(scopeApi))));
	}
	@GetMapping("/test")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "测试权限", notes = "测试权限")
	public Result<?>list() {
		return Result.success("成功访问");
	}



	@PostMapping("/")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增或修改", notes = "传入scopeApi")
	public Result<?> submit(@Valid @RequestBody ScopeApi scopeApi) {
		return Result.status(scopeApiService.saveOrUpdate(scopeApi));
	}

	@DeleteMapping("/{ids}")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
		return Result.status(scopeApiService.removeByIds(ids));
	}
	@GetMapping("/grant-tree")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "权限分配树形结构", notes = "权限分配树形结构")
	public Result<List<ScopeApiVO>> grantTree(AdougeUser user) {
		return Result.data(scopeApiService.grantTree(user));
	}


}

