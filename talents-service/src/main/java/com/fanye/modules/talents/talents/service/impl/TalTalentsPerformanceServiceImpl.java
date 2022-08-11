package com.fanye.modules.talents.talents.service.impl;

import com.fanye.modules.talents.talents.entity.TalTalentsPerformance;
import com.fanye.modules.talents.talents.entity.TalTalentsWork;
import com.fanye.modules.talents.talents.mapper.TalTalentsPerformanceMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsPerformanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.DubboReference;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.core.utils.TreeUtils;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.ExcelExpUtils;
import com.fanye.modules.sys.generate.metadata.entity.SysMetadataField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2021-04-06
 */
@DubboService
@Slf4j
public class TalTalentsPerformanceServiceImpl implements ITalTalentsPerformanceService {

    @Autowired
    private TalTalentsPerformanceMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalTalentsPerformance talTalentsPerformance, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsPerformance> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsPerformance> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsPerformance
                , paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public List<TalTalentsPerformance> getListByTalId(Long talId) {
        TalTalentsPerformance query = new TalTalentsPerformance();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsPerformance>(query));
    }


    @Override
    public Integer queryCount(TalTalentsPerformance talTalentsPerformance) {
        return baseMapper.selectCount(new QueryWrapper<TalTalentsPerformance>(talTalentsPerformance));
    }


    @Override
    public Result getById(String id) {
        TalTalentsPerformance one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsPerformance talTalentsPerformance) {
        baseMapper.insert(talTalentsPerformance);
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
    public Result updateData(TalTalentsPerformance talTalentsPerformance) {
        baseMapper.updateById(talTalentsPerformance);
        return Result.success();
    }
}
