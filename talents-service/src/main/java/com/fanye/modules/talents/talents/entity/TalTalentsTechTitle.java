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
 * 证书/职称
 * </p>
 *
 * @author dell
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsTechTitle implements Serializable {

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
     * 证书名称
     */
    @TableField("title_name")
    private String titleName;

    /**
     * 称号级别
     */
    @TableField("title_level")
    private String titleLevel;

    /**
     * 获得时间
     */
    @TableField("obtain_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date obtainDate;

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

