package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsOtherinfoFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsOtherinfoFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsOtherinfoFormalService;
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
public class TalTalentsOtherinfoFormalServiceImpl implements ITalTalentsOtherinfoFormalService {

    @Autowired
    private TalTalentsOtherinfoFormalMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalTalentsOtherinfoFormal talTalentsOtherinfo, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsOtherinfoFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsOtherinfoFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsOtherinfo
                , paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public Integer queryCount(TalTalentsOtherinfoFormal talTalentsOtherinfo) {
        return baseMapper.selectCount(new QueryWrapper<TalTalentsOtherinfoFormal>(talTalentsOtherinfo));
    }


    @Override
    public Result getById(String id) {
        TalTalentsOtherinfoFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsOtherinfoFormal talTalentsOtherinfo) {
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
    public Result updateData(TalTalentsOtherinfoFormal talTalentsOtherinfo) {
        baseMapper.updateById(talTalentsOtherinfo);
        return Result.success();
    }
}
