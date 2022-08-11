package com.fanye.modules.talents.notification.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 主键ID
     */
    @TableField("training_id")
    private Long trainingId;

    /**
     * 通知标题
     */
    @TableField("title")
    private String title;

    /**
     * 通知内容
     */
    @TableField("content")
    private String content;

    /**
     * 是否包含附件
     */
    @TableField("has_attachment")
    private String hasAttachment;

    @TableField("attachment")
    private String attachment;

    /**
     * 是否开启报名
     */
    @TableField("is_sign_up")
    private String isSignUp;

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


}
