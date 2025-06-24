package com.hx.base.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class PdfSettingVO implements Serializable {


    private Integer id;

    private String modelType;


    private Integer module;


    private String headerHtml;

    private String footerHtml;


    private String marginTop;

    private String marginBottom;

    private String marginLeft;

    private String marginRight;


    private String css;


    private String headerImage;


    private String explainImage;


    private String deepExplainImage;


    private String healthImage;


}
