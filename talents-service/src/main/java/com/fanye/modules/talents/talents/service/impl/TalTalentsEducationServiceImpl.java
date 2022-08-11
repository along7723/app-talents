package com.fanye.modules.talents.talents.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsEducation;
import com.fanye.modules.talents.talents.mapper.TalTalentsEducationMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsEducationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 教育经历 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@DubboService
@Slf4j
public class TalTalentsEducationServiceImpl implements ITalTalentsEducationService {

    @Autowired
    private TalTalentsEducationMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsEducation talTalentsEducation, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsEducation> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsEducation> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsEducation, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public List<TalTalentsEducation> getListByTalId(Long talId) {

        TalTalentsEducation query = new TalTalentsEducation();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsEducation>(query));
    }

    @Override
    public Result getById(String id) {
        TalTalentsEducation one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsEducation talTalentsEducation) {
        baseMapper.insert(talTalentsEducation);
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
    public Result updateData(TalTalentsEducation talTalentsEducation) {
        baseMapper.updateById(talTalentsEducation);
        return Result.success();
    }
}
