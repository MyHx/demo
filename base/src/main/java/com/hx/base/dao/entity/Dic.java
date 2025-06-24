package com.hx.base.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lijiaqi
 * @version 1.0
 * @date 2021/8/9 13:42
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "apk_dic")
public class Dic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dicName;

    private String dicCode;

    private String showName;

    private String dicValue;

    private String dicDesc;

    private Integer sortNum;

    private String status;

    private Date createTime;

    private String createUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;
}
