package com.fanye.modules.talents.talentsFormal.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsWorkFormal;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsWorkFormalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 从业经历_正式 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Api(tags = {"从业经历_正式"})
@RestController
@RequestMapping("/workFormal")
public class TalTalentsWorkFormalController {

    @DubboReference
    private ITalTalentsWorkFormalService talTalentsWorkFormalService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalTalentsWorkFormal talTalentsWorkFormal,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talTalentsWorkFormalService.queryListByPage(talTalentsWorkFormal, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return talTalentsWorkFormalService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalTalentsWorkFormal talTalentsWorkFormal) {

        return talTalentsWorkFormalService.add(talTalentsWorkFormal);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talTalentsWorkFormalService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talTalentsWorkFormalService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalTalentsWorkFormal talTalentsWorkFormal) {
        return talTalentsWorkFormalService.updateData(talTalentsWorkFormal);
    }
}
