package com.fanye.modules.talents.talents.service.impl;

import com.fanye.modules.talents.talents.entity.TalTalentsEducation;
import com.fanye.modules.talents.talents.entity.TalTalentsOtherinfo;
import com.fanye.modules.talents.talents.mapper.TalTalentsOtherinfoMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsOtherinfoService;
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
public class TalTalentsOtherinfoServiceImpl implements ITalTalentsOtherinfoService {

    @Autowired
    private TalTalentsOtherinfoMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalTalentsOtherinfo talTalentsOtherinfo, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsOtherinfo> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsOtherinfo> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsOtherinfo
                , paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public List<TalTalentsOtherinfo> getListByTalId(Long talId) {

        TalTalentsOtherinfo query = new TalTalentsOtherinfo();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsOtherinfo>(query));
    }


    @Override
    public Integer queryCount(TalTalentsOtherinfo talTalentsOtherinfo) {
        return baseMapper.selectCount(new QueryWrapper<TalTalentsOtherinfo>(talTalentsOtherinfo));
    }


    @Override
    public Result getById(String id) {
        TalTalentsOtherinfo one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsOtherinfo talTalentsOtherinfo) {
        baseMapper.insert(talTalentsOtherinfo);
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
    public Result updateData(TalTalentsOtherinfo talTalentsOtherinfo) {
        baseMapper.updateById(talTalentsOtherinfo);
        return Result.success();
    }
}
