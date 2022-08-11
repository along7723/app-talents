package com.fanye.modules.talents.countUserType.entity;

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
 * 用户统计表
 * </p>
 *
 * @author yichenlei
 * @since 2021-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalCountUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户类型
     */
    @TableField("user_type")
    private String userType;

    /**
     * 统计时间
     */
    @TableField("count_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date countTime;

    /**
     * 统计数量
     */
    @TableField("count_num")
    private Long countNum;


}
