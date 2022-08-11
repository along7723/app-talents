package com.fanye.modules.talents.talentsFormal.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsScienceFormal;

import java.util.Map;

/**
 * <p>
 * 科研学术_正式 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
public interface ITalTalentsScienceFormalService {

    /**
     * 查询数据
     *
     * @param talTalentsScienceFormal 查询条件
     * @param pageIndex               页码
     * @param pageSize                每页条数
     * @return Result
     */
    Result queryListByPage(TalTalentsScienceFormal talTalentsScienceFormal, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);

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
     * @param talTalentsScienceFormal 信息
     * @return Result
     */
    Result add(TalTalentsScienceFormal talTalentsScienceFormal);

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
     * @param talTalentsScienceFormal 信息
     * @return Result
     */
    Result updateData(TalTalentsScienceFormal talTalentsScienceFormal);

}
