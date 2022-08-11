package com.fanye.modules.talents.talents.service;

import com.fanye.modules.talents.talents.entity.TalTalentsOtherinfo;
import com.fanye.modules.talents.talents.entity.TalTalentsPaper;
import com.fanye.modules.core.entity.Result;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
* <p>
    * 发表论文 服务接口类
    * </p>
*
* @author dell
* @since 2021-04-06
*/
public interface ITalTalentsPaperService {

    /**
    * 查询分页数据
    *
    * @param talTalentsPaper 查询条件
    * @param pageIndex   页码
    * @param pageSize    每页条数
    * @return Result
    */
     Result queryListByPage(TalTalentsPaper talTalentsPaper, Integer pageIndex, Integer pageSize, Map <String , String[]> paramsMap, String[] fastQueryFiledNames);


    List<TalTalentsPaper> getListByTalId(Long talId);

    /**
    * 查询数量
    * @param talTalentsPaper
    * @return
    */
    Integer queryCount(TalTalentsPaper talTalentsPaper);

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
    Result add(TalTalentsPaper talTalentsPaper);


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
    Result updateData(TalTalentsPaper talTalentsPaper);
    }
