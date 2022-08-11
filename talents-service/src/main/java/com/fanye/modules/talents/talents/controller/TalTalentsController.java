package com.fanye.modules.talents.talents.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.JwtUtils;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.core.utils.WebUtil;
import com.fanye.modules.sys.generate.metadata.entity.SysMetadataField;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.sys.manage.user.entity.SysCoreUser;
import com.fanye.modules.sys.manage.user.service.ISysCoreUserService;
import com.fanye.modules.talents.company.entity.TalCompany;
import com.fanye.modules.talents.company.service.ITalCompanyService;
import com.fanye.modules.talents.talents.entity.*;
import com.fanye.modules.talents.talents.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人才 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@Api(tags = {"人才"})
@RestController
@RequestMapping("/talents")
public class TalTalentsController {

    @Autowired
    WebUtil webUtil;
    @DubboReference
    private ITalTalentsService talTalentsService;
    @DubboReference
    ISysCoreUserService userService;
    @DubboReference
    ITalCompanyService companyService;
    @DubboReference
    ISysExpExcelFieldService expExcelFieldService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalTalents talTalents,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talTalentsService.queryListByPage(talTalents, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "我的填报")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/queryMy")
    public Result queryMyListByPage(TalTalents talTalents,
                                    @RequestParam(defaultValue = "1") Integer pageIndex,
                                    @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) throws Exception {

        Long userId = JwtUtils.getUserId(request);
        SysCoreUser user = userService.getOne(String.valueOf(userId));
        if ("personal".equals(user.getRoleName()) || "expert".equals(user.getRoleName()) || "admin".equals(user.getAccount())) {
            return talTalentsService.queryOneTalTalents(userId);// talTalentsService.queryListByPage(talTalents, pageIndex, pageSize, parameterMap, fastQueryFiledsName);
        } else {
            return Result.error("您不是个人用户或专家用户，无法填报数据！");
        }
    }


    @GetMapping("/queryCompany")
    public Result queryCompanyListByPage(TalTalents talTalents,
                                         @RequestParam(defaultValue = "1") Integer pageIndex,
                                         @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) throws Exception {

        Long userId = JwtUtils.getUserId(request);
        SysCoreUser user = userService.getOne(String.valueOf(userId));

        if ("company".equals(user.getRoleName()) || "admin".equals(user.getAccount())) {
            TalCompany company = companyService.getOneByCreateUser(String.valueOf(userId));

            if (null == company) {//4
                return Result.error("您还未填报企业信息，请先填报企业信息！");
            }

            if (!"4".equals(company.getStatus())) {
                return Result.error("企业信息审核中，请等待审核通过！");
            }

            talTalents.setCreateCompId(company.getCompId());
            String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
            return talTalentsService.queryListByPage(talTalents, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);
        } else {
            return Result.error("您不是企业用户，无法查询数据！");
        }
    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getCountOfIdNo")
    public Result getCountOfIdNo(@RequestParam String idNo) {

        return Result.success(talTalentsService.getCountOfIdNo(idNo));
    }

    @ApiOperation(value = "关联员工")
    @GetMapping("/relevanceTalents")
    public Result relevanceTalents(@RequestParam String idNo, @RequestParam Long compId) {

        TalTalents tal = talTalentsService.queryOneTalTalents(idNo);
        if (tal.getCreateCompId() == null || -1 == tal.getCreateCompId()) {
            TalTalents talents = new TalTalents();
            talents.setTalId(tal.getTalId());
            talents.setCreateCompId(compId);
            talTalentsService.updateData(talents);
            return Result.success();
        } else {
            return Result.error("该员工在其他公司未离职，不能进行关联！");
        }
    }


    @ApiOperation(value = "员工离职")
    @GetMapping("/leaveOffice")
    public Result leaveOffice(@RequestParam Long talId, @RequestParam Long compId) {

        TalTalents talents = new TalTalents();
        talents.setTalId(talId);
        talents.setCreateCompId(-1L);
        talTalentsService.updateData(talents);

        return Result.success();
    }


    @ApiOperation(value = "导入数据")
    @PostMapping("/importData")
    public Result importExcel(@RequestParam Long tableId, @RequestParam Long compId, @RequestParam("file") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws Exception {

        if (file.isEmpty()) {
            return Result.error("上传文件不能为空！");
        }

        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return Result.error("上传文件格式不正确,必须是xls或xlsx格式");
        }
        SysMetadataField sysMetadataTable = new SysMetadataField();
        sysMetadataTable.settId(tableId);
        sysMetadataTable.setIsImport("1");
        //查询需要导入的字段列表
        List<SysMetadataField> fields = expExcelFieldService.query(sysMetadataTable);
        if (fields == null || fields.size() == 0) {
            return Result.error("没有待导入的字段，请先配置待导入字段");
        }
        Long userId = JwtUtils.getUserId(request);
        Map result = talTalentsService.importData(tableId, userId, compId, file);

        return Result.success(result);
    }

    @ApiOperation(value = "下载EXCEL模板")
    @GetMapping("/downloadexcelTemplate")
    public void downloadExcelTemplate(@RequestParam Long tableId, HttpServletResponse response) throws Exception {
        talTalentsService.downloadTemplate(tableId, response);
    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return Result.success(talTalentsService.getById(id));
    }

    @DubboReference
    ITalTalentsEducationService educationService;
    @DubboReference
    ITalTalentsWorkService workService;
    @DubboReference
    ITalTalentsPerformanceService performanceService;
    @DubboReference
    ITalTalentsTitleService titleService;
    @DubboReference
    ITalTalentsTechTitleService techTitleService;
    @DubboReference
    ITalTalentsScienceService scienceService;
    @DubboReference
    ITalTalentsPaperService paperService;
    @DubboReference
    ITalTalentsPatentService patentService;
    @DubboReference
    ITalTalentsOtherinfoService otherinfoService;
    @DubboReference
    ITalTalentsAttachmentService attachmentService;

    @ApiOperation(value = "查询人才附属信息")
    @GetMapping("/getSubData")
    public Result getSubData(@RequestParam Long id, @RequestParam String isExpert) {

        Map data = new HashMap<>();

        List<TalTalentsEducation> educations = educationService.getListByTalId(id);
        data.put("educations", educations);

        List<TalTalentsWork> works = workService.getListByTalId(id);
        data.put("works", works);

        List<TalTalentsAttachment> attachments = attachmentService.getListByTalId(id);
        data.put("attachments", attachments);

        if ("1".equals(isExpert)) {
            List<TalTalentsTitle> titles = titleService.getListByTalId(id);
            data.put("titles", titles);

            List<TalTalentsTechTitle> techTitles = techTitleService.getListByTalId(id);
            data.put("techTitles", techTitles);

            List<TalTalentsScience> sciences = scienceService.getListByTalId(id);
            data.put("sciences", sciences);

            List<TalTalentsPaper> papers = paperService.getListByTalId(id);
            data.put("papers", papers);

            List<TalTalentsPatent> patents = patentService.getListByTalId(id);
            data.put("patents", patents);

            List<TalTalentsOtherinfo> otherinfos = otherinfoService.getListByTalId(id);
            data.put("otherinfos", otherinfos);

        } else {

            List<TalTalentsPerformance> performances = performanceService.getListByTalId(id);
            data.put("performances", performances);
        }

        return Result.success(data);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalTalents talTalents, HttpServletRequest request) throws Exception {

        Long userId = JwtUtils.getUserId(request);
        talTalents.setCreateUser(userId);
        talTalents.setCreateTime(new Date());
        talTalents.setStatus("1");
        talTalents.setCreateUserType("personal");
        talTalents.setUpdateUserType("personal");
        return talTalentsService.add(talTalents);

    }

    @ApiOperation(value = "企业人才填报")
    @PostMapping("/addCompany")
    public Result addCompany(@RequestBody TalTalents talTalents, HttpServletRequest request) throws Exception {

        Long userId = JwtUtils.getUserId(request);
        talTalents.setCreateUser(userId);
        talTalents.setCreateTime(new Date());
        talTalents.setStatus("1");
        talTalents.setCreateUserType("company");
        talTalents.setUpdateUserType("company");

        TalCompany company = companyService.getOneByCreateUser(String.valueOf(userId));
        talTalents.setCreateCompId(company.getCompId());

        return talTalentsService.add(talTalents);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talTalentsService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talTalentsService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalTalents talTalents, HttpServletRequest request) {
        setUpdateUserType(talTalents, request);
        return talTalentsService.updateData(talTalents);
    }

    @ApiOperation(value = "审核")
    @PostMapping("/examine")
    public Result examine(@RequestBody TalTalents talTalents, HttpServletRequest request) {
        talTalents.setCheckedUser(webUtil.getUserId());
        setUpdateUserType(talTalents, request);
        return talTalentsService.updateData(talTalents);
    }

    @ApiOperation(value = "待办查询")
    @GetMapping("/queryTODO")
    public Result queryTODO() {
        Integer talentsCount = talTalentsService.queryTODO();
        Integer companyCount = companyService.queryTODO();
        Integer userCount = userService.queryTODO();

        Map todoCount = new HashMap();
        todoCount.put("talentsCount", talentsCount);
        todoCount.put("companyCount", companyCount);
        todoCount.put("userCount", userCount);
        return Result.success(todoCount);
    }

    void setUpdateUserType(TalTalents talTalents, HttpServletRequest request) {
        Long userId = JwtUtils.getUserId(request);
        SysCoreUser user = userService.getOne(String.valueOf(userId));
        if ("company".equals(user.getRoleName())) {
            talTalents.setUpdateUserType("company");
        } else {
            talTalents.setUpdateUserType("personal");
        }
    }

}
