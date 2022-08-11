package com.fanye.modules.talents.talents.service.impl;

import com.fanye.modules.talents.talents.entity.TalTalentsAttachment;
import com.fanye.modules.talents.talents.entity.TalTalentsWork;
import com.fanye.modules.talents.talents.mapper.TalTalentsAttachmentMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsAttachmentService;
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
 * 人才附件 服务实现类
 * </p>
 *
 * @author dell
 * @since 2021-04-10
 */
@DubboService
@Slf4j
public class TalTalentsAttachmentServiceImpl implements ITalTalentsAttachmentService {

    @Autowired
    private TalTalentsAttachmentMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalTalentsAttachment talTalentsAttachment, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsAttachment> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsAttachment> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsAttachment
                , paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public List<TalTalentsAttachment> getListByTalId(Long talId) {
        TalTalentsAttachment query = new TalTalentsAttachment();
        query.setTalId(talId);
        return baseMapper.selectList(new QueryWrapper<TalTalentsAttachment>(query));
    }


    @Override
    public Integer queryCount(TalTalentsAttachment talTalentsAttachment) {
        return baseMapper.selectCount(new QueryWrapper<TalTalentsAttachment>(talTalentsAttachment));
    }


    @Override
    public Result getById(String id) {
        TalTalentsAttachment one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsAttachment talTalentsAttachment) {
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
    public Result updateData(TalTalentsAttachment talTalentsAttachment) {
        baseMapper.updateById(talTalentsAttachment);
        return Result.success();
    }
}
