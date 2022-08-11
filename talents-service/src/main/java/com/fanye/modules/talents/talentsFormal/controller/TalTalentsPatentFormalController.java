package com.fanye.modules.talents.talentsFormal.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsPatent;
import com.fanye.modules.talents.talents.service.ITalTalentsPatentService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsPatentFormal;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsPatentFormalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
* <p>
    *  前端控制器
    * </p>
*
* @author dell
* @since 2021-04-06
*/
    @Api(tags = {""})
    @RestController
@RequestMapping("/patentFormal")
public class TalTalentsPatentFormalController {

    @DubboReference
    private ITalTalentsPatentFormalService talTalentsPatentService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "pageIndex", value = "页码"),
    @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalTalentsPatentFormal talTalentsPatent,
                                  @RequestParam(defaultValue="1") Integer pageIndex ,
                                  @RequestParam(defaultValue="10") Integer pageSize, HttpServletRequest request) {

    String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
    return talTalentsPatentService.queryListByPage(talTalentsPatent, pageIndex, pageSize, request.getParameterMap(),fastQueryFiledsName);

    }
    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

    return talTalentsPatentService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalTalentsPatentFormal talTalentsPatent){

    return talTalentsPatentService.add(talTalentsPatent);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id){
    return talTalentsPatentService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
    return talTalentsPatentService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalTalentsPatentFormal talTalentsPatent){
    return talTalentsPatentService.updateData(talTalentsPatent);
    }


    }
