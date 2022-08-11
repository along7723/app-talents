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
 * 培训经历
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsTraining implements Serializable {

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
     * 培训名称
     */
    @TableField("training_name")
    public String trainingName;

    /**
     * 培训内容
     */
    @TableField("training_content")
    public String trainingContent;

    /**
     * 组织单位
     */
    @TableField("training_department")
    public String trainingDepartment;

    /**
     * 开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @TableField("start_time")
    public Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @TableField("end_time")
    public Date endTime;

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
