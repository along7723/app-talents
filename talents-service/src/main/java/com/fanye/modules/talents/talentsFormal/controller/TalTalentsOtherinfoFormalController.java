package com.fanye.modules.talents.talentsFormal.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsOtherinfoFormal;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsOtherinfoFormalService;
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
@RequestMapping("/otherinfoFormal")
public class TalTalentsOtherinfoFormalController {

    @DubboReference
    private ITalTalentsOtherinfoFormalService talTalentsOtherinfoService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "pageIndex", value = "页码"),
    @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalTalentsOtherinfoFormal talTalentsOtherinfo,
                                  @RequestParam(defaultValue="1") Integer pageIndex ,
                                  @RequestParam(defaultValue="10") Integer pageSize, HttpServletRequest request) {

    String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
    return talTalentsOtherinfoService.queryListByPage(talTalentsOtherinfo, pageIndex, pageSize, request.getParameterMap(),fastQueryFiledsName);

    }
    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

    return talTalentsOtherinfoService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalTalentsOtherinfoFormal talTalentsOtherinfo){

    return talTalentsOtherinfoService.add(talTalentsOtherinfo);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id){
    return talTalentsOtherinfoService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
    return talTalentsOtherinfoService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalTalentsOtherinfoFormal talTalentsOtherinfo){
    return talTalentsOtherinfoService.updateData(talTalentsOtherinfo);
    }


    }
