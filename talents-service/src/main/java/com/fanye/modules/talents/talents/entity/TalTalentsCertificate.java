package com.fanye.modules.talents.talents.entity;

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
 * 资格证书
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalentsCertificate implements Serializable {

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
     * 证书名称
     */
    @TableField("name")
    public String name;

    /**
     * 证书级别
     */
    @TableField("level")
    public String level;

    /**
     * 获得时间
     */
    @TableField("obtain_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date obtainDate;

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
