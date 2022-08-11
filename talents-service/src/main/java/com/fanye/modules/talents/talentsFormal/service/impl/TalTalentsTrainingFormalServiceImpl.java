package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsTrainingFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsTrainingFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsTrainingFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 培训经历_正式 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@DubboService
@Slf4j
public class TalTalentsTrainingFormalServiceImpl implements ITalTalentsTrainingFormalService {

    @Autowired
    private TalTalentsTrainingFormalMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsTrainingFormal talTalentsTrainingFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsTrainingFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsTrainingFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsTrainingFormal, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTalentsTrainingFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsTrainingFormal talTalentsTrainingFormal) {
        baseMapper.insert(talTalentsTrainingFormal);
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
    public Result updateData(TalTalentsTrainingFormal talTalentsTrainingFormal) {
        baseMapper.updateById(talTalentsTrainingFormal);
        return Result.success();
    }
}
