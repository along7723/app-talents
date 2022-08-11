package com.fanye.modules.talents.notification.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.talents.notification.entity.TalNotification;
import com.fanye.modules.talents.notification.mapper.TalNotificationMapper;
import com.fanye.modules.talents.notification.service.ITalNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-19
 */
@DubboService
@Slf4j
public class TalNotificationServiceImpl implements ITalNotificationService {

    @Autowired
    private TalNotificationMapper baseMapper;

    @Override
    public Result queryListByPage(TalNotification talNotification, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalNotification> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalNotification> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talNotification, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalNotification one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalNotification talNotification) {
        baseMapper.insert(talNotification);
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
    public Result updateData(TalNotification talNotification) {
        baseMapper.updateById(talNotification);
        return Result.success();
    }
}
