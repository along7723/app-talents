package com.fanye.modules.talents.talents.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsTags;
import com.fanye.modules.talents.talents.entity.TalTalentsTechTitle;
import com.fanye.modules.talents.talents.mapper.TalTalentsTagsMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsTagsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人才标签 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@DubboService
@Slf4j
public class TalTalentsTagsServiceImpl implements ITalTalentsTagsService {

    @Autowired
    private TalTalentsTagsMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsTags talTalentsTags, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsTags> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsTags> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsTags, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public List<TalTalentsTags> getListByTalId(Long talId) {

        TalTalentsTags query = new TalTalentsTags();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsTags>(query));
    }

    @Override
    public Result getById(String id) {
        TalTalentsTags one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsTags talTalentsTags) {
        baseMapper.insert(talTalentsTags);
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
    public Result updateData(TalTalentsTags talTalentsTags) {
        baseMapper.updateById(talTalentsTags);
        return Result.success();
    }
}
