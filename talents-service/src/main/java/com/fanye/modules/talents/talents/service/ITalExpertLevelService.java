package com.fanye.modules.talents.talents.service;

import com.fanye.modules.talents.talents.entity.TalExpertLevel;
import com.fanye.modules.core.entity.Result;


import java.util.Map;

/**
* <p>
 * 专家级别 服务接口类
 * </p>
*
* @author yichenlei
* @since 2021-01-27
*/
 public interface ITalExpertLevelService {

 /**
 * 查询数据
 *
 * @param talExpertLevel 查询条件
 * @param pageIndex   页码
 * @param pageSize    每页条数
 * @return Result
 */
 Result queryListByPage(TalExpertLevel talExpertLevel, Integer pageIndex, Integer pageSize, Map<String , String[]> paramsMap, String[] fastQueryFiledNames);

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
 * @param talExpertLevel 信息
 * @return Result
 */
 Result add(TalExpertLevel talExpertLevel);

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
 * @param talExpertLevel 信息
 * @return Result
 */
 Result updateData(TalExpertLevel talExpertLevel);

 }
