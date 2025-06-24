package com.hx.base.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
@Table(name = "model_prompt_template")
@Where(clause = "status = 1")
public class PromptTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String modelType;
    /**
     * base.aimeddoctor.common.enums.Module
     */
    private Integer module;
    /**
     * 提示词模板类型说明
     * type=1，简要解读
     * type=2，第一版深度解读（非多线程，已废弃）
     * type=3，豆包整理问题
     * type=4，深度解读
     * type=5，另起一条线程（例如检验检查复诊计划、药店风险识别与康复建议）
     * type=6，第一版内容整理（已废弃）
     */
    private String type;
    private String template;
    private String remark;
    private String additionalComments;
    private Integer status;
}
