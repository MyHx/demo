package com.hx.base.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ai_consultant")
@Where(clause = "status = '1'")
public class Consultant implements Serializable {

    private static final long serialVersionUID = -3882332448190342367L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orgCode")
    private String orgCode;

    @Column(name = "type")
    private Integer type;

    @Column(name = "head_image")
    private String headImage;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "name")
    private String name;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "status")
    private Integer status;

    @Column(name = "default_flag")
    private Integer defaultFlag;
}
