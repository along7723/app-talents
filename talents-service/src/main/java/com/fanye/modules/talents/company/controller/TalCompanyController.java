package com.fanye.modules.talents.company.controller;

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
import com.fanye.modules.talents.talents.entity.TalTalents;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/company")
public class TalCompanyController {

    @Autowired
    WebUtil webUtil;
    @DubboReference
    private ITalCompanyService talCompanyService;
    @DubboReference
    ISysCoreUserService userService;
    @DubboReference
    ISysExpExcelFieldService expExcelFieldService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalCompany talCompany,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talCompanyService.queryListByPage(talCompany, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "我的企业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/queryMy")
    public Result queryMyListByPage(TalCompany talCompany,
                                    @RequestParam(defaultValue = "1") Integer pageIndex,
                                    @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {


        Long userId = JwtUtils.getUserId(request);
        SysCoreUser user = userService.getOne(String.valueOf(userId));
        if ("company".equals(user.getRoleName()) || "admin".equals(user.getAccount())) {

            List<TalCompany> list = new ArrayList<>();
            TalCompany one = talCompanyService.getOneByCreateUser(String.valueOf(userId));

            if (null == one) {
                one = talCompanyService.getOneByTaxId(user.getIdNumber());
            }

            if (null != one) {
                list.add(one);
            }

            if (!list.isEmpty()) {
                return Result.success(Result.wrapData(list, list.size(), list.size(), 1));
            }
            return Result.success();

//            talCompany.setCreateUser(userId);
//            String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
//            return talCompanyService.queryListByPage(talCompany, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);
        } else {
            return Result.error("您不是企业用户，无法填报数据！");
        }

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return talCompanyService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalCompany talCompany) {

        talCompany.setStatus("1");
        return talCompanyService.add(talCompany);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talCompanyService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talCompanyService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalCompany talCompany) {
        return talCompanyService.updateData(talCompany);
    }


    @ApiOperation(value = "审核")
    @PostMapping("/examine")
    public Result examine(@RequestBody TalCompany talCompany) {
        //talCompany.setCheckedUser(webUtil.getUserId());
        return talCompanyService.updateData(talCompany);
    }


    @ApiOperation(value = "导入数据")
    @PostMapping("/importData")
    public Result importExcel(@RequestParam Long tableId, @RequestParam("file") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws Exception {

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
        Map map = talCompanyService.importData(tableId, userId, file, response);

        return Result.success(map);
    }

    @ApiOperation(value = "下载EXCEL模板")
    @GetMapping("/downloadexcelTemplate")
    public void downloadExcelTemplate(@RequestParam Long tableId, HttpServletResponse response) throws Exception {
        talCompanyService.downloadTemplate(tableId, response);
    }
}
