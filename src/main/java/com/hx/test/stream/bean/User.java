package com.hx.test.stream.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String department;
    private BigDecimal salary;
}
