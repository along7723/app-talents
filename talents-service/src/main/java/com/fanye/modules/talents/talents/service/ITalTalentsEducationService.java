package com.fanye.modules.talents.talents.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.talents.entity.TalTalentsEducation;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 教育经历 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
public interface ITalTalentsEducationService {

    /**
     * 查询数据
     *
     * @param talTalentsEducation 查询条件
     * @param pageIndex           页码
     * @param pageSize            每页条数
     * @return Result
     */
    Result queryListByPage(TalTalentsEducation talTalentsEducation, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);


    List<TalTalentsEducation> getListByTalId(Long talId);
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
     * @param talTalentsEducation 信息
     * @return Result
     */
    Result add(TalTalentsEducation talTalentsEducation);

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
     * @param talTalentsEducation 信息
     * @return Result
     */
    Result updateData(TalTalentsEducation talTalentsEducation);

}
