package com.fanye.modules.talents.company.entity;

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
 * 公司-正式
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalCompanyFormal implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 企业ID
     */
    @TableId(value = "comp_id", type = IdType.AUTO)
    private Long compId;

    /**
     * 企业名称
     */
    @TableField("comp_name")
    private String compName;

    /**
     * 纳税人识别号
     */
    @TableField("taxpayer_id")
    private String taxpayerId;

    /**
     * 成立日期
     */
    @TableField("establish_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date establishDate;

    /**
     * 所在地
     */
    @TableField("location")
    private String location;

    /**
     * 详细地址
     */
    @TableField("address")
    private String address;

    /**
     * 企业性质
     */
    @TableField("comp_natrue")
    private String compNatrue;

    /**
     * 税收性质
     */
    @TableField("tax_natrue")
    private String taxNatrue;

    /**
     * 企业规模
     */
    @TableField("scale")
    private Integer scale;

    /**
     * 业务类型
     */
    @TableField("business_type")
    private String businessType;

    /**
     * 风险状况
     */
    @TableField("risk_status")
    private String riskStatus;

    /**
     * 股权变更
     */
    @TableField("equity_change")
    private String equityChange;

    /**
     * 法人姓名
     */
    @TableField("corp_name")
    private String corpName;

    /**
     * 法人身份证号
     */
    @TableField("corp_idno")
    private String corpIdno;

    /**
     * 法人性别
     */
    @TableField("corp_sex")
    private String corpSex;

    /**
     * 法人电话
     */
    @TableField("corp_phone")
    private String corpPhone;

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
     * 企业联系人
     */
    @TableField("comp_user")
    private String compUser;

    /**
     * 联系人电话
     */
    @TableField("comp_phone")
    private String compPhone;

    /**
     * 所属行业
     */
    @TableField("industry")
    private String industry;




}
