package com.hx.base.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @Author 唐立志
 * @Create 2025/1/13 14:55
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class OrgConfigVO implements Serializable {


    private Integer id;

    private String orgCode;

    private String orgName;

    private String showName;

    private String modelType;

    private String modelCode;

    private String orgImage;

    private Integer status;

    private Date createdTime;
}
