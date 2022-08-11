package com.fanye.modules.talents.training.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.talents.training.entity.TalTraining;
import com.fanye.modules.talents.training.service.ITalTrainingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@Api(tags = {""})
@RestController
@RequestMapping("/training")
public class TalTrainingController {

    @DubboReference
    private ITalTrainingService talTrainingService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalTraining talTraining,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talTrainingService.queryListByPage(talTraining, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return talTrainingService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalTraining talTraining) {

        return talTrainingService.add(talTraining);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talTrainingService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talTrainingService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalTraining talTraining) {
        return talTrainingService.updateData(talTraining);
    }

    @ApiOperation(value = "培训报名")
    @GetMapping("/signUp")
    public Result signUp(@RequestParam("talTrainingId") Long talTrainingId ) {
        return talTrainingService.signUp(talTrainingId);
    }

    @ApiOperation(value = "判断是否培训报名")
    @GetMapping("/checkSignUp")
    public Result checkSignUp(@RequestParam("talTrainingId") Long talTrainingId ) {
        return Result.success(talTrainingService.checkSignUp(talTrainingId));
    }

    @ApiOperation(value = "统计培训报名")
    @GetMapping("/count")
    public Result countTrend(@RequestParam("year") String year ) {
        return talTrainingService.countTrend(year);
    }
}
