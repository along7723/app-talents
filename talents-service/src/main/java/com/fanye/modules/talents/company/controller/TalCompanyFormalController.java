package com.fanye.modules.talents.company.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.talents.company.entity.TalCompanyFormal;
import com.fanye.modules.talents.company.service.ITalCompanyFormalService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsFormal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 公司-正式 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-27
 */
@Api(tags = {"公司-正式"})
@RestController
@RequestMapping("/companyFormal")
public class TalCompanyFormalController {

    @DubboReference
    private ITalCompanyFormalService talCompanyFormalService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalCompanyFormal talCompanyFormal,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talCompanyFormalService.queryListByPage(talCompanyFormal, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return talCompanyFormalService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalCompanyFormal talCompanyFormal) {

        return talCompanyFormalService.add(talCompanyFormal);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talCompanyFormalService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talCompanyFormalService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalCompanyFormal talCompanyFormal) {
        return talCompanyFormalService.updateData(talCompanyFormal);
    }


    @ApiOperation(value = "行业统计")
    @GetMapping("/countCompanyIndustry")
    public Result countCompanyIndustry() {
        return talCompanyFormalService.countCompanyIndustry();
    }

    @ApiOperation(value = "导出数据")
    @PostMapping("/expData")
    public void expData(@RequestParam Long tableId, TalCompanyFormal talCompanyFormal, HttpServletResponse response, HttpServletRequest request) {
        String[] fastQueryFiledNames = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        talCompanyFormalService.expData(tableId, talCompanyFormal, request.getParameterMap(), fastQueryFiledNames, response);
    }

}
