package com.fanye.modules.talents.countUserType.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.countUserType.entity.TalCountUser;
import com.fanye.modules.talents.countUserType.mapper.TalCountUserMapper;
import com.fanye.modules.talents.countUserType.service.ITalCountUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户统计表 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-02-03
 */
@DubboService
@Slf4j
public class TalCountUserServiceImpl implements ITalCountUserService {

    @Autowired
    private TalCountUserMapper baseMapper;

    @Override
    public Result queryListByPage(TalCountUser talCountUser, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalCountUser> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalCountUser> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talCountUser, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public List<TalCountUser> query(TalCountUser talCountUser) {
        return baseMapper.selectList(new QueryWrapper<TalCountUser>(talCountUser));
    }

    @Override
    public Integer queryCount(TalCountUser talCountUser) {
        return baseMapper.selectCount(new QueryWrapper<TalCountUser>(talCountUser));
    }

    @Override
    public Result getById(String id) {
        TalCountUser one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalCountUser talCountUser) {
        baseMapper.insert(talCountUser);
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
    public Result updateData(TalCountUser talCountUser) {
        baseMapper.updateById(talCountUser);
        return Result.success();
    }

    @Override
    public Result countSex() {

        return Result.success(baseMapper.countSex());
    }

    @Override
    public Result countAge() {
        return Result.success(baseMapper.countAge());
    }

    @Override
    public Result countEducation() {
        return  Result.success(baseMapper.countEducation());
    }

    @Override
    public Result countNation() {
        return  Result.success(baseMapper.countNation());
    }

    @Override
    public Result countSource() {
        return  Result.success(baseMapper.countSource());
    }

    @Override
    public Result countUserTrend() {
        return  Result.success(baseMapper.countUserTrend());
    }
}
