package com.fanye.modules.talents.talents.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.talents.entity.TalTalentsTechTitle;
import com.fanye.modules.talents.talents.entity.TalTalentsTitle;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人才称号 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
public interface ITalTalentsTitleService {

    /**
     * 查询数据
     *
     * @param talTalentsTitle 查询条件
     * @param pageIndex       页码
     * @param pageSize        每页条数
     * @return Result
     */
    Result queryListByPage(TalTalentsTitle talTalentsTitle, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);

    List<TalTalentsTitle> getListByTalId(Long talId);
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
     * @param talTalentsTitle 信息
     * @return Result
     */
    Result add(TalTalentsTitle talTalentsTitle);

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
     * @param talTalentsTitle 信息
     * @return Result
     */
    Result updateData(TalTalentsTitle talTalentsTitle);

}
