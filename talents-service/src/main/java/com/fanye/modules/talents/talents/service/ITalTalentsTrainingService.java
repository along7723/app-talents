package com.fanye.modules.talents.talents.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.talents.entity.TalTalentsTitle;
import com.fanye.modules.talents.talents.entity.TalTalentsTraining;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 培训经历 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
public interface ITalTalentsTrainingService {

    /**
     * 查询数据
     *
     * @param talTalentsTraining 查询条件
     * @param pageIndex          页码
     * @param pageSize           每页条数
     * @return Result
     */
    Result queryListByPage(TalTalentsTraining talTalentsTraining, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);

    List<TalTalentsTraining> getListByTalId(Long talId);
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
     * @param talTalentsTraining 信息
     * @return Result
     */
    Result add(TalTalentsTraining talTalentsTraining);

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
     * @param talTalentsTraining 信息
     * @return Result
     */
    Result updateData(TalTalentsTraining talTalentsTraining);

}
