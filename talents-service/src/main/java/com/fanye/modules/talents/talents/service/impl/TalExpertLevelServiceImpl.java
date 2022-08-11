package com.fanye.modules.talents.talents.service.impl;

import com.fanye.modules.talents.talents.entity.TalExpertLevel;
import com.fanye.modules.talents.talents.mapper.TalExpertLevelMapper;
import com.fanye.modules.talents.talents.service.ITalExpertLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.core.entity.Result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
* <p>
 * 专家级别 服务实现类
 * </p>
*
* @author yichenlei
* @since 2021-01-27
*/
@DubboService
@Slf4j
 public class TalExpertLevelServiceImpl implements ITalExpertLevelService {

 @Autowired
 private TalExpertLevelMapper baseMapper;

 @Override
 public Result queryListByPage(TalExpertLevel talExpertLevel, Integer pageIndex, Integer pageSize, Map<String ,String[]> paramsMap,String[] fastQueryFiledNames) {
 IPage<TalExpertLevel> wherePage = new Page<>(pageIndex, pageSize);

 IPage<TalExpertLevel> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talExpertLevel,paramsMap,fastQueryFiledNames));

 return Result.success(Result.wrapData(iPage));
 }

 @Override
 public Result getById(String id) {
 TalExpertLevel one = baseMapper.selectById(id);
 return Result.success(one);
 }

 @Override
 public Result add(TalExpertLevel talExpertLevel){
 baseMapper.insert(talExpertLevel);
 return Result.success();
 }

 @Override
 public Result delete(String id){
 baseMapper.deleteById(id);
 return Result.success();
 }

 @Override
 public Result delBatch(String[] ids){
 baseMapper.deleteBatchIds(Arrays.asList(ids));
 return Result.success();
 }


 @Override
 public Result updateData(TalExpertLevel talExpertLevel){
 baseMapper.updateById(talExpertLevel);
 return Result.success();
 }
 }
