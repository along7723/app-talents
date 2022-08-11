package com.fanye.modules.talents.company.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.ExcelExpUtils;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.talents.company.entity.TalCompanyFormal;
import com.fanye.modules.talents.company.mapper.TalCompanyFormalMapper;
import com.fanye.modules.talents.company.service.ITalCompanyFormalService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsFormal;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公司-正式 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-27
 */
@DubboService
@Slf4j
public class TalCompanyFormalServiceImpl implements ITalCompanyFormalService {

    @Autowired
    private TalCompanyFormalMapper baseMapper;

    @Override
    public Result queryListByPage(TalCompanyFormal talCompanyFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalCompanyFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalCompanyFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talCompanyFormal, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalCompanyFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalCompanyFormal talCompanyFormal) {
        baseMapper.insert(talCompanyFormal);
        return Result.success();
    }

    @Override
    public Result delete(String id) {
        baseMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result delBatch(String[] ids) {
        baseMapper.deleteBatchIds(Arrays.asList(ids));
        return Result.success();
    }


    @Override
    public Result updateData(TalCompanyFormal talCompanyFormal) {
        baseMapper.updateById(talCompanyFormal);
        return Result.success();
    }


    @Override
    public Result countCompanyIndustry() {
        return Result.success(baseMapper.countCompanyIndustry());
    }


    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;
    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;
    @Override
    public void expData(Long tId, TalCompanyFormal talCompanyFormal, Map<String, String[]> paramsMap, String[] fastQueryFiledNames, HttpServletResponse response) {
        List<TalCompanyFormal> testUsers = baseMapper.selectList(QueryUtils.buildQueryWrapper(talCompanyFormal, paramsMap, fastQueryFiledNames));
        //查询出表的字段信息
        ExcelExpUtils.exportExcel(response, testUsers, tId, expExcelFieldService, iSysCoreDicItemService, iSysCoreDicService);
    }
}
