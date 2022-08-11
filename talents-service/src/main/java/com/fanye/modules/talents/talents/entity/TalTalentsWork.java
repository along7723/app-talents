package com.fanye.modules.talents.talents.entity;

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
 * 从业经历
 * </p>
 *
 * @author dell
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsWork implements Serializable {

    private static final long serialVersionUID = 1L;



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
     * 工作单位
     */
    @TableField("company")
    private String company;

    /**
     * 单位所在地
     */
    @TableField("company_location")
    private String companyLocation;

    /**
     * 职务
     */
    @TableField("job")
    private String job;

    /**
     * 职称
     */
    @TableField("job_duty")
    private String jobDuty;

    /**
     * 入职时间
     */
    @TableField("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date startDate;

    /**
     * 离职时间
     */
    @TableField("end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endDate;

    /**
     * 离职原因
     */
    @TableField("leave_reason")
    private String leaveReason;

    /**
     * 备注
     */
    @TableField("company_appraise")
    private String companyAppraise;

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


    @TableField("comp_id")
    private Long compId;

}

