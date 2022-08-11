package com.fanye.modules.talents.talentsFormal.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.talents.entity.TalTalentsPerformance;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsPerformanceFormal;

import java.util.Map;

/**
* <p>
    *  服务接口类
    * </p>
*
* @author dell
* @since 2021-04-06
*/
public interface ITalTalentsPerformanceFormalService {

    /**
    * 查询分页数据
    *
    * @param talTalentsPerformance 查询条件
    * @param pageIndex   页码
    * @param pageSize    每页条数
    * @return Result
    */
     Result queryListByPage(TalTalentsPerformanceFormal talTalentsPerformance, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);



    /**
    * 查询数量
    * @param talTalentsPerformance
    * @return
    */
    Integer queryCount(TalTalentsPerformanceFormal talTalentsPerformance);

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
    * @param talTalentsPerformance 信息
    * @return Result
    */
    Result add(TalTalentsPerformanceFormal talTalentsPerformance);


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
    * @param talTalentsPerformance 信息
    * @return Result
    */
    Result updateData(TalTalentsPerformanceFormal talTalentsPerformance);
    }
