package com.fanye.modules.talents.talentsFormal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fanye.modules.talents.talents.entity.TalTalentsWork;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 从业经历_正式
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsWorkFormal extends TalTalentsWork implements Serializable {

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


}
