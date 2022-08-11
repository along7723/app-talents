package com.fanye.modules.talents.plan.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.talents.plan.entity.TalPlan;
import com.fanye.modules.talents.plan.service.ITalPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 人才计划 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@Api(tags = {"人才计划"})
@RestController
@RequestMapping("/plan")
public class TalPlanController {

    @DubboReference
    private ITalPlanService talPlanService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalPlan talPlan,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talPlanService.queryListByPage(talPlan, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return talPlanService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalPlan talPlan) {

        return talPlanService.add(talPlan);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talPlanService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talPlanService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalPlan talPlan) {
        return talPlanService.updateData(talPlan);
    }
}
