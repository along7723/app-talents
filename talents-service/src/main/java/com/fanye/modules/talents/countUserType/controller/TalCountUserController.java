package com.fanye.modules.talents.countUserType.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.talents.countUserType.entity.TalCountUser;
import com.fanye.modules.talents.countUserType.service.ITalCountUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户统计表 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2021-02-03
 */
@Api(tags = {"用户统计表"})
@RestController
@RequestMapping("/countUserType")
public class TalCountUserController {

    @DubboReference
    private ITalCountUserService talCountUserService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalCountUser talCountUser,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talCountUserService.queryListByPage(talCountUser, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {
        return talCountUserService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalCountUser talCountUser) {
        return talCountUserService.add(talCountUser);
    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talCountUserService.delete(id);
    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talCountUserService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalCountUser talCountUser) {
        return talCountUserService.updateData(talCountUser);
    }

    @ApiOperation(value = "统计用户注册趋势")
    @GetMapping("/countUserTrend")
    public Result countUserTrend() {
        return talCountUserService.countUserTrend();
    }

    @ApiOperation(value = "统计性别")
    @GetMapping("/countSex")
    public Result countSex() {
        return talCountUserService.countSex();
    }

    @ApiOperation(value = "统计年龄")
    @GetMapping("/countAge")
    public Result countAge() {
        return talCountUserService.countAge();
    }

    @ApiOperation(value = "统计文化")
    @GetMapping("/countCulture")
    public Result countCulture() {
        return talCountUserService.countEducation();
    }

    @ApiOperation(value = "统计民族")
    @GetMapping("/countNation")
    public Result countNation() {
        return talCountUserService.countNation();
    }

    @ApiOperation(value = "统计来源")
    @GetMapping("/countSource")
    public Result countSource() {
        return talCountUserService.countSource();
    }

}
