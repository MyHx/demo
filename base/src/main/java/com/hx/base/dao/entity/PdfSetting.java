package com.hx.base.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "model_pdf_setting")
@Where(clause = "status = 1")
public class PdfSetting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String modelType;
    private String headerHtml;

    private String footerHtml;

    private String marginTop;
    private String marginBottom;
    private String marginLeft;
    private String marginRight;

    private String css;

    private Integer status;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;


}
