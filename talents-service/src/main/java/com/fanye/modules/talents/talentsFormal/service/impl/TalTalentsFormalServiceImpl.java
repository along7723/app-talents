package com.fanye.modules.talents.talentsFormal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.ExcelExpUtils;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsFormal;
import com.fanye.modules.talents.talentsFormal.mapper.TalTalentsFormalMapper;
import com.fanye.modules.talents.talentsFormal.service.ITalTalentsFormalService;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人才_正式 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@DubboService
@Slf4j
public class TalTalentsFormalServiceImpl implements ITalTalentsFormalService {

    @Autowired
    private TalTalentsFormalMapper baseMapper;

    @Override
    public Result queryListByPage(TalTalentsFormal talTalentsFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalentsFormal> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalentsFormal> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalentsFormal, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTalentsFormal one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public TalTalentsFormal getTalantsById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Result add(TalTalentsFormal talTalentsFormal) {
        baseMapper.insert(talTalentsFormal);
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
    public Result updateData(TalTalentsFormal talTalentsFormal) {
        baseMapper.updateById(talTalentsFormal);
        return Result.success();
    }

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;
    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public void expData(Long tId, TalTalentsFormal talTalentsFormal, Map<String, String[]> paramsMap, String[] fastQueryFiledNames, HttpServletResponse response) {
        List<TalTalentsFormal> testUsers = baseMapper.selectList(QueryUtils.buildQueryWrapper(talTalentsFormal, paramsMap, fastQueryFiledNames));
        //查询出表的字段信息
        ExcelExpUtils.exportExcel(response, testUsers, tId, expExcelFieldService, iSysCoreDicItemService, iSysCoreDicService);
    }
    @Override
    public boolean checkIdno(String idno) {
        Map<String,Object> map =new HashMap<>();
        map.put("idno",idno);
        return baseMapper.selectByMap(map).size()>0;
    }
}
