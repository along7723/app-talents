package com.fanye.modules.talents.talentsFormal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fanye.modules.talents.talents.entity.TalTalentsTitle;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 人才称号_正式
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsTitleFormal extends TalTalentsTitle implements Serializable {

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
     * 奖励/人才计划名称
     */
    @TableField("title_name")
    private String titleName;

    /**
     * 级别
     */
    @TableField("title_level")
    private String titleLevel;

    /**
     * 获得时间
     */
    @TableField("obtain_data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date obtainData;

    /**
     * 备注
     */
    @TableField("memo")
    private String memo;

    /**
     * 佐证资料
     */
    @TableField("support_file")
    private String supportFile;

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
