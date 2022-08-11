package com.fanye.modules.talents.talentsFormal.entity;

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
 * 
 * </p>
 *
 * @author dell
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsPatentFormal implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 人才ID
     */
    @TableField("tal_id")
    private Integer talId;

    /**
     * 专利号
     */
    @TableField("patent_no")
    private String patentNo;

    /**
     * 专利名称
     */
    @TableField("patent_name")
    private String patentName;

    /**
     * 申请日期
     */
    @TableField("apply_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date applyDate;

    /**
     * 专利权人
     */
    @TableField("patentee")
    private String patentee;

    /**
     * 专利权变更/许可/转让情况
     */
    @TableField("patent_change_info")
    private String patentChangeInfo;

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

