package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.talents.talents.entity.TalTalentsPaper;
import com.fanye.modules.talents.talents.mapper.TalTalentsPaperMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsPaperService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsPaperFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsPaperFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsPaperFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 发表论文 服务实现类
 * </p>
 *
 * @author dell
 * @since 2021-04-06
 */
@DubboService
@Slf4j
public class TalTalentsPaperFormalServiceImpl implements ITalTalentsPaperFormalService {

    @Autowired
    private TalTalentsPaperFormalMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalTalentsPaperFormal talTalentsPaper, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsPaperFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsPaperFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsPaper
                , paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public Integer queryCount(TalTalentsPaperFormal talTalentsPaper) {
        return baseMapper.selectCount(new QueryWrapper<TalTalentsPaperFormal>(talTalentsPaper));
    }


    @Override
    public Result getById(String id) {
        TalTalentsPaperFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsPaperFormal talTalentsPaper) {
        baseMapper.insert(talTalentsPaper);
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
    public Result updateData(TalTalentsPaperFormal talTalentsPaper) {
        baseMapper.updateById(talTalentsPaper);
        return Result.success();
    }
}
