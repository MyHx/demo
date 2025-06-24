package com.hx.base.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@NoArgsConstructor
@Table(name = "model_prompt_dic")
@Where(clause = "status = 1")
public class PromptDic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String modelType;
    /**
     * base.aimeddoctor.common.enums.Module
     */
    private Integer module;
    private String content;
    private Integer sortNum;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
