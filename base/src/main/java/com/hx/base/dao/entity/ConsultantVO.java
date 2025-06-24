package com.hx.base.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ConsultantVO implements Serializable {

    private static final long serialVersionUID = 385899140991532247L;


    private Integer id;


    private String typeName;


    private String name;


    private Integer defaultFlag;
}
