package com.fanye.modules.talents.plan.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.plan.entity.TalPlan;
import com.fanye.modules.talents.plan.mapper.TalPlanMapper;
import com.fanye.modules.talents.plan.service.ITalPlanService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 人才计划 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@DubboService
@Slf4j
public class TalPlanServiceImpl implements ITalPlanService {

    @Autowired
    private TalPlanMapper baseMapper;

    @Override
    public Result queryListByPage(TalPlan talPlan, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalPlan> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalPlan> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talPlan, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalPlan one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalPlan talPlan) {
        baseMapper.insert(talPlan);
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
    public Result updateData(TalPlan talPlan) {
        baseMapper.updateById(talPlan);
        return Result.success();
    }
}
