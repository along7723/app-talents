package com.fanye.modules.talents.jobInfo.service;

import com.fanye.modules.talents.jobInfo.entity.TalJobInfo;
import com.fanye.modules.core.entity.Result;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
* <p>
    * 招聘信息 服务接口类
    * </p>
*
* @author Administrator
* @since 2021-04-01
*/
public interface ITalJobInfoService {

    /**
    * 查询分页数据
    *
    * @param talJobInfo 查询条件
    * @param pageIndex   页码
    * @param pageSize    每页条数
    * @return Result
    */
     Result queryListByPage(TalJobInfo talJobInfo, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);



    /**
    * 查询数量
    * @param talJobInfo
    * @return
    */
    Integer queryCount(TalJobInfo talJobInfo);

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
    * @param talJobInfo 信息
    * @return Result
    */
    Result add(TalJobInfo talJobInfo);


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
    * @param talJobInfo 信息
    * @return Result
    */
    Result updateData(TalJobInfo talJobInfo);
    }
