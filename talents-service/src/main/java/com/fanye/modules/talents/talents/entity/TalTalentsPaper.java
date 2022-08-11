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
 * 发表论文
 * </p>
 *
 * @author dell
 * @since 2021-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsPaper implements Serializable {

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
     * 论著名称
     */
    @TableField("paper_name")
    private String paperName;

    /**
     * 发表时间
     */
    @TableField("publish_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date publishDate;

    /**
     * 发表载体
     */
    @TableField("publish_where")
    private String publishWhere;

    /**
     * 是否第一作者
     */
    @TableField("is_first_author")
    private String isFirstAuthor;

    /**
     * 国际国内知名期刊收录情况
     */
    @TableField("journal_record_info")
    private String journalRecordInfo;

    /**
     * 国际国内知名期刊或重要会议引用情况
     */
    @TableField("journal_use_info")
    private String journalUseInfo;

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
     * 更新人
     */
    @TableField("update_user")
    private Long updateUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;




}

