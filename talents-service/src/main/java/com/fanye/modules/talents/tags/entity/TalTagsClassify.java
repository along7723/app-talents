package com.fanye.modules.talents.tags.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 标签分类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTagsClassify implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签ID
     */
    @TableId(value = "cls_id", type = IdType.AUTO)
    private Long clsId;

    /**
     * 标签名称
     */
    @TableField("cls_name")
    private String clsName;

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
