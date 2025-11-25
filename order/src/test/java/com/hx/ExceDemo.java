package com.hx;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class ExceDemo {
    public static void main(String[] args) throws Exception {
        // 模拟数据
        List<String> questionTitles = Arrays.asList("1", "2", "3", "4", "5-1", "5-2", "5-3", "6", "7", "8");
        List<String> correctRates = Arrays.asList("80%", "75%", "90%", "85%", "90%", "75%", "85%", "80%", "75%", "80%");

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("成绩表");

        // 样式定义 ----------------------------
        CellStyle headerStyle = wb.createCellStyle();
        Font bold = wb.createFont();
        bold.setBold(true);
        headerStyle.setFont(bold);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        CellStyle centerStyle = wb.createCellStyle();
        centerStyle.cloneStyleFrom(headerStyle);
        centerStyle.setFont(wb.createFont());
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 圆点样式
        CellStyle greenStyle = wb.createCellStyle();
        greenStyle.cloneStyleFrom(centerStyle);
        greenStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
        greenStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle redStyle = wb.createCellStyle();
        redStyle.cloneStyleFrom(centerStyle);
        redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        redStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // ===================== 第一行表头 =====================
        Row header1 = sheet.createRow(0);
        int col = 0;
        header1.createCell(col++).setCellValue("序号");
        header1.createCell(col++).setCellValue("姓名");
        header1.createCell(col++).setCellValue("题号");

        // “题号”跨多列
        for (int i = 0; i < questionTitles.size(); i++) {
            Cell topicCell = header1.createCell(col++);
            topicCell.setCellValue(questionTitles.get(i));
        }

        // ===================== 第二行 - 每题正确率 =====================
        Row header2 = sheet.createRow(1);
        Cell cell1 = header2.createCell(2);
        cell1.setCellValue("正确率");
        col = 0;
        // 合并前2列的表头单元格
        for (int i = 0; i < 2; i++) {
            sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
        }

        // 写题目编号对应的正确率
        for (int i = 0; i < questionTitles.size(); i++) {
            Cell cell = header2.createCell(3 + i);
            cell.setCellValue(correctRates.get(i));
        }

        // 设置样式
        for (int r = 0; r <= 1; r++) {
            Row row = sheet.getRow(r);
            for (int i = 0; i < 3 + questionTitles.size(); i++) {
                Cell cell = row.getCell(i);
                if (cell == null) cell = row.createCell(i);
                cell.setCellStyle(headerStyle);
                sheet.setColumnWidth(i, 256 * 10);
            }
        }

        // ===================== 填充学生数据 =====================
        List<Student> students = Arrays.asList(
                new Student("曹佳怡", "80%", new boolean[]{true, true, true, true, true, false, true, true, false, true})
        );

        int rowIdx = 2;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            Row row = sheet.createRow(rowIdx++);
            int c = 0;
            row.createCell(c++).setCellValue(i + 1);
            row.createCell(c++).setCellValue(s.name);;
            row.createCell(c++).setCellValue(s.totalRate);

            // 每题结果
            for (boolean correct : s.answers) {
                Cell cell = row.createCell(c++);
                cell.setCellValue("●");
                cell.setCellStyle(correct ? greenStyle : redStyle);
            }

            // 行格式
            for (int j = 0; j < c; j++) {
                if (j >= 3) continue;
                row.getCell(j).setCellStyle(centerStyle);
            }
        }

        // 输出文件
        try (FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\lamroy\\Desktop\\成绩导出_合并表头.xlsx"))) {
            wb.write(fos);
        }
        wb.close();

        System.out.println("✅ 导出完成：成绩导出_合并表头.xlsx");
    }

    static class Student {
        String name;
        String totalRate;
        boolean[] answers;

        public Student(String name, String totalRate, boolean[] answers) {
            this.name = name;
            this.totalRate = totalRate;
            this.answers = answers;
        }
    }
}
