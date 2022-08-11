package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.talents.talents.entity.TalTalentsPerformance;
import com.fanye.modules.talents.talents.mapper.TalTalentsPerformanceMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsPerformanceService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsPerformanceFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsPerformanceFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsPerformanceFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2021-04-06
 */
@DubboService
@Slf4j
public class TalTalentsPerformanceFormalServiceImpl implements ITalTalentsPerformanceFormalService {

    @Autowired
    private TalTalentsPerformanceFormalMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalTalentsPerformanceFormal talTalentsPerformance, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsPerformanceFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsPerformanceFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsPerformance
                , paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public Integer queryCount(TalTalentsPerformanceFormal talTalentsPerformance) {
        return baseMapper.selectCount(new QueryWrapper<TalTalentsPerformanceFormal>(talTalentsPerformance));
    }


    @Override
    public Result getById(String id) {
        TalTalentsPerformanceFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsPerformanceFormal talTalentsPerformance) {
        baseMapper.insert(talTalentsPerformance);
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
    public Result updateData(TalTalentsPerformanceFormal talTalentsPerformance) {
        baseMapper.updateById(talTalentsPerformance);
        return Result.success();
    }
}
