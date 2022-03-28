package com.hx.base.dao.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hexian
 * @date 2022/3/28 14:48
 */

/**
 * 企业沙龙活动基本信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorpSalon {
    /**
     * 主键
     */
    private Long id;

    /**
     * 企业微信id
     */
    private String corpId;

    /**
     * 沙龙活动id
     */
    private Long salonId;

    /**
     * 沙龙活动名称
     */
    private String name;

    /**
     * 主办单位
     */
    private String organizer;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 活动详细地址
     */
    private String detailedAddress;

    /**
     * 是否删除 0为否，1为是
     */
    private Integer isDeleted;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 创建人所属部门
     */
    private String createByDept;

    /**
     * 创建人所属部门Id
     */
    private String createByDeptId;
}