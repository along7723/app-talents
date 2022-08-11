package com.fanye.modules.talents.talentPolicy.service.impl;

import com.fanye.modules.talents.talentPolicy.entity.TalTalentPolicy;
import com.fanye.modules.talents.talentPolicy.mapper.TalTalentPolicyMapper;
import com.fanye.modules.talents.talentPolicy.service.ITalTalentPolicyService;
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
    * 人才政策信息 服务实现类
    * </p>
*
* @author Administrator
* @since 2021-04-12
*/
@DubboService
@Slf4j
    public class TalTalentPolicyServiceImpl implements ITalTalentPolicyService {

    @Autowired
    private TalTalentPolicyMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalTalentPolicy talTalentPolicy, Integer pageIndex, Integer pageSize, Map
    <String ,String[]> paramsMap,String[] fastQueryFiledNames) {
        IPage<TalTalentPolicy> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentPolicy> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentPolicy
        ,paramsMap,fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
        }



            @Override
            public Integer queryCount(TalTalentPolicy talTalentPolicy) {
            return baseMapper.selectCount(new QueryWrapper<TalTalentPolicy>(talTalentPolicy));
            }


                @Override
                public Result getById(String id) {
                TalTalentPolicy one = baseMapper.selectById(id);
                return Result.success(one);
                }

                @Override
                public Result add(TalTalentPolicy talTalentPolicy){
                baseMapper.insert(talTalentPolicy);
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
                public Result updateData(TalTalentPolicy talTalentPolicy){
                baseMapper.updateById(talTalentPolicy);
                return Result.success();
                }
            }
