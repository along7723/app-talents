package com.fanye.modules.talents.talentsFormal.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsCreditFormal;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsCreditFormalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 信用记录_正式 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Api(tags = {"信用记录_正式"})
@RestController
@RequestMapping("/creditFormal")
public class TalTalentsCreditFormalController {

    @DubboReference
    private ITalTalentsCreditFormalService talTalentsCreditFormalService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalTalentsCreditFormal talTalentsCreditFormal,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talTalentsCreditFormalService.queryListByPage(talTalentsCreditFormal, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return talTalentsCreditFormalService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalTalentsCreditFormal talTalentsCreditFormal) {

        return talTalentsCreditFormalService.add(talTalentsCreditFormal);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talTalentsCreditFormalService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talTalentsCreditFormalService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalTalentsCreditFormal talTalentsCreditFormal) {
        return talTalentsCreditFormalService.updateData(talTalentsCreditFormal);
    }
}
