package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsCertificateFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsCertificateFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsCertificateFormalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 资格证书_正式 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@DubboService
@Slf4j
public class TalTalentsCertificateFormalServiceImpl implements ITalTalentsCertificateFormalService {

    @Autowired
    private TalTalentsCertificateFormalMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsCertificateFormal talTalentsCertificateFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsCertificateFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsCertificateFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsCertificateFormal, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTalentsCertificateFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTalentsCertificateFormal talTalentsCertificateFormal) {
        baseMapper.insert(talTalentsCertificateFormal);
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
    public Result updateData(TalTalentsCertificateFormal talTalentsCertificateFormal) {
        baseMapper.updateById(talTalentsCertificateFormal);
        return Result.success();
    }
}
