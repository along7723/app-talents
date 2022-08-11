package com.fanye.modules.talents.talentsFormal.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsPaperFormal;

import java.util.Map;

/**
* <p>
    * 发表论文 服务接口类
    * </p>
*
* @author dell
* @since 2021-04-06
*/
public interface ITalTalentsPaperFormalService {

    /**
    * 查询分页数据
    *
    * @param talTalentsPaper 查询条件
    * @param pageIndex   页码
    * @param pageSize    每页条数
    * @return Result
    */
     Result queryListByPage(TalTalentsPaperFormal talTalentsPaper, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);



    /**
    * 查询数量
    * @param talTalentsPaper
    * @return
    */
    Integer queryCount(TalTalentsPaperFormal talTalentsPaper);

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
    * @param talTalentsPaper 信息
    * @return Result
    */
    Result add(TalTalentsPaperFormal talTalentsPaper);


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
    * @param talTalentsPaper 信息
    * @return Result
    */
    Result updateData(TalTalentsPaperFormal talTalentsPaper);
    }
