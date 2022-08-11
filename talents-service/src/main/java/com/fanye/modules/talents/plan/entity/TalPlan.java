package com.fanye.modules.talents.plan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 人才计划
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 培训名称
     */
    @TableField("plan_name")
    private String planName;

    /**
     * 培训内容
     */
    @TableField("plan_description")
    private String planDescription;

    /**
     * 组织单位
     */
    @TableField("plan_department")
    private String planDepartment;

    /**
     * 计划人数
     */
    @TableField("plan_members")
    private Integer planMembers;

    /**
     * 成员数量
     */
    @TableField("members")
    private Integer members;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private String startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endTime;

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
