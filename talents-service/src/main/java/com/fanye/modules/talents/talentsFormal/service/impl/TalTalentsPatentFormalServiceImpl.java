package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.talents.talents.entity.TalTalentsPatent;
import com.fanye.modules.talents.talents.mapper.TalTalentsPatentMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsPatentService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsPatentFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsPatentFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsPatentFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
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
public class TalTalentsPatentFormalServiceImpl implements ITalTalentsPatentFormalService {

    @Autowired
    private TalTalentsPatentFormalMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalTalentsPatentFormal talTalentsPatent, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsPatentFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsPatentFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsPatent
                , paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public Integer queryCount(TalTalentsPatentFormal talTalentsPatent) {
        return baseMapper.selectCount(new QueryWrapper<TalTalentsPatentFormal>(talTalentsPatent));
    }


    @Override
    public Result getById(String id) {
        TalTalentsPatentFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsPatentFormal talTalentsPatent) {
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
    public Result updateData(TalTalentsPatentFormal talTalentsPatent) {
        baseMapper.updateById(talTalentsPatent);
        return Result.success();
    }
}
