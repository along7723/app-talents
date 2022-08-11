package com.fanye.modules.talents.talents.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.RequestUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsCertificate;
import com.fanye.modules.talents.talents.service.ITalTalentsCertificateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 资格证书 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@Api(tags = {"资格证书"})
@RestController
@RequestMapping("/certificate")
public class TalTalentsCertificateController {

    @DubboReference
    private ITalTalentsCertificateService talTalentsCertificateService;

    @ApiOperation(value = "查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数")
    })
    @GetMapping("/query")
    public Result queryListByPage(TalTalentsCertificate talTalentsCertificate,
                                  @RequestParam(defaultValue = "1") Integer pageIndex,
                                  @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {

        String[] fastQueryFiledsName = RequestUtils.getFastQueryFields(request); //如："account", "nickname"
        return talTalentsCertificateService.queryListByPage(talTalentsCertificate, pageIndex, pageSize, request.getParameterMap(), fastQueryFiledsName);

    }

    @ApiOperation(value = "数据详情")
    @GetMapping("/getById")
    public Result getById(@RequestParam String id) {

        return talTalentsCertificateService.getById(id);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody TalTalentsCertificate talTalentsCertificate) {

        return talTalentsCertificateService.add(talTalentsCertificate);

    }

    @ApiOperation(value = "删除数据")
    @GetMapping("/del")
    public Result delete(@RequestParam("id") String id) {
        return talTalentsCertificateService.delete(id);

    }

    @ApiOperation(value = "批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody String[] ids) {
        return talTalentsCertificateService.delBatch(ids);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody TalTalentsCertificate talTalentsCertificate) {
        return talTalentsCertificateService.updateData(talTalentsCertificate);
    }
}