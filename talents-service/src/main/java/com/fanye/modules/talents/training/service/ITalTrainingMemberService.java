package com.fanye.modules.talents.training.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.training.entity.TalTrainingMember;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 * 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
public interface ITalTrainingMemberService {

    /**
     * 查询数据
     *
     * @param talTrainingMember 查询条件
     * @param pageIndex         页码
     * @param pageSize          每页条数
     * @return Result
     */
    Result queryListByPage(TalTrainingMember talTrainingMember, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);

    /**
     * 数据详情
     *
     * @param id
     * @return
     */
    Result getById(String id);

    /**
     * 查询数量
     * @param talPlanMember
     * @return
     */
    Integer queryCount(TalTrainingMember talPlanMember);
    /**
     * 添加
     *
     * @param talTrainingMember 信息
     * @return Result
     */
    Result add(TalTrainingMember talTrainingMember);

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
     * @param talTrainingMember 信息
     * @return Result
     */
    Result updateData(TalTrainingMember talTrainingMember);

    /**
     * 导出查询的数据
     */
    void expData(Long tId, TalTrainingMember talTrainingMember, Map<String, String[]> paramsMap, String[] fastQueryFiledNames, HttpServletResponse response);
}
