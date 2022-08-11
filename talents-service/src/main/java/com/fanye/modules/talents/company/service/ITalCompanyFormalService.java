package com.fanye.modules.talents.company.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.company.entity.TalCompanyFormal;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsFormal;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 * 公司-正式 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-27
 */
public interface ITalCompanyFormalService {

    /**
     * 查询数据
     *
     * @param talCompanyFormal 查询条件
     * @param pageIndex        页码
     * @param pageSize         每页条数
     * @return Result
     */
    Result queryListByPage(TalCompanyFormal talCompanyFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);

    /**
     * 数据详情
     *
     * @param id
     * @return
     */
    Result getById(String id);

    /**
     * 添加
     *
     * @param talCompanyFormal 信息
     * @return Result
     */
    Result add(TalCompanyFormal talCompanyFormal);

    /**
     * 删除
     *
     * @param id 主键
     * @return Result
     */
    Result delete(String id);

    /**
     * 批量删除
     *
     * @param ids 主键
     * @return Result
     */
    Result delBatch(String[] ids);

    /**
     * 修改信息
     *
     * @param talCompanyFormal 信息
     * @return Result
     */
    Result updateData(TalCompanyFormal talCompanyFormal);
    /**
     * 统计行业信息
     *
     * @return Result
     */
    Result countCompanyIndustry( );
    /**
     * 导出查询的数据
     */
    void expData(Long tId, TalCompanyFormal talCompanyFormal, Map<String, String[]> paramsMap, String[] fastQueryFiledNames, HttpServletResponse response);

}
