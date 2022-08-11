package com.fanye.modules.talents.talentsFormal.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsAttachmentFormal;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人才附件 服务接口类
 * </p>
 *
 * @author dell
 * @since 2021-04-10
 */
public interface ITalTalentsAttachmentFormalService {

    /**
     * 查询分页数据
     *
     * @param talTalentsAttachment 查询条件
     * @param pageIndex            页码
     * @param pageSize             每页条数
     * @return Result
     */
    Result queryListByPage(TalTalentsAttachmentFormal talTalentsAttachment, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);


    List<TalTalentsAttachmentFormal> getListByTalId(Long talId);

    /**
     * 查询数量
     *
     * @param talTalentsAttachment
     * @return
     */
    Integer queryCount(TalTalentsAttachmentFormal talTalentsAttachment);

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
     * @param talTalentsAttachment 信息
     * @return Result
     */
    Result add(TalTalentsAttachmentFormal talTalentsAttachment);


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
     * @param talTalentsAttachment 信息
     * @return Result
     */
    Result updateData(TalTalentsAttachmentFormal talTalentsAttachment);
}
