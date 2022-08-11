package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsCreditFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsCreditFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsCreditFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 信用记录_正式 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@DubboService
@Slf4j
public class TalTalentsCreditFormalServiceImpl implements ITalTalentsCreditFormalService {

    @Autowired
    private TalTalentsCreditFormalMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsCreditFormal talTalentsCreditFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsCreditFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsCreditFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsCreditFormal, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTalentsCreditFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsCreditFormal talTalentsCreditFormal) {
        baseMapper.insert(talTalentsCreditFormal);
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
    public Result updateData(TalTalentsCreditFormal talTalentsCreditFormal) {
        baseMapper.updateById(talTalentsCreditFormal);
        return Result.success();
    }
}
