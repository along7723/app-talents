package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsWorkFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsWorkFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsWorkFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 从业经历_正式 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@DubboService
@Slf4j
public class TalTalentsWorkFormalServiceImpl implements ITalTalentsWorkFormalService {

    @Autowired
    private TalTalentsWorkFormalMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsWorkFormal talTalentsWorkFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsWorkFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsWorkFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsWorkFormal, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTalentsWorkFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsWorkFormal talTalentsWorkFormal) {
        baseMapper.insert(talTalentsWorkFormal);
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
    public Result updateData(TalTalentsWorkFormal talTalentsWorkFormal) {
        baseMapper.updateById(talTalentsWorkFormal);
        return Result.success();
    }
}
