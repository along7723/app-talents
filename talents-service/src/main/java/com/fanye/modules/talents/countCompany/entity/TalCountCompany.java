package com.fanye.modules.talents.countCompany.entity;

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
 * 单位数量统计表
 * </p>
 *
 * @author yichenlei
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalCountCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
