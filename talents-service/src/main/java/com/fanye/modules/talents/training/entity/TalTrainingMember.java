package com.fanye.modules.talents.training.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTrainingMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 培训ID
     */
    @TableField("training_id")
    private Long trainingId;

    /**
     * 人才ID
     */
    @TableField("tal_id")
    private Long talId;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
     * 报名时间
     */
    @TableField("apply_time")
    private Date applyTime;

    @TableField("flow_state")
    private String flowState;

    @TableField("flow_result")
    private String flowResult;


    @TableField("flow_check_user")
    private Long flowCheckUser;

    @TableField("flow_check_time")
    private Date flowCheckTime;

    @TableField("is_checkin")
    private String isCheckin;

    @TableField("checkin_time")
    private Date checkinTime;


    @TableField("checkin_times")
    private Integer checkinTimes;

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
