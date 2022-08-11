package com.fanye.modules.talents.tags.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.talents.tags.entity.TalTagsClassify;
import com.fanye.modules.talents.tags.service.ITalTagsClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 标签分类 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-19
 */
@Api(tags = {"标签分类"})
@RestController
@RequestMapping("/tagClassify")
public class TalTagsClassifyController {

    @DubboReference
    private ITalTagsClassifyService talTagsClassifyService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalTagsClassify talTagsClassify,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talTagsClassifyService.queryListByPage(talTagsClassify, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return talTagsClassifyService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalTagsClassify talTagsClassify) {

        return talTagsClassifyService.add(talTagsClassify);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talTagsClassifyService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talTagsClassifyService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalTagsClassify talTagsClassify) {
        return talTagsClassifyService.updateData(talTagsClassify);
    }
}
