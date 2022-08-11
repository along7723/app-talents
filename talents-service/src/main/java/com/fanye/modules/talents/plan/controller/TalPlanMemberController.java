package com.fanye.modules.talents.plan.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.core.utils.WebUtil;
import com.fanye.modules.talents.plan.entity.TalPlanMember;
import com.fanye.modules.talents.plan.service.ITalPlanMemberService;
import com.fanye.modules.talents.talents.entity.TalTalents;
import com.fanye.modules.talents.talents.entity.TalTalentsPerformance;
import com.fanye.modules.talents.talents.service.ITalTalentsPerformanceService;
import com.fanye.modules.talents.talents.service.ITalTalentsService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsFormal;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsFormalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@RequestMapping("/planMembers")
@Slf4j
public class TalPlanMemberController {

    @DubboReference
    private ITalPlanMemberService talPlanMemberService;

    @DubboReference
    private ITalTalentsFormalService talentsFormalSevice;


    @DubboReference
    private ITalTalentsService iTalTalentsService;
    @DubboReference
    private ITalTalentsPerformanceService performanceService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalPlanMember talPlanMember,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talPlanMemberService.queryListByPage(talPlanMember, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return talPlanMemberService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalPlanMember talPlanMember) {

        return talPlanMemberService.add(talPlanMember);

    }


    @Autowired
    WebUtil webUtil;
    @ApiOperation(value = "手动新增计划人才")
    @GetMapping("/addNewPerson")
    public Result addNewPerson(TalTalents talTalents, Long planId, String workAchievement) {
//        if (talTalents.getIdno().length()!=18){
//            return Result.error("身份证号码错误！");
//        }
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
//        String dateString=talTalents.getIdno().substring(6,14);
//        try {
//            Date date= simpleDateFormat.parse(dateString);
//            talTalents.setBirthday(date);
//        } catch (ParseException e) {
//            return Result.error("身份证号码错误！未提取到出生日期！");
//        }
        if (iTalTalentsService.checkIdno(talTalents.getIdno()) || talentsFormalSevice.checkIdno(talTalents.getIdno())) {
            // 已经存在则不添加
            TalTalents oldTalents = iTalTalentsService.queryOneTalTalents(talTalents.getIdno());
            talTalents.setTalId(oldTalents.getTalId());
        }else{

            talTalents.setStatus("4");
            talTalents.setCreateUser(webUtil.getUserId());
            talTalents.setCreateTime(new Date());
            talTalents = iTalTalentsService.insert(talTalents);

            if (StringUtils.isNotBlank(workAchievement)){
                TalTalentsPerformance talTalentsPerformance=new TalTalentsPerformance();
                talTalentsPerformance.setCreateTime(new Date());
                talTalentsPerformance.setCreateUser(webUtil.getUserId());
                talTalentsPerformance.setTalId(talTalents.getTalId());
                talTalentsPerformance.setInfo(workAchievement);
                performanceService.add(talTalentsPerformance);
            }

            //复制到正式表
            iTalTalentsService.updateData(talTalents);
        }

        //添加到计划表
        TalPlanMember talPlanMember = new TalPlanMember();
        talPlanMember.setPlanId(planId);
        talPlanMember.setTalId(talTalents.getTalId().toString());
        talPlanMember.setBirthday(talTalents.getBirthday());
        talPlanMember.setName(talTalents.getName());
        talPlanMember.setSex(talTalents.getSex());
        talPlanMember.setIdno(talTalents.getIdno());
        talPlanMember.setWorkCompany(talTalents.getWorkCompany());
        talPlanMember.setWorkJob(talTalents.getWorkJob());
        talPlanMemberService.add(talPlanMember);

        return Result.success();
    }

    @ApiOperation(value = "批量新增成员")
    @GetMapping("/addMembers")
    public Result addMembers(@RequestParam Long planId, @RequestParam String[] talIds) {
        log.info("planId:" + planId);
        log.info("talIds:" + talIds);
        for (String talId : talIds) {
            TalPlanMember talPlanMember = new TalPlanMember();
            talPlanMember.setPlanId(planId);
            talPlanMember.setTalId(talId);

            int count = talPlanMemberService.queryCount(talPlanMember);
            if (count > 0) {
                continue;
            }

            TalTalentsFormal talants = talentsFormalSevice.getTalantsById(talId);
            talPlanMember.setBirthday(talants.getBirthday());
            if (talants.getEducation()!=null){
                talPlanMember.setEducation(talants.getEducation());
            }
            talPlanMember.setName(talants.getName());
            talPlanMember.setSex(talants.getSex());
            talPlanMember.setIdno(talants.getIdno());
            talPlanMember.setWorkCompany(talants.getWorkCompany());
            talPlanMember.setWorkJob(talants.getWorkJob());

            talPlanMemberService.add(talPlanMember);
        }

        return Result.success("添加成功");

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talPlanMemberService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talPlanMemberService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalPlanMember talPlanMember) {
        return talPlanMemberService.updateData(talPlanMember);
    }
}
