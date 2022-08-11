package com.fanye.modules.talents.talents.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsScience;
import com.fanye.modules.talents.talents.entity.TalTalentsTechTitle;
import com.fanye.modules.talents.talents.mapper.TalTalentsTechTitleMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsTechTitleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 技术职称 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@DubboService
@Slf4j
public class TalTalentsTechTitleServiceImpl implements ITalTalentsTechTitleService {

    @Autowired
    private TalTalentsTechTitleMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsTechTitle talTalentsTechTitle, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsTechTitle> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsTechTitle> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsTechTitle, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public List<TalTalentsTechTitle> getListByTalId(Long talId) {

        TalTalentsTechTitle query = new TalTalentsTechTitle();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsTechTitle>(query));
    }


    @Override
    public Result getById(String id) {
        TalTalentsTechTitle one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsTechTitle talTalentsTechTitle) {
        baseMapper.insert(talTalentsTechTitle);
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
    public Result updateData(TalTalentsTechTitle talTalentsTechTitle) {
        baseMapper.updateById(talTalentsTechTitle);
        return Result.success();
    }
}
