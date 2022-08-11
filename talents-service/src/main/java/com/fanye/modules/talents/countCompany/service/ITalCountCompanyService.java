package com.fanye.modules.talents.countCompany.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.countCompany.entity.TalCountCompany;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 单位数量统计表 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2021-02-24
 */
public interface ITalCountCompanyService {

    /**
     * 查询分页数据
     *
     * @param talCountCompany 查询条件
     * @param pageIndex       页码
     * @param pageSize        每页条数
     * @return Result
     */
    Result queryListByPage(TalCountCompany talCountCompany, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);


    /**
     * 查询数据
     *
     * @param talCountCompany
     * @return
     */
    List<TalCountCompany> query(TalCountCompany talCountCompany);

    /**
     * 查询数量
     *
     * @param talCountCompany
     * @return
     */
    Integer queryCount(TalCountCompany talCountCompany);


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
     * @param talCountCompany 信息
     * @return Result
     */
    Result add(TalCountCompany talCountCompany);

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
     * @param talCountCompany 信息
     * @return Result
     */
    Result updateData(TalCountCompany talCountCompany);


    /**
     * 企业在职人数排名
     *
     * @return Result
     */
    Result onTheJob();

    /**
     * 单位数量变化趋势
     */
    Result quantityChange();

    /**
     * 按时查询单位数量
     */
    int queryQuantityChange();

    /**
     * 单位类型分布
     */
    Result unitType();
}
