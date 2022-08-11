package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.talents.talents.entity.TalTalentsAttachment;
import com.fanye.modules.talents.talents.mapper.TalTalentsAttachmentMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsAttachmentService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsAttachmentFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsAttachmentFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsAttachmentFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人才附件 服务实现类
 * </p>
 *
 * @author dell
 * @since 2021-04-10
 */
@DubboService
@Slf4j
public class TalTalentsAttachmentFormalServiceImpl implements ITalTalentsAttachmentFormalService {

    @Autowired
    private TalTalentsAttachmentFormalMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalTalentsAttachmentFormal talTalentsAttachment, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsAttachmentFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsAttachmentFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsAttachment
                , paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public List<TalTalentsAttachmentFormal> getListByTalId(Long talId) {
        TalTalentsAttachmentFormal query = new TalTalentsAttachmentFormal();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsAttachmentFormal>(query));
    }


    @Override
    public Integer queryCount(TalTalentsAttachmentFormal talTalentsAttachment) {
        return baseMapper.selectCount(new QueryWrapper<TalTalentsAttachmentFormal>(talTalentsAttachment));
    }


    @Override
    public Result getById(String id) {
        TalTalentsAttachmentFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsAttachmentFormal talTalentsAttachment) {
        baseMapper.insert(talTalentsAttachment);
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
    public Result updateData(TalTalentsAttachmentFormal talTalentsAttachment) {
        baseMapper.updateById(talTalentsAttachment);
        return Result.success();
    }
}
