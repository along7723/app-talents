package com.fanye.modules.talents.talentsFormal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fanye.modules.talents.talents.entity.TalTalentsScience;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 科研学术_正式
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsScienceFormal extends TalTalentsScience implements Serializable {

    public static final long serialVersionUID = 1L;



    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 人才ID
     */
    @TableField("tal_id")
    private Long talId;

    /**
     * 项目(课题)名称
     */
    @TableField("science_name")
    private String scienceName;

    /**
     * 申报人任务
     */
    @TableField("science_content")
    private String scienceContent;

    /**
     * 申报人职务
     */
    @TableField("duty")
    private String duty;

    /**
     * 参与人数
     */
    @TableField("members")
    private Integer members;

    /**
     * 成果
     */
    @TableField("result")
    private String result;

    /**
     * 成果奖励情况
     */
    @TableField("award")
    private String award;

    /**
     * 入职时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @TableField("start_date")
    private Date startDate;

    /**
     * 离职时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @TableField("end_date")
    private Date endDate;

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
