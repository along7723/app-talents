package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsEducationFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsEducationFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsEducationFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 教育经历_正式 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@DubboService
@Slf4j
public class TalTalentsEducationFormalServiceImpl implements ITalTalentsEducationFormalService {

    @Autowired
    private TalTalentsEducationFormalMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsEducationFormal talTalentsEducationFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsEducationFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsEducationFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsEducationFormal, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTalentsEducationFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsEducationFormal talTalentsEducationFormal) {
        baseMapper.insert(talTalentsEducationFormal);
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
    public Result updateData(TalTalentsEducationFormal talTalentsEducationFormal) {
        baseMapper.updateById(talTalentsEducationFormal);
        return Result.success();
    }
}
