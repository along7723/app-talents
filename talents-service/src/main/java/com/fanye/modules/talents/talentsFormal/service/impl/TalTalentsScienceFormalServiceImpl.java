package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsScienceFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsScienceFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsScienceFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 科研学术_正式 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@DubboService
@Slf4j
public class TalTalentsScienceFormalServiceImpl implements ITalTalentsScienceFormalService {

    @Autowired
    private TalTalentsScienceFormalMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsScienceFormal talTalentsScienceFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsScienceFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsScienceFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsScienceFormal, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTalentsScienceFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsScienceFormal talTalentsScienceFormal) {
        baseMapper.insert(talTalentsScienceFormal);
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
    public Result updateData(TalTalentsScienceFormal talTalentsScienceFormal) {
        baseMapper.updateById(talTalentsScienceFormal);
        return Result.success();
    }
}
