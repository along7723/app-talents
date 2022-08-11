package com.fanye.modules.talents.talentsFormal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fanye.modules.talents.talents.entity.TalTalentsEducation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 教育经历_正式
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsEducationFormal extends TalTalentsEducation implements Serializable {

    public static final long serialVersionUID = 1L;



    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 人才ID
     */
    @TableField("tal_id")
    private Long talId;

    /**
     * 毕业学校
     */
    @TableField("college")
    private String college;

    /**
     * 专业名称
     */
    @TableField("major")
    private String major;

    /**
     * 学历
     */
    @TableField("education")
    private String education;

    /**
     * 学位
     */
    @TableField("degree")
    private String degree;

    /**
     * 入学年月
     */
    @TableField("start_years")
    private String startYears;

    /**
     * 毕业年月
     */
    @TableField("end_years")
    private String endYears;

    /**
     * 备注
     */
    @TableField("memo")
    private String memo;

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
