package com.fanye.modules.talents.training.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.training.entity.TalTraining;

import java.util.Map;

/**
 * <p>
 * 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
public interface ITalTrainingService {

    /**
     * 查询数据
     *
     * @param talTraining 查询条件
     * @param pageIndex   页码
     * @param pageSize    每页条数
     * @return Result
     */
    Result queryListByPage(TalTraining talTraining, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);

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
     * @param talTraining 信息
     * @return Result
     */
    Result add(TalTraining talTraining);

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
     * @param talTraining 信息
     * @return Result
     */
    Result updateData(TalTraining talTraining);

    /**
     * 培训报名
     *
     * @param talTrainingId 培训ID
     * @return Result
     */
    Result signUp(Long talTrainingId);


    /**
     * 判断是否已经培训报名
     * @param talTrainingId 培训ID
     * @return Result
     */
    Boolean checkSignUp(Long talTrainingId);

    /**
     * 统计趋势
     * @param year 年份
     * @return
     */
    Result countTrend(String year);
}
