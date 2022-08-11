package com.fanye.modules.talents.talents.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import com.fanye.modules.talents.talents.service.ITalExpertLevelService;
import com.fanye.modules.talents.talents.entity.TalExpertLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;


 import org.springframework.web.bind.annotation.RestController;

/**
* <p>
 * 专家级别 前端控制器
 * </p>
*
* @author yichenlei
* @since 2021-01-27
*/
 @Api(tags = {"专家级别"})
 @RestController
@RequestMapping("/expert")
public class TalExpertLevelController {

 @DubboReference
 private ITalExpertLevelService talExpertLevelService;

 @ApiOperation(value = "查询数据")
 @ApiImplicitParams({
 @ApiImplicitParam(name = "pageIndex", value = "页码"),
 @ApiImplicitParam(name = "pageSize", value = "每页条数")
 })
 @GetMapping("/query")
 public Result queryListByPage(TalExpertLevel talExpertLevel,
 @RequestParam(defaultValue="1") Integer pageIndex ,
 @RequestParam(defaultValue="10") Integer pageSize, HttpServletRequest request) {

 String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
 return talExpertLevelService.queryListByPage(talExpertLevel, pageIndex, pageSize, request.getParameterMap(),fastQueryFiledsName);

 }

 @ApiOperation(value = "数据详情")
 @GetMapping("/getById")
 public Result getById(@RequestParam String id) {

 return talExpertLevelService.getById(id);
 }

 @ApiOperation(value = "新增数据")
 @PostMapping("/add")
 public Result add(@RequestBody TalExpertLevel talExpertLevel){

 return talExpertLevelService.add(talExpertLevel);

 }

 @ApiOperation(value = "删除数据")
 @GetMapping("/del")
 public Result delete(@RequestParam("id") String id){
  return talExpertLevelService.delete(id);

 }

 @ApiOperation(value = "批量删除")
 @PostMapping("/delBatch")
 public Result delBatch(@RequestBody String[] ids) {
 return talExpertLevelService.delBatch(ids);
 }

 @ApiOperation(value = "修改数据")
 @PostMapping("/update")
 public Result update(@RequestBody TalExpertLevel talExpertLevel){
  return talExpertLevelService.updateData(talExpertLevel);
 }
 }
