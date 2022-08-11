package com.fanye.modules.talents.training.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.ExcelExpUtils;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsFormal;
import com.fanye.modules.talents.training.entity.TalTraining;
import com.fanye.modules.talents.training.entity.TalTrainingMember;
import com.fanye.modules.talents.training.mapper.TalTrainingMapper;
import com.fanye.modules.talents.training.mapper.TalTrainingMemberMapper;
import com.fanye.modules.talents.training.service.ITalTrainingMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@DubboService
@Slf4j
public class TalTrainingMemberServiceImpl implements ITalTrainingMemberService {

    @Autowired
    private TalTrainingMemberMapper baseMapper;

    @Autowired
    private TalTrainingMapper trainingMapper;

    @Override
    public Result queryListByPage(TalTrainingMember talTrainingMember, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTrainingMember> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTrainingMember> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTrainingMember, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTrainingMember one = baseMapper.selectById(id);
        return Result.success(one);
    }


    @Override
    public Integer queryCount(TalTrainingMember talTrainingMember) {
        return baseMapper.selectCount(new QueryWrapper<TalTrainingMember>(talTrainingMember));
    }

    @Override
    public Result add(TalTrainingMember talTrainingMember) {
        baseMapper.insert(talTrainingMember);
        this.updateMemberNum(talTrainingMember.getTrainingId());
        return Result.success();
    }

    @Override
    public Result delete(String id) {
        TalTrainingMember one = baseMapper.selectById(id);
        baseMapper.deleteById(id);
        this.updateMemberNum(one.getTrainingId());
        return Result.success();
    }

    @Override
    public Result delBatch(String[] ids) {
        if(null==ids){
            return Result.success();
        }
        TalTrainingMember one = baseMapper.selectById(ids[0]);
        baseMapper.deleteBatchIds(Arrays.asList(ids));
        this.updateMemberNum(one.getTrainingId());
        return Result.success();
    }


    @Override
    public Result updateData(TalTrainingMember talTrainingMember) {
        baseMapper.updateById(talTrainingMember);
        this.updateMemberNum(talTrainingMember.getTrainingId());
        return Result.success();
    }

    private void updateMemberNum(Long trainingId){
        TalTrainingMember member = new TalTrainingMember();
        member.setTrainingId(trainingId);

        int num = this.queryCount(member);
        TalTraining training = new TalTraining();
        training.setTrainingId(trainingId);
        training.setApplyMembers(num);
        trainingMapper.updateById(training);
    }

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;
    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;
    @Override
    public void expData(Long tId, TalTrainingMember talTrainingMember, Map<String, String[]> paramsMap, String[] fastQueryFiledNames, HttpServletResponse response) {
        List<TalTrainingMember> testUsers = baseMapper.selectList(QueryUtils.buildQueryWrapper(talTrainingMember, paramsMap, fastQueryFiledNames));
        //查询出表的字段信息
        ExcelExpUtils.exportExcel(response, testUsers, tId, expExcelFieldService, iSysCoreDicItemService, iSysCoreDicService);
    }
}
