package com.fanye.modules.talents.talents.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talents.entity.TalTalentsCertificate;
import com.fanye.modules.talents.talents.mapper.TalTalentsCertificateMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsCertificateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 资格证书 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@DubboService
@Slf4j
public class TalTalentsCertificateServiceImpl implements ITalTalentsCertificateService {

    @Autowired
    private TalTalentsCertificateMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsCertificate talTalentsCertificate, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsCertificate> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsCertificate> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsCertificate, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTalentsCertificate one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsCertificate talTalentsCertificate) {
        baseMapper.insert(talTalentsCertificate);
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
    public Result updateData(TalTalentsCertificate talTalentsCertificate) {
        baseMapper.updateById(talTalentsCertificate);
        return Result.success();
    }
}
