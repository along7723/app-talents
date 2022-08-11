package com.fanye.modules.talents.talents.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsTechTitle;
import com.fanye.modules.talents.talents.entity.TalTalentsTitle;
import com.fanye.modules.talents.talents.mapper.TalTalentsTitleMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsTitleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人才称号 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@DubboService
@Slf4j
public class TalTalentsTitleServiceImpl implements ITalTalentsTitleService {

    @Autowired
    private TalTalentsTitleMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsTitle talTalentsTitle, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsTitle> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsTitle> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsTitle, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public List<TalTalentsTitle> getListByTalId(Long talId) {

        TalTalentsTitle query = new TalTalentsTitle();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsTitle>(query));
    }

    @Override
    public Result getById(String id) {
        TalTalentsTitle one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsTitle talTalentsTitle) {
        baseMapper.insert(talTalentsTitle);
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
    public Result updateData(TalTalentsTitle talTalentsTitle) {
        baseMapper.updateById(talTalentsTitle);
        return Result.success();
    }
}
