package com.fanye.modules.talents.plan.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.plan.entity.TalPlanMember;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
public interface ITalPlanMemberService {

    /**
     * 查询分页数据
     *
     * @param talPlanMember 查询条件
     * @param pageIndex     页码
     * @param pageSize      每页条数
     * @return Result
     */
    Result queryListByPage(TalPlanMember talPlanMember, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);

    /**
     * 查询数据
     * @param talPlanMember
     * @return
     */
    List<TalPlanMember> query(TalPlanMember talPlanMember);

    /**
     * 查询数量
     * @param talPlanMember
     * @return
     */
    Integer queryCount(TalPlanMember talPlanMember);

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
     * @param talPlanMember 信息
     * @return Result
     */
    Result add(TalPlanMember talPlanMember);

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
     * @param talPlanMember 信息
     * @return Result
     */
    Result updateData(TalPlanMember talPlanMember);

}
