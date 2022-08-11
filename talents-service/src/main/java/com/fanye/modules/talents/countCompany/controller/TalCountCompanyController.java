package com.fanye.modules.talents.countCompany.controller;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.countCompany.service.ITalCountCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户统计表 前端控制器
 * </p>
 *
 * @author yichenlei
 * @since 2021-02-03
 */
@Api(tags = {"公司信息统计"})
@RestController
@RequestMapping("/countCompany")
public class TalCountCompanyController {

    @DubboReference
    private ITalCountCompanyService talCountCompanyService;


    @ApiOperation(value = "企业在职人数排名")
    @GetMapping("/onTheJob")
    public Result onTheJob() {
        return talCountCompanyService.onTheJob();
    }

    @ApiOperation(value = "单位数量变化趋势")
    @GetMapping("/quantityChange")
    public Result quantityChange() {
        return talCountCompanyService.quantityChange();
    }

    @ApiOperation(value = "单位类型分布")
    @GetMapping("/unitType")
    public Result unitType() {
        return talCountCompanyService.unitType();
    }

}
