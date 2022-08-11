package com.fanye.modules.talents.plan.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 *
 * </p>
 *
 * @author dell
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalPlanMember implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 培训ID
     */
    @TableField("plan_id")
    private Long planId;

    /**
     * 人才ID
     */
    @TableField("tal_id")
    private String talId;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 出生日期
     */
    @TableField("birthday")
    private Date birthday;

    /**
     * 身份证号
     */
    @TableField("idno")
    private String idno;

    /**
     * 文化程序
     */
    @TableField("education")
    private String education;

    /**
     * 工作单位
     */
    @TableField("work_company")
    private String workCompany;

    /**
     * 职务
     */
    @TableField("work_job")
    private String workJob;

    /**
     * 创建人
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


}

