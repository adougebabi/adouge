package com.adouge.service.system.controller;

import com.adouge.boot.controller.BaseController;
import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.mybatis.support.Query;
import com.adouge.core.tool.api.Result;
import com.adouge.service.system.entity.Client;
import com.adouge.service.system.service.IClientService;
import com.adouge.service.system.vo.ClientVO;
import com.adouge.service.system.wrapper.ClientWrapper;
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
 * 客户端表  控制器
 *
 * @author Vinson
 * @since 2020-06-16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/client")
@Api(value = "客户端表 ", tags = "客户端表 接口")
public class ClientController extends BaseController<Client> {

	private final IClientService clientService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入client")
	public Result<ClientVO> detail(Client client) {
		return Result.data(ClientWrapper.build().entityVO(clientService.getOne(Condition.getQueryWrapper(client))));
	}

	/**
	 * 分页 客户端表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入client")
	public Result<IPage<ClientVO>> list(Client client, Query query) {
		return Result.data(ClientWrapper.build().pageVO(clientService.page(Condition.getPage(query), Condition.getQueryWrapper(client))));
	}


	/**
	 * 新增或修改 客户端表
	 */
	@PostMapping("/")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增或修改", notes = "传入client")
	public Result<?> submit(@Valid @RequestBody Client client) {
		return Result.status(clientService.saveOrUpdate(client));
	}

	/**
	 * 删除 客户端表
	 */
	@DeleteMapping("/{ids}")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
		return Result.status(clientService.removeByIds(ids));
	}

}
