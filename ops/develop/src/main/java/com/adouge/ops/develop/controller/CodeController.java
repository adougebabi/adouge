package com.adouge.ops.develop.controller;

import com.adouge.core.develop.support.CodeGenerator;
import com.adouge.core.mybatis.support.Condition;
import com.adouge.core.mybatis.support.Query;
import com.adouge.core.tool.api.Result;
import com.adouge.core.tool.utils.WebUtil;
import com.adouge.ops.develop.entity.Code;
import com.adouge.ops.develop.service.ICodeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Vinson
 * @date : 2020/6/9 2:40 下午
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("code")
@Api(tags = "代码生成")
public class CodeController {
    private final ICodeService codeService;

    @GetMapping("gen/{id}")
    @ApiOperation(value = "生成文件")
    public Result<?> generator(@PathVariable Long id){
        codeService.generator(id, WebUtil.getResponse());
        return Result.success("");
    }

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入code")
    public Result<Code> detail(Code code) {
        return Result.data(codeService.getOne(Condition.getQueryWrapper(code)));
    }

    /**
     * 分页 代码生成表
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入code")
    public Result<IPage<Code>> list(Code code, Query query) {
        return Result.data(codeService.page(Condition.getPage(query), Condition.getQueryWrapper(code)));
    }

    /**
     * 新增或修改 代码生成表
     */
    @PostMapping("/")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "新增或修改", notes = "传入code")
    public Result<?> submit(@Valid @RequestBody Code code) {
        return Result.status(codeService.saveOrUpdate(code));
    }

    /**
     * 删除 代码生成表
     */
    @DeleteMapping("/{ids}")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public Result<?> remove(@ApiParam(value = "主键集合", required = true) @PathVariable List<String> ids) {
        return Result.status(codeService.removeByIds(ids));
    }
}
