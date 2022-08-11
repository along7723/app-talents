package com.fanye.modules.talents.talents.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsEducation;
import com.fanye.modules.talents.talents.entity.TalTalentsWork;
import com.fanye.modules.talents.talents.mapper.TalTalentsWorkMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsWorkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 从业经历 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@DubboService
@Slf4j
public class TalTalentsWorkServiceImpl implements ITalTalentsWorkService {

    @Autowired
    private TalTalentsWorkMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsWork talTalentsWork, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsWork> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsWork> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsWork, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public List<TalTalentsWork> getListByTalId(Long talId) {
        TalTalentsWork query = new TalTalentsWork();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsWork>(query));
    }

    @Override
    public Result getById(String id) {
        TalTalentsWork one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsWork talTalentsWork) {
        baseMapper.insert(talTalentsWork);
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
    public Result updateData(TalTalentsWork talTalentsWork) {
        baseMapper.updateById(talTalentsWork);
        return Result.success();
    }
}
