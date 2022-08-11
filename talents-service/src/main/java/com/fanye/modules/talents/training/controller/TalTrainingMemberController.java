package com.fanye.modules.talents.training.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.core.utils.WebUtil;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsFormal;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsFormalService;
import com.fanye.modules.talents.training.entity.TalTrainingMember;
import com.fanye.modules.talents.training.service.ITalTrainingMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@Api(tags = {""})
@RestController
@RequestMapping("/trainingMember")
@Slf4j
public class TalTrainingMemberController {

    @Autowired
    private WebUtil webUtil;

    @DubboReference
    private ITalTrainingMemberService talTrainingMemberService;
    @DubboReference
    private ITalTalentsFormalService talentsFormalSevice;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalTrainingMember talTrainingMember,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talTrainingMemberService.queryListByPage(talTrainingMember, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return talTrainingMemberService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalTrainingMember talTrainingMember) {

        return talTrainingMemberService.add(talTrainingMember);

    }


    @ApiOperation(value = "批量新增成员")
    @GetMapping("/addMembers")
    public Result addMembers(@RequestParam Long trainingId, @RequestParam String[] talIds) {
        log.info("planId:" + trainingId);
        log.info("talIds:" + talIds);
        for (String talId : talIds) {
            TalTrainingMember talTrainingMember = new TalTrainingMember();
            talTrainingMember.setTrainingId(trainingId);
            talTrainingMember.setTalId(Long.parseLong(talId));

            int count = talTrainingMemberService.queryCount(talTrainingMember);
            if (count > 0) {
                continue;
            }

            TalTalentsFormal talants = talentsFormalSevice.getTalantsById(talId);
            talTrainingMember.setBirthday(talants.getBirthday());
            talTrainingMember.setEducation(talants.getEducation());
            talTrainingMember.setName(talants.getName());
            talTrainingMember.setSex(talants.getSex());
            talTrainingMember.setIdno(talants.getIdno());

            talTrainingMemberService.add(talTrainingMember);
        }

        return Result.success("添加成功");

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talTrainingMemberService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talTrainingMemberService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalTrainingMember talTrainingMember) {
        return talTrainingMemberService.updateData(talTrainingMember);
    }

    @ApiOperation(value = "审核")
    @PostMapping("/examine")
    public Result examine(@RequestBody TalTrainingMember talTrainingMember) {
        TalTrainingMember talTrainingMemberNew =new TalTrainingMember();
        talTrainingMemberNew.setFlowCheckUser(webUtil.getUserId());
        talTrainingMemberNew.setFlowCheckTime(new Date());
        talTrainingMemberNew.setId(talTrainingMember.getId());
        talTrainingMemberNew.setFlowState(talTrainingMember.getFlowState());
        return talTrainingMemberService.updateData(talTrainingMemberNew);
    }

    @ApiOperation(value = "导出数据")
    @PostMapping("/expData")
    public void expData(@RequestParam Long tableId, TalTrainingMember talTrainingMember, HttpServletResponse response, HttpServletRequest request) {
        String[] fastQueryFiledNames = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        talTrainingMemberService.expData(tableId, talTrainingMember, request.getParameterMap(), fastQueryFiledNames, response);
    }


}
