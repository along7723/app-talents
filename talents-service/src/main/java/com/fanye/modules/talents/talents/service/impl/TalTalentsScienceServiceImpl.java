package com.fanye.modules.talents.talents.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsPatent;
import com.fanye.modules.talents.talents.entity.TalTalentsScience;
import com.fanye.modules.talents.talents.mapper.TalTalentsScienceMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsScienceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 科研学术 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@DubboService
@Slf4j
public class TalTalentsScienceServiceImpl implements ITalTalentsScienceService {

    @Autowired
    private TalTalentsScienceMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsScience talTalentsScience, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsScience> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsScience> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsScience, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public List<TalTalentsScience> getListByTalId(Long talId) {

        TalTalentsScience query = new TalTalentsScience();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsScience>(query));
    }

    @Override
    public Result getById(String id) {
        TalTalentsScience one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsScience talTalentsScience) {
        baseMapper.insert(talTalentsScience);
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
    public Result updateData(TalTalentsScience talTalentsScience) {
        baseMapper.updateById(talTalentsScience);
        return Result.success();
    }
}
