package com.fanye.modules.talents.talents.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsCredit;
import com.fanye.modules.talents.talents.mapper.TalTalentsCreditMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsCreditService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 信用记录 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@DubboService
@Slf4j
public class TalTalentsCreditServiceImpl implements ITalTalentsCreditService {

    @Autowired
    private TalTalentsCreditMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsCredit talTalentsCredit, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsCredit> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsCredit> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsCredit, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTalentsCredit one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsCredit talTalentsCredit) {
        baseMapper.insert(talTalentsCredit);
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
    public Result updateData(TalTalentsCredit talTalentsCredit) {
        baseMapper.updateById(talTalentsCredit);
        return Result.success();
    }
}
