package com.fanye.modules.talents.tags.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.tags.entity.TalTags;
import com.fanye.modules.talents.tags.mapper.TalTagsMapper;
import com.fanye.modules.talents.tags.service.ITalTagsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 人才标签 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-19
 */
@DubboService
@Slf4j
public class TalTagsServiceImpl implements ITalTagsService {

    @Autowired
    private TalTagsMapper baseMapper;

    @Override
    public Result queryListByPage(TalTags talTags, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTags> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTags> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTags, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTags one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTags talTags) {
        baseMapper.insert(talTags);
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
    public Result updateData(TalTags talTags) {
        baseMapper.updateById(talTags);
        return Result.success();
    }
}
