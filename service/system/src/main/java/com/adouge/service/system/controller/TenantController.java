package com.adouge.service.system.controller;

import com.adouge.boot.controller.BaseController;
import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.mybatis.support.Query;
import com.adouge.core.tool.api.Result;
import com.adouge.core.tool.constant.WebConstant;
import com.adouge.secure.AdougeUser;
import com.adouge.service.system.entity.Tenant;
import com.adouge.service.system.service.ITenantService;
import com.adouge.service.system.vo.TenantVO;
import com.adouge.service.system.wrapper.TenantWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * 租户表  控制器
 *
 * @author Vinson
 * @since 2020-06-16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tenant")
@Api(value = "租户表 ", tags = "租户表 接口")
public class TenantController extends BaseController<Tenant> {

	private final ITenantService tenantService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入tenant")
	public Result<TenantVO> detail(Tenant tenant) {
		return Result.data(TenantWrapper.build().entityVO(tenantService.getOne(Condition.getQueryWrapper(tenant))));
	}

	/**
	 * 分页 租户表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入tenant")
	public Result<IPage<TenantVO>> list(Tenant tenant, Query query) {
		return Result.data(TenantWrapper.build().pageVO(tenantService.page(Condition.getPage(query), Condition.getQueryWrapper(tenant))));
	}


	/**
	 * 新增或修改 租户表
	 */
	@PostMapping("/")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增或修改", notes = "传入tenant")
	public Result<?> submit(@Valid @RequestBody Tenant tenant) {
		return Result.status(tenantService.saveOrUpdate(tenant));
	}

	/**
	 * 删除 租户表
	 */
	@DeleteMapping("/{ids}")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
		return Result.status(tenantService.removeByIds(ids));
	}
	@GetMapping("/select")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "下拉数据源", notes = "传入tenant")
	public Result<List<Tenant>> select(Tenant tenant, AdougeUser user) {
		QueryWrapper<Tenant> queryWrapper = Condition.getQueryWrapper(tenant);
		List<Tenant> list = tenantService.list((!user.getTenantId().equals(WebConstant.ADMIN_TENANT_ID)) ? queryWrapper.lambda().eq(Tenant::getTenantId, user.getTenantId()) : queryWrapper);
		return Result.data(list);
	}

}
