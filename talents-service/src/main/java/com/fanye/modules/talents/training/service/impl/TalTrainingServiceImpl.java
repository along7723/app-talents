package com.fanye.modules.talents.training.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.core.utils.WebUtil;
import com.fanye.modules.sys.manage.user.entity.SysCoreUser;
import com.fanye.modules.sys.manage.user.service.ISysCoreUserService;
import com.fanye.modules.talents.talents.entity.TalTalents;
import com.fanye.modules.talents.talents.mapper.TalTalentsMapper;
import com.fanye.modules.talents.training.entity.TalTraining;
import com.fanye.modules.talents.training.entity.TalTrainingMember;
import com.fanye.modules.talents.training.mapper.TalTrainingMapper;
import com.fanye.modules.talents.training.mapper.TalTrainingMemberMapper;
import com.fanye.modules.talents.training.service.ITalTrainingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@DubboService
@Slf4j
public class TalTrainingServiceImpl implements ITalTrainingService {

    @Autowired
    private TalTrainingMapper baseMapper;
    @Autowired
    private TalTalentsMapper talentsMapper;
    @Autowired
    private TalTrainingMemberMapper talTrainingMemberMapper;
    @Autowired
    private WebUtil webUtil;
    @DubboReference
    ISysCoreUserService userService;

    @Override
    public Result queryListByPage(TalTraining talTraining, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTraining> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTraining> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTraining, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result getById(String id) {
        TalTraining one = baseMapper.selectById(id);
        return Result.success(one);
    }

    @Override
    public Result add(TalTraining talTraining) {
        baseMapper.insert(talTraining);
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
    public Result updateData(TalTraining talTraining) {
        baseMapper.updateById(talTraining);
        return Result.success();
    }

    @Override
    public Result signUp(Long talTrainingId) {
        TalTalents talTalents = getTalentInfo();
        if (null != talTalents) {
            if(!"4".equals(talTalents.getStatus())){
                return Result.error("人才信息还未通过审核！");
            }

            Long userID = webUtil.getUserId();
            TalTrainingMember member = new TalTrainingMember();
            member.setTrainingId(talTrainingId);
            member.setTalId(talTalents.getTalId());
            member.setName(talTalents.getName());
            member.setSex(talTalents.getSex());
            member.setBirthday(talTalents.getBirthday());
            member.setIdno(talTalents.getIdno());
            member.setFlowState("6");
            member.setEducation(talTalents.getEducation());
            member.setApplyTime(new Date());
            member.setCreateUser(userID);
            member.setCreateTime(new Date());
            talTrainingMemberMapper.insert(member);
        } else {
            return Result.error("请先填报人才信息！");
        }
        return Result.success();

    }

    @Override
    public Boolean checkSignUp(Long talTrainingId) {
        TalTalents talents = getTalentInfo();
        if (null != talents) {
            TalTrainingMember member = new TalTrainingMember();
            member.setTrainingId(talTrainingId);
            member.setTalId(talents.getTalId());
            int count = talTrainingMemberMapper.selectCount(QueryUtils.buildQueryWrapper(member));
            return count>0;
        }
        return false;
    }

    @Override
    public Result countTrend(String year) {
        List<Map> list = baseMapper.countTrend(year);
        return Result.success(list);
    }

    private TalTalents getTalentInfo() {
        //TODO 后期需要改为 用户和人才关联查询  目前用的是创建人关联查询
        Long userId = webUtil.getUserId();
        Map<String, Object> where = new HashMap<>();
        where.put("create_user", userId);
        List<TalTalents> talents = talentsMapper.selectByMap(where);
        if (!talents.isEmpty() && talents.size() > 0) {
            return talents.get(0);
        }

        SysCoreUser user = userService.getOne(String.valueOf(userId));
        String sfzh = user.getIdNumber();

        if(StringUtils.isNotBlank(sfzh)) {
            TalTalents query = new TalTalents();
            query.setIdno(sfzh);
            List<TalTalents> talTalentsList = talentsMapper.selectList(new QueryWrapper<TalTalents>(query));
            if(null!=talTalentsList && !talTalentsList.isEmpty()) {
                return talTalentsList.get(0);
            }
        }

        return null;
    }

}
