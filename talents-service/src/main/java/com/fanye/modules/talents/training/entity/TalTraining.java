package com.fanye.modules.talents.training.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTraining implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "training_id", type = IdType.AUTO)
    private Long trainingId;

    /**
     * 培训名称
     */
    @TableField("training_name")
    private String trainingName;

    /**
     * 培训内容
     */
    @TableField("training_content")
    private String trainingContent;

    /**
     * 组织单位
     */
    @TableField("training_department")
    private String trainingDepartment;

    @TableField("traiming_location")
    private String traimingLocation;

    @TableField("plan_members")
    private String planMembers;

    /**
     * 报名人数
     */
    @TableField("apply_members")
    private Integer applyMembers;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 报名截止时间
     */
    @TableField("apply_end_time")
    private Date applyEndTime;

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
