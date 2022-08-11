package com.fanye.modules.talents.countUserType.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.countUserType.entity.TalCountUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户统计表 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2021-02-03
 */
public interface ITalCountUserService {

    /**
     * 查询分页数据
     *
     * @param talCountUser 查询条件
     * @param pageIndex    页码
     * @param pageSize     每页条数
     * @return Result
     */
    Result queryListByPage(TalCountUser talCountUser, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);


    /**
     * 查询数据
     *
     * @param talCountUser
     * @return
     */
    List<TalCountUser> query(TalCountUser talCountUser);

    /**
     * 查询数量
     *
     * @param talCountUser
     * @return
     */
    Integer queryCount(TalCountUser talCountUser);


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
     * @param talCountUser 信息
     * @return Result
     */
    Result add(TalCountUser talCountUser);

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
     * @param talCountUser 信息
     * @return Result
     */
    Result updateData(TalCountUser talCountUser);

    /**
     * 统计性别
     *
     * @return Result
     */
    Result countSex();
    /**
     * 统计年龄
     *
     * @return Result
     */
    Result countAge();
    /**
     * 统计文化
     *
     * @return Result
     */
    Result countEducation();
    /**
     * 统计民族
     *
     * @return Result
     */
    Result countNation();

    /**
     * 统计来源
     * @return
     */
    Result countSource();
    /**
     * 统计用户注册趋势
     * @return
     */
    Result countUserTrend();
}
