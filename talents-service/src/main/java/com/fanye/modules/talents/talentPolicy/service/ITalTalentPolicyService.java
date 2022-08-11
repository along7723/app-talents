package com.fanye.modules.talents.talentPolicy.service;

import com.fanye.modules.talents.talentPolicy.entity.TalTalentPolicy;
import com.fanye.modules.core.entity.Result;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
* <p>
    * 人才政策信息 服务接口类
    * </p>
*
* @author Administrator
* @since 2021-04-12
*/
public interface ITalTalentPolicyService {

    /**
    * 查询分页数据
    *
    * @param talTalentPolicy 查询条件
    * @param pageIndex   页码
    * @param pageSize    每页条数
    * @return Result
    */
     Result queryListByPage(TalTalentPolicy talTalentPolicy, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);



    /**
    * 查询数量
    * @param talTalentPolicy
    * @return
    */
    Integer queryCount(TalTalentPolicy talTalentPolicy);

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
    * @param talTalentPolicy 信息
    * @return Result
    */
    Result add(TalTalentPolicy talTalentPolicy);


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
    * @param talTalentPolicy 信息
    * @return Result
    */
    Result updateData(TalTalentPolicy talTalentPolicy);
    }
