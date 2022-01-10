package com.hx.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author hexian
 */
@Data
public class ComplexHeadData {
    @ExcelProperty({"主标题", "字符串标题"})
    private String string;
    @ExcelProperty({"主标题", "日期标题"})
    private String date;
    @ExcelProperty({"主标题", "数字标题"})
    private String doubleData;

    @ExcelProperty({"主标题2", "字符串标题"})
    private String string2;
    @ExcelProperty({"主标题2", "日期标题"})
    private String date2;
    @ExcelProperty({"主标题2", "数字标题"})
    private String doubleData2;

    @ExcelProperty(value = "客户平均标签个数")
    private String doubleData3;

}