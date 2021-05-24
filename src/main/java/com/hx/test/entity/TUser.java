package com.hx.test.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 实体
 *
 * @author hexian
 */
@Data
@Entity
public class TUser {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String password;
    private String phone;
}