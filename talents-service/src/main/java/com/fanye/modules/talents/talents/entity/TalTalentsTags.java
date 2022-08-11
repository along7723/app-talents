package com.fanye.modules.talents.talents.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 人才标签
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsTags implements Serializable {

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
     * 称号名称
     */
    @TableField("tag_id")
    public Long tagId;

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
