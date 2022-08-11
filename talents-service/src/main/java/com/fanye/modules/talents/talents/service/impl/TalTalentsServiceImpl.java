package com.fanye.modules.talents.talents.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.ExcelExpUtils;
import com.fanye.modules.core.utils.ExcelUtils;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.sys.manage.user.entity.SysCoreUser;
import com.fanye.modules.sys.manage.user.service.ISysCoreUserService;
import com.fanye.modules.talents.talents.entity.TalTalents;
import com.fanye.modules.talents.talents.entity.TalTalentsEducation;
import com.fanye.modules.talents.talents.entity.TalTalentsWork;
import com.fanye.modules.talents.talents.mapper.TalTalentsEducationMapper;
import com.fanye.modules.talents.talents.mapper.TalTalentsMapper;
import com.fanye.modules.talents.talents.mapper.TalTalentsWorkMapper;
import com.fanye.modules.talents.talents.service.ITalTalentsService;
import com.fanye.modules.talents.talentsFormal.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 * 人才 服务实现类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@DubboService
@Slf4j
public class TalTalentsServiceImpl implements ITalTalentsService {

    @DubboReference
    ISysCoreUserService userService;
    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;
    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;


    @Override
    public Result queryListByPage(TalTalents talTalents, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalTalents> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalTalents> iPage = talentsMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talTalents, paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }

    @Override
    public Result queryOneTalTalents(Long userId) {
        List<TalTalents> list = new ArrayList<>();
        TalTalents talTalents = talentsMapper.queryOneTalTalents(userId);

        if (null == talTalents) {
            SysCoreUser user = userService.getOne(String.valueOf(userId));
            String sfzh = user.getIdNumber();

            if (StringUtils.isNotBlank(sfzh)) {
                TalTalents query = new TalTalents();
                query.setIdno(sfzh);
                List<TalTalents> talTalentsList = talentsMapper.selectList(new QueryWrapper<TalTalents>(query));
                if (null != talTalentsList && !talTalentsList.isEmpty()) {
                    list.add(talTalentsList.get(0));
                }
            }
        } else {
            list.add(talTalents);
        }

        if (!list.isEmpty()) {
//            list.add(talTalents);
            return Result.success(Result.wrapData(list, list.size(), list.size(), 1));
        }
        return Result.success();
    }

    @Override
    public TalTalents queryOneTalTalents(String idNo) {
        TalTalents query = new TalTalents();
        query.setIdno(idNo);
        return talentsMapper.selectOne(new QueryWrapper<TalTalents>(query));
    }

    @Override
    public Integer getCountOfIdNo(String idNo) {
        TalTalents query = new TalTalents();
        query.setIdno(idNo);
        Integer integer = talentsMapper.selectCount(new QueryWrapper<TalTalents>(query));
        return integer;
    }

    @Override
    public TalTalents getById(String id) {
        TalTalents one = talentsMapper.selectById(id);
        return one;
    }

    public Map importData(Long tId, Long userId, Long compId, MultipartFile file) {
        Map result = new HashMap();
        List<String> noIds = new ArrayList<>();
        int okNum = 0;
        int noNum = 0;
        List<TalTalents> talTalentss = ExcelUtils.readExcel(TalTalents.class, file, tId, expExcelFieldService, iSysCoreDicItemService, iSysCoreDicService);
        if (talTalentss.size() > 0) {
            for (TalTalents sone : talTalentss) {
                int count = this.getCountOfIdNo(sone.getIdno());
                if (count > 0) {
                    // 已经存在
                    TalTalents t = this.queryOneTalTalents(sone.getIdno());
                    if (null == t.getCreateCompId() || -1 == t.getCreateCompId()) {
                        t.setCreateCompId(compId);
                        talentsMapper.updateById(t);
                        okNum++;
                    } else {
                        noIds.add(sone.getIdno());
                        noNum++;
                    }
                } else {
                    sone.setCreateUser(userId);
                    sone.setCreateTime(new Date());
                    sone.setCreateCompId(compId);
                    sone.setStatus("1");
                    log.info("sone:" + JSONObject.toJSONString(sone));
                    talentsMapper.insert(sone);
                    okNum++;
                }
            }
        }

        result.put("okNum", okNum);
        result.put("noNum", noNum);
        result.put("noIds", noIds);

        return result;
    }


    @Override
    public void downloadTemplate(Long tId, HttpServletResponse response) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String fileName = URLEncoder.encode("人才导入模板", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream()).head(ExcelExpUtils.importhead(tId, expExcelFieldService)).sheet("人才导入模板").doWrite(null);
    }

    @Override
    public Result add(TalTalents talTalents) {
        talentsMapper.insert(talTalents);
        return Result.success();
    }

    @Override
    public TalTalents insert(TalTalents talTalents) {
        talentsMapper.insert(talTalents);
        return talTalents;
    }


    @Override
    public Result delete(String id) {
        talentsMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result delBatch(String[] ids) {
        talentsMapper.deleteBatchIds(Arrays.asList(ids));
        return Result.success();
    }

    @Autowired
    private TalTalentsWorkMapper workMapper;
    @Autowired
    private TalTalentsEducationMapper educationMapper;

    @Override
    public Result updateData(TalTalents talTalents) {
        if ("6".equals(talTalents.getStatus())) {
            //判断工作经历
            TalTalentsWork talTalentsWork = new TalTalentsWork();
            talTalentsWork.setTalId(talTalents.getTalId());
            boolean checkWork = workMapper.selectList(QueryUtils.buildQueryWrapper(talTalentsWork)).size() > 0;
            if (!checkWork) {
                return Result.error((talTalents.getName() != null?talTalents.getName()+",":"") + "缺少工作经历，提交审核失败");
            }
            //判断教育经历
            TalTalentsEducation talTalentsEducation = new TalTalentsEducation();
            talTalentsEducation.setTalId(talTalents.getTalId());
            boolean education = educationMapper.selectList(QueryUtils.buildQueryWrapper(talTalentsEducation)).size() > 0;
            if (!education) {
                return Result.error((talTalents.getName() != null?talTalents.getName()+",":"") + "缺少教育经历，提交审核失败");
            }
        }
        talentsMapper.updateById(talTalents);
        if ("4".equals(talTalents.getStatus())) {
            adopt(talTalents);
        }
        return Result.success();
    }

    @Override
    public boolean checkIdno(String idno) {
        Map<String, Object> map = new HashMap<>();
        map.put("idno", idno);
        return talentsMapper.selectByMap(map).size() > 0;
    }

    @Override
    public Integer queryTODO() {
        TalTalents query = new TalTalents();
        query.setStatus("6");
        return talentsMapper.selectCount(new QueryWrapper<>(query));
    }

    @Autowired
    private TalTalentsMapper talentsMapper;

    @Autowired
    private TalTalentsFormalMapper talentsFormalMapper;


    @Autowired
    private TalTalentsAttachmentFormalMapper attachmentFormalMapper;
    @Autowired
    private TalTalentsCertificateFormalMapper certificateFormalMapper;
    @Autowired
    private TalTalentsCreditFormalMapper creditFormalMapper;
    @Autowired
    private TalTalentsEducationFormalMapper educationFormalMapper;
    @Autowired
    private TalTalentsOtherinfoFormalMapper otherinfoFormalMapper;
    @Autowired
    private TalTalentsPaperFormalMapper paperFormalMapper;
    @Autowired
    private TalTalentsPatentFormalMapper patentFormalMapper;
    @Autowired
    private TalTalentsPerformanceFormalMapper performanceFormalMapper;
    @Autowired
    private TalTalentsScienceFormalMapper scienceFormalMapper;
    @Autowired
    private TalTalentsTagsFormalMapper tagsFormalMapper;
    @Autowired
    private TalTalentsTechTitleFormalMapper techTitleFormalMapper;
    @Autowired
    private TalTalentsTitleFormalMapper titleFormalMapper;
    @Autowired
    private TalTalentsTrainingFormalMapper trainingFormalMapper;
    @Autowired
    private TalTalentsWorkFormalMapper workFormalMapper;

    public void adopt(TalTalents talTalents) {
        Map<String, Object> map = new HashMap<>();
        map.put("tal_id", talTalents.getTalId());

        talentsFormalMapper.deleteByMap(map);

        attachmentFormalMapper.deleteByMap(map);//
        certificateFormalMapper.deleteByMap(map);
        creditFormalMapper.deleteByMap(map);
        educationFormalMapper.deleteByMap(map);
        otherinfoFormalMapper.deleteByMap(map);//
        paperFormalMapper.deleteByMap(map);//
        patentFormalMapper.deleteByMap(map);//
        performanceFormalMapper.deleteByMap(map);//
        scienceFormalMapper.deleteByMap(map);
        tagsFormalMapper.deleteByMap(map);
        techTitleFormalMapper.deleteByMap(map);
        titleFormalMapper.deleteByMap(map);
        trainingFormalMapper.deleteByMap(map);
        workFormalMapper.deleteByMap(map);

        talentsFormalMapper.copyToFormal(talTalents.getTalId());

        attachmentFormalMapper.copyToFormal(talTalents.getTalId());
        certificateFormalMapper.copyToFormal(talTalents.getTalId());
        creditFormalMapper.copyToFormal(talTalents.getTalId());
        educationFormalMapper.copyToFormal(talTalents.getTalId());
        otherinfoFormalMapper.copyToFormal(talTalents.getTalId());
        paperFormalMapper.copyToFormal(talTalents.getTalId());
        patentFormalMapper.copyToFormal(talTalents.getTalId());
        performanceFormalMapper.copyToFormal(talTalents.getTalId());
        scienceFormalMapper.copyToFormal(talTalents.getTalId());
        tagsFormalMapper.copyToFormal(talTalents.getTalId());
        techTitleFormalMapper.copyToFormal(talTalents.getTalId());
        titleFormalMapper.copyToFormal(talTalents.getTalId());
        trainingFormalMapper.copyToFormal(talTalents.getTalId());
        workFormalMapper.copyToFormal(talTalents.getTalId());
    }


}
