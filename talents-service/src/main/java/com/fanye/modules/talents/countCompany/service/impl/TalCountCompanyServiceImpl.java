package com.fanye.modules.talents.countCompany.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.countCompany.entity.TalCountCompany;
import com.fanye.modules.talents.countCompany.mapper.TalCountCompanyMapper;
import com.fanye.modules.talents.countCompany.service.ITalCountCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 单位数量统计表 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-02-24
 */
@DubboService
@Slf4j
public class TalCountCompanyServiceImpl implements ITalCountCompanyService {

    @Autowired
    private TalCountCompanyMapper baseMapper;

    @Override
    public Result queryListByPage(TalCountCompany talCountCompany, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalCountCompany> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalCountCompany> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talCountCompany, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public List<TalCountCompany> query(TalCountCompany talCountCompany) {
        return baseMapper.selectList(new QueryWrapper<TalCountCompany>(talCountCompany));
    }

    @Override
    public Integer queryCount(TalCountCompany talCountCompany) {
        return baseMapper.selectCount(new QueryWrapper<TalCountCompany>(talCountCompany));
    }

    @Override
    public Result getById(String id) {
        TalCountCompany one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalCountCompany talCountCompany) {
        baseMapper.insert(talCountCompany);
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
    public Result updateData(TalCountCompany talCountCompany) {
        baseMapper.updateById(talCountCompany);
        return Result.success();
    }

    @Override
    public Result onTheJob() {
       return Result.success(baseMapper.onTheJob());
    }

    @Override
    public Result quantityChange() {
        return Result.success(baseMapper.quantityChange());
    }

    @Override
    public int queryQuantityChange() {
        QueryWrapper<TalCountCompany> queryWrapper=new QueryWrapper();
        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public Result unitType() {
        return Result.success(baseMapper.unitType());
    }
}
