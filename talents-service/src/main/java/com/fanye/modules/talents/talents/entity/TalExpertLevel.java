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
 * 专家级别
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalExpertLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 级别代码
     */
    @TableId(value = "expert_id", type = IdType.AUTO)
    private Long expertId;

    /**
     * 级别名称
     */
    @TableField("expert_name")
    private String expertName;

    /**
     * 级别备注
     */
    @TableField("expert_memo")
    private String expertMemo;

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
