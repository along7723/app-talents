package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsTagsFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsTagsFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsTagsFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 人才标签_正式 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@DubboService
@Slf4j
public class TalTalentsTagsFormalServiceImpl implements ITalTalentsTagsFormalService {

    @Autowired
    private TalTalentsTagsFormalMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsTagsFormal talTalentsTagsFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsTagsFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsTagsFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsTagsFormal, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTalentsTagsFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsTagsFormal talTalentsTagsFormal) {
        baseMapper.insert(talTalentsTagsFormal);
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
    public Result updateData(TalTalentsTagsFormal talTalentsTagsFormal) {
        baseMapper.updateById(talTalentsTagsFormal);
        return Result.success();
    }
}
