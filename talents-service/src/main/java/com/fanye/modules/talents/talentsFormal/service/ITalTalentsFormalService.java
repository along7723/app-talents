package com.fanye.modules.talents.talentsFormal.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsFormal;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 人才_正式 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
public interface ITalTalentsFormalService {

    /**
     * 查询数据
     *
     * @param talTalentsFormal 查询条件
     * @param pageIndex        页码
     * @param pageSize         每页条数
     * @return Result
     */
    Result queryListByPage(TalTalentsFormal talTalentsFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);

    /**
     * 数据详情
     *
     * @param id
     * @return
     */
    Result getById(String id);

    TalTalentsFormal getTalantsById(String id);

    /**
     * 添加
     *
     * @param talTalentsFormal 信息
     * @return Result
     */
    Result add(TalTalentsFormal talTalentsFormal);

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
     * @param talTalentsFormal 信息
     * @return Result
     */
    Result updateData(TalTalentsFormal talTalentsFormal);
    /**
     * 导出查询的数据
     */
    void expData(Long tId, TalTalentsFormal talTalentsFormal, Map<String, String[]> paramsMap, String[] fastQueryFiledNames, HttpServletResponse response);

    boolean checkIdno(String idno);
}
