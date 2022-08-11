package com.fanye.modules.talents.tags.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.tags.entity.TalTagsClassify;
import com.fanye.modules.talents.tags.mapper.TalTagsClassifyMapper;
import com.fanye.modules.talents.tags.service.ITalTagsClassifyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 标签分类 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-19
 */
@DubboService
@Slf4j
public class TalTagsClassifyServiceImpl implements ITalTagsClassifyService {

    @Autowired
    private TalTagsClassifyMapper baseMapper;

    @Override
    public Result queryListByPage(TalTagsClassify talTagsClassify, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTagsClassify> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTagsClassify> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTagsClassify, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTagsClassify one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTagsClassify talTagsClassify) {
        baseMapper.insert(talTagsClassify);
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
    public Result updateData(TalTagsClassify talTagsClassify) {
        baseMapper.updateById(talTagsClassify);
        return Result.success();
    }
}
