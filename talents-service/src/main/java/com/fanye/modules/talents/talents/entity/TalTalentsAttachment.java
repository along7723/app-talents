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
 * 人才附件
 * </p>
 *
 * @author dell
 * @since 2021-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsAttachment implements Serializable {

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
     * 附件名称
     */
    @TableField("attach_name")
    private String attachName;

    /**
     * 附件
     */
    @TableField("attach_file")
    private String attachFile;

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

