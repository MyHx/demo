package com.hx.excel;

import com.alibaba.excel.EasyExcel;
import com.hx.FileTest;
import com.hx.entity.ComplexHeadData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hexian
 * @date 2021/8/6 15:28
 */
public class ExcelDemo {
    /**
     * 复杂头写入
     * <p>1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
     * <p>3. 直接写即可
     */
    @Test
    public void complexHeadWrite() {
        List<ComplexHeadData> list = new ArrayList<>();
        ComplexHeadData data1 = new ComplexHeadData();
        data1.setDate("1d");
        data1.setDoubleData("1DD");
        data1.setString("mxx1");
        data1.setDate2("2d");
        data1.setDoubleData2("2DD");
        data1.setString2("mxx2");
        data1.setDoubleData3("ddd3");
        list.add(data1);
        ComplexHeadData data2 = new ComplexHeadData();
        data2.setDate("1d1");
        data2.setDoubleData("1DD1");
        data2.setString("1mxx1");
        data2.setDate2("2d2");
        data2.setDoubleData2("2DD2");
        data2.setString2("3mxx");
        data2.setDoubleData3("ddd111");
        list.add(data2);
        String fileName = FileTest.getPath() + "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ComplexHeadData.class).sheet("模板").doWrite(list);
    }
}
