package com.fanye.modules.talents.plan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.plan.entity.TalPlanMember;
import com.fanye.modules.talents.plan.mapper.TalPlanMemberMapper;
import com.fanye.modules.talents.plan.service.ITalPlanMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@DubboService
@Slf4j
public class TalPlanMemberServiceImpl implements ITalPlanMemberService {

    @Autowired
    private TalPlanMemberMapper baseMapper;

    @Override
    public Result queryListByPage(TalPlanMember talPlanMember, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalPlanMember> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalPlanMember> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talPlanMember, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public List<TalPlanMember> query(TalPlanMember talPlanMember) {
        return baseMapper.selectList(new QueryWrapper<TalPlanMember>(talPlanMember));
    }

    @Override
    public Integer queryCount(TalPlanMember talPlanMember) {
        return baseMapper.selectCount(new QueryWrapper<TalPlanMember>(talPlanMember));
    }

    @Override
    public Result getById(String id) {
        TalPlanMember one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalPlanMember talPlanMember) {
        baseMapper.insert(talPlanMember);
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
    public Result updateData(TalPlanMember talPlanMember) {
        baseMapper.updateById(talPlanMember);
        return Result.success();
    }
}
