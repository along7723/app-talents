package com.fanye.modules.talents.talents.service.impl;

import com.fanye.modules.talents.talents.entity.TalTalentsPaper;
import com.fanye.modules.talents.talents.entity.TalTalentsPatent;
import com.fanye.modules.talents.talents.mapper.TalTalentsPatentMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsPatentService;
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
public class TalTalentsPatentServiceImpl implements ITalTalentsPatentService {

    @Autowired
    private TalTalentsPatentMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalTalentsPatent talTalentsPatent, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsPatent> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsPatent> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsPatent
                , paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public List<TalTalentsPatent> getListByTalId(Long talId) {

        TalTalentsPatent query = new TalTalentsPatent();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsPatent>(query));
    }

    @Override
    public Integer queryCount(TalTalentsPatent talTalentsPatent) {
        return baseMapper.selectCount(new QueryWrapper<TalTalentsPatent>(talTalentsPatent));
    }


    @Override
    public Result getById(String id) {
        TalTalentsPatent one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsPatent talTalentsPatent) {
        baseMapper.insert(talTalentsPatent);
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
    public Result updateData(TalTalentsPatent talTalentsPatent) {
        baseMapper.updateById(talTalentsPatent);
        return Result.success();
    }
}
