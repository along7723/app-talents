package com.fanye.modules.talents.talents.entity;

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
 * 信用记录
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsCredit implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    public Long id;

    /**
     * 人才ID
     */
    @TableField("tal_id")
    public Long talId;

    /**
     * 信用类别
     */
    @TableField("credit_type")
    public String creditType;

    /**
     * 信用积分
     */
    @TableField("credit_score")
    public Integer creditScore;

    /**
     * 积分操作
     */
    @TableField("score_opt")
    public String scoreOpt;

    /**
     * 备注
     */
    @TableField("remark")
    public String remark;

    @TableField("credit_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date creditDate;

    /**
     * 创建人
     */
    @TableField("create_user")
    public Long createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    public Date createTime;


}
