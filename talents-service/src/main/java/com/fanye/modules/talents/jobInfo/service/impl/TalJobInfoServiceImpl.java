package com.fanye.modules.talents.jobInfo.service.impl;

import com.fanye.modules.talents.jobInfo.entity.TalJobInfo;
import com.fanye.modules.talents.jobInfo.mapper.TalJobInfoMapper;
import com.fanye.modules.talents.jobInfo.service.ITalJobInfoService;
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
    * 招聘信息 服务实现类
    * </p>
*
* @author Administrator
* @since 2021-04-01
*/
@DubboService
@Slf4j
    public class TalJobInfoServiceImpl implements ITalJobInfoService {

    @Autowired
    private TalJobInfoMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalJobInfo talJobInfo, Integer pageIndex, Integer pageSize, Map
    <String ,String[]> paramsMap,String[] fastQueryFiledNames) {
        IPage<TalJobInfo> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalJobInfo> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talJobInfo
        ,paramsMap,fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
        }



            @Override
            public Integer queryCount(TalJobInfo talJobInfo) {
            return baseMapper.selectCount(new QueryWrapper<TalJobInfo>(talJobInfo));
            }


                @Override
                public Result getById(String id) {
                TalJobInfo one = baseMapper.selectById(id);
                return Result.success(one);
                }

                @Override
                public Result add(TalJobInfo talJobInfo){
                baseMapper.insert(talJobInfo);
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
                public Result updateData(TalJobInfo talJobInfo){
                baseMapper.updateById(talJobInfo);
                return Result.success();
                }
            }
