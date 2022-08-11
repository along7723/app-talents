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
 * 人才
 * </p>
 *
 * @author Administrator
 * @since 2021-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TalTalents implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 主键ID
     */
    @TableId(value = "tal_id", type = IdType.AUTO)
    private Long talId;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 出生日期
     */
    @TableField("birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 国籍
     */
    @TableField("nationality")
    private String nationality;

    /**
     * 证件类型
     */
    @TableField("idtype")
    private String idtype;

    /**
     * 身份证号
     */
    @TableField("idno")
    private String idno;

    /**
     * 照片（彩色免冠1寸照片）
     */
    @TableField("photo")
    private String photo;

    /**
     * 民族
     */
    @TableField("nation")
    private String nation;

    /**
     * 出生地
     */
    @TableField("csd")
    private String csd;

    /**
     * 健康状况
     */
    @TableField("jkzk")
    private String jkzk;

    /**
     * 政治面貌
     */
    @TableField("politics_status")
    private String politicsStatus;

    /**
     * 电子邮箱
     */
    @TableField("dzyx")
    private String dzyx;

    /**
     * 婚姻状况
     */
    @TableField("marital_status")
    private String maritalStatus;

    /**
     * 身高
     */
    @TableField("height")
    private Integer height;

    /**
     * 体重
     */
    @TableField("weight")
    private Integer weight;

    /**
     * 户籍地
     */
    @TableField("hjd")
    private String hjd;

    /**
     * 籍贯地
     */
    @TableField("jgd")
    private String jgd;

    /**
     * 居住地
     */
    @TableField("jzd")
    private String jzd;

    /**
     * 居住地详址
     */
    @TableField("jzdxz")
    private String jzdxz;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 毕业院校
     */
    @TableField("graduate_school")
    private String graduateSchool;

    /**
     * 最高学历
     */
    @TableField("education")
    private String education;

    /**
     * 最高学位
     */
    @TableField("marjor")
    private String marjor;

    /**
     * 专业技术职称/技能水平
     */
    @TableField("lat_domain")
    private String latDomain;

    /**
     * 从事金融工作年限
     */
    @TableField("work_years")
    private Integer workYears;

    /**
     * 工作单位
     */
    @TableField("work_company")
    private String workCompany;

    /**
     * 现任职务
     */
    @TableField("work_job")
    private String workJob;

    /**
     * 专长
     */
    @TableField("zc")
    private String zc;

    /**
     * 社会兼职
     */
    @TableField("shjz")
    private String shjz;

    /**
     * 推荐单位
     */
    @TableField("tjdw")
    private String tjdw;

    /**
     * 单位联系人
     */
    @TableField("tjdw_lxr")
    private String tjdwLxr;

    /**
     * 单位联系电话
     */
    @TableField("tjdw_dh")
    private String tjdwDh;

    /**
     * 备注
     */
    @TableField("bz")
    private String bz;

    /**
     * 是否专家
     */
    @TableField("is_expert")
    private String isExpert;

    /**
     * 专家级别
     */
    @TableField("expert_level")
    private String expertLevel;

    /**
     * 本人承诺
     */
    @TableField("is_promise")
    private String isPromise;

    /**
     * 是否需要审核
     */
    @TableField("is_need_check")
    private String isNeedCheck;

    /**
     * 审核级别
     */
    @TableField("check_level")
    private String checkLevel;

    /**
     * 审核人
     */
    @TableField("checked_user")
    private Long checkedUser;

    /**
     * 数据状态
     */
    @TableField("status")
    private String status;


    /**
     * 审核原因
     */
    @TableField("reason")
    private String reason;

    /**
     * 创建公司
     */
    @TableField("create_comp_id")
    private Long createCompId;

    /**
     * 创建用户
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     *修改用户类型
     */
    @TableField("update_user_type")
    private String updateUserType;

    /**
     * 创建用户类型
     */
    @TableField("create_user_type")
    private String createUserType;


}

