package com.adouge.service.system.controller;

import com.adouge.boot.controller.BaseController;
import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.tool.api.Result;
import com.adouge.service.system.entity.Dict;
import com.adouge.service.system.service.IDictService;
import com.adouge.service.system.vo.DictVO;
import com.adouge.service.system.wrapper.DictWrapper;
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
 * 字典表  控制器
 *
 * @author Vinson
 * @since 2020-07-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Api(value = "字典表 ", tags = "字典表 接口")
public class DictController extends BaseController<IDictService> {

	private final IDictService dictService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入dict")
	public Result<DictVO> detail(Dict dict) {
		return Result.data(DictWrapper.build().entityVO(dictService.getOne(Condition.getQueryWrapper(dict))));

	}

	/**
	 * 分页 字典表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入dict")
	public Result<List<DictVO>> list(@ApiIgnore @RequestParam Map<String, Object> dict) {
		return Result.data(DictWrapper.build().listNodeVO(dictService.list(Condition.getQueryWrapper(dict,Dict.class).lambda()
				.orderByAsc(Dict::getSort))));
	}


	/**
	 * 新增或修改 字典表
	 */
	@PostMapping("/")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增或修改", notes = "传入dict")
	public Result<?> submit(@Valid @RequestBody Dict dict) {
		return Result.status(dictService.saveOrUpdate(dict));
	}

	/**
	 * 删除 字典表
	 */
	@DeleteMapping("/{ids}")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
		return Result.status(dictService.removeByIds(ids));
	}

	@GetMapping("/tree")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "获取树")
	public Result<?> tree() {
		return Result.data(dictService.tree());
	}
}
