package com.fanye.modules.talents.talents.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsTitle;
import com.fanye.modules.talents.talents.entity.TalTalentsTraining;
import com.fanye.modules.talents.talents.mapper.TalTalentsTrainingMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsTrainingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 培训经历 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@DubboService
@Slf4j
public class TalTalentsTrainingServiceImpl implements ITalTalentsTrainingService {

    @Autowired
    private TalTalentsTrainingMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsTraining talTalentsTraining, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsTraining> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsTraining> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsTraining, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public List<TalTalentsTraining> getListByTalId(Long talId) {

        TalTalentsTraining query = new TalTalentsTraining();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsTraining>(query));
    }


    @Override
    public Result getById(String id) {
        TalTalentsTraining one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsTraining talTalentsTraining) {
        baseMapper.insert(talTalentsTraining);
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
    public Result updateData(TalTalentsTraining talTalentsTraining) {
        baseMapper.updateById(talTalentsTraining);
        return Result.success();
    }
}
