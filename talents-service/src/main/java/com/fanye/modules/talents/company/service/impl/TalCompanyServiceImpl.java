package com.fanye.modules.talents.company.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.core.utils.ExcelExpUtils;
import com.fanye.modules.core.utils.ExcelUtils;
import com.fanye.modules.core.utils.QueryUtils;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicItemService;
import com.fanye.modules.sys.manage.dic.service.ISysCoreDicService;
import com.fanye.modules.sys.manage.expExcel.service.ISysExpExcelFieldService;
import com.fanye.modules.talents.company.entity.TalCompany;
import com.fanye.modules.talents.company.mapper.TalCompanyFormalMapper;
import com.fanye.modules.talents.company.mapper.TalCompanyMapper;
import com.fanye.modules.talents.company.service.ITalCompanyService;
import com.fanye.modules.talents.talents.entity.TalTalents;
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
 * 公司 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2021-04-16
 */
@DubboService
@Slf4j
public class TalCompanyServiceImpl implements ITalCompanyService {

    @Autowired
    private TalCompanyMapper baseMapper;

    @DubboReference
    private ISysExpExcelFieldService expExcelFieldService;

    @DubboReference
    ISysCoreDicItemService iSysCoreDicItemService;
    @DubboReference
    ISysCoreDicService iSysCoreDicService;

    @Override
    public Result queryListByPage(TalCompany talCompany, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames) {
        IPage<TalCompany> wherePage = new Page<>(pageIndex, pageSize);

        IPage<TalCompany> iPage = baseMapper.selectPage(wherePage, QueryUtils.buildQueryWrapper(talCompany
                , paramsMap, fastQueryFiledNames));

        return Result.success(Result.wrapData(iPage));
    }


    @Override
    public void expData(Long tId, TalCompany talCompany, Map
            <String, String[]> paramsMap,
                        String[] fastQueryFiledNames, HttpServletResponse response) {
        List<TalCompany> talCompanys =
                baseMapper.selectList(QueryUtils.buildQueryWrapper(talCompany, paramsMap, fastQueryFiledNames));
        //查询出表的字段信息
        ExcelExpUtils.exportExcel(response, talCompanys, tId, expExcelFieldService, iSysCoreDicItemService,
                iSysCoreDicService);
    }

    public Map importData(Long tId,Long userId, MultipartFile file, HttpServletResponse response) {
        Map result = new HashMap();
        List<String> noIds = new ArrayList<>();
        int okNum = 0;
        int noNum = 0;
        List<TalCompany> talCompanys = ExcelUtils.readExcel(TalCompany.class, file, tId, expExcelFieldService, iSysCoreDicItemService, iSysCoreDicService);
        if (talCompanys.size() > 0) {
            for (TalCompany sone : talCompanys) {
                TalCompany query = new TalCompany();
                query.setTaxpayerId(sone.getTaxpayerId());
                Integer count = baseMapper.selectCount(new QueryWrapper<TalCompany>(query));
                if(count>0){
                    noIds.add(sone.getTaxpayerId());
                    noNum++;
                }else {
                    sone.setCreateUser(userId);
                    sone.setCreateTime(new Date());
                    sone.setStatus("6");
                    baseMapper.insert(sone);
                    okNum++;
                }
            }
        }

        result.put("okNum",okNum);
        result.put("noNum",noNum);
        result.put("noIds",noIds);

        return result;
    }

    @Override
    public void downloadTemplate(Long tId, HttpServletResponse response) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String fileName = URLEncoder.encode("公司导入模板", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream()).head(ExcelExpUtils.importhead(tId, expExcelFieldService)).sheet("公司导入模板").doWrite(null);
    }


    @Override
    public Integer queryCount(TalCompany talCompany) {
        return baseMapper.selectCount(new QueryWrapper<TalCompany>(talCompany));
    }


    @Override
    public Result getById(String id) {
        TalCompany one = baseMapper.selectById(id);
        return Result.success(one);
    }
    @Override
    public TalCompany getOneByCreateUser(String id) {
        TalCompany company = new TalCompany();
        company.setCreateUser(Long.parseLong(id));
        List<TalCompany> talCompanies = baseMapper.selectList(new QueryWrapper<>(company));
        if(talCompanies!=null && talCompanies.size()>0){
            return talCompanies.get(0);
        }
        return null;
    }

    @Override
    public TalCompany getOneByTaxId(String taxId) {
        TalCompany company = new TalCompany();
        company.setTaxpayerId(taxId);
        return baseMapper.selectOne(new QueryWrapper<>(company));
    }

    @Override
    public Result add(TalCompany talCompany) {
        baseMapper.insert(talCompany);
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
    public Result updateData(TalCompany talCompany) {
        baseMapper.updateById(talCompany);
        if ("4".equals(talCompany.getStatus())) {
            adopt(talCompany);
        }
        return Result.success();
    }


    @Autowired
    private TalCompanyFormalMapper companyFormalMapper;

    public void adopt(TalCompany talCompany) {
        companyFormalMapper.deleteById(talCompany.getCompId());
        companyFormalMapper.copyToFormal(talCompany.getCompId());
    }

    @Override
    public Integer queryTODO() {
        TalCompany query = new TalCompany();
        query.setStatus("6");
        return baseMapper.selectCount(new QueryWrapper<>(query));
    }
}
