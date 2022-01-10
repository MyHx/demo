package com.hx.entity;

import lombok.Data;

/**
 * 实体
 *
 * @author hexian
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Integer sex;
    private Integer age;
    private String depName;
}