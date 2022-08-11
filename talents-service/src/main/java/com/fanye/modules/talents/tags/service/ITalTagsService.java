package com.fanye.modules.talents.tags.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.tags.entity.TalTags;

import java.util.Map;

/**
 * <p>
 * 人才标签 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-19
 */
public interface ITalTagsService {

    /**
     * 查询数据
     *
     * @param talTags   查询条件
     * @param pageIndex 页码
     * @param pageSize  每页条数
     * @return Result
     */
    Result queryListByPage(TalTags talTags, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);

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
     * @param talTags 信息
     * @return Result
     */
    Result add(TalTags talTags);

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
     * @param talTags 信息
     * @return Result
     */
    Result updateData(TalTags talTags);

}
