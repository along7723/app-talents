package com.fanye.modules.talents.talents.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import com.fanye.modules.talents.talents.service.ITalTalentsPaperService;
import com.fanye.modules.talents.talents.entity.TalTalentsPaper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 发表论文 前端控制器
    * </p>
*
* @author dell
* @since 2021-04-06
*/
    @Api(tags = {"发表论文"})
    @RestController
@RequestMapping("/paper")
public class TalTalentsPaperController {

    @DubboReference
    private ITalTalentsPaperService talTalentsPaperService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "pageIndex", value = "页码"),
    @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalTalentsPaper talTalentsPaper,
    @RequestParam(defaultValue="1") Integer pageIndex ,
    @RequestParam(defaultValue="10") Integer pageSize, HttpServletRequest request) {

    String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
    return talTalentsPaperService.queryListByPage(talTalentsPaper, pageIndex, pageSize, request.getParameterMap(),fastQueryFiledsName);

    }
    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

    return talTalentsPaperService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalTalentsPaper talTalentsPaper){

    return talTalentsPaperService.add(talTalentsPaper);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id){
    return talTalentsPaperService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
    return talTalentsPaperService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalTalentsPaper talTalentsPaper){
    return talTalentsPaperService.updateData(talTalentsPaper);
    }


    }
