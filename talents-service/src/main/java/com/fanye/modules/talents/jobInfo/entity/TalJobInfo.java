package com.fanye.modules.talents.jobInfo.entity;

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
 * 招聘信息
 * </p>
 *
 * @author Administrator
 * @since 2021-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalJobInfo implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 招聘名称
     */
    @TableField("job_title")
    private String jobTitle;

    /**
     * 招聘内容
     */
    @TableField("job_content")
    private String jobContent;

    /**
     * 招聘企业
     */
    @TableField("job_company")
    private String jobCompany;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

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

    /**
     * 修改人
     */
    @TableField("update_user")
    private Long updateUser;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;




}

