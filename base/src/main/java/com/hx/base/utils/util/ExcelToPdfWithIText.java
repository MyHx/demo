package com.hx.base.utils.util;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelToPdfWithIText {
    // ==================== 核心微调参数（关键修改点） ====================
    // 1. 数据行高从15f→14f（小幅压缩，不影响可读性）
    private static final float HEADER_ROW_HEIGHT = 20f;
    private static final float DATA_ROW_HEIGHT = 14f;
    // 2. 上下边距从10f→8f（增加页面可用高度），左右保持5f
    private static final float PAGE_MARGIN_LEFT = 5f;
    private static final float PAGE_MARGIN_RIGHT = 5f;
    private static final float PAGE_MARGIN_TOP = 8f;
    private static final float PAGE_MARGIN_BOTTOM = 8f;
    // 表格缩放保持98%，列宽不变（避免影响长题号显示）
    private static final float TABLE_WIDTH_PERCENTAGE = 98f;
    private static final float FIXED_COL_WIDTH = 45f;
    private static final float CONTENT_COL_WIDTH = 35f;
    // 其他常量不变
    private static final String CHINESE_FONT_PATH = "C:/Windows/Fonts/simsun.ttc";
    private static final int FIXED_COL_COUNT = 3;
    private static final int QUESTION_TITLE_COL = 2;

    private static List<CellRangeAddress> mergedRegions;
    private static int dynamicHeaderEndRow = 2;
    // 重新计算页面可用宽高（适配新边距）
    private static final float A4_AVAILABLE_WIDTH = PageSize.A4.getWidth() - PAGE_MARGIN_LEFT - PAGE_MARGIN_RIGHT;
    private static final float A4_AVAILABLE_HEIGHT = PageSize.A4.getHeight() - PAGE_MARGIN_TOP - PAGE_MARGIN_BOTTOM;

    /**
     * 核心转换方法：Excel → PDF
     * @param excelPath Excel文件路径
     * @param pdfPath PDF输出路径
     * @throws IOException Excel读取异常
     * @throws DocumentException PDF生成异常
     */
    public static void convert(String excelPath, String pdfPath) throws IOException, DocumentException {
        Workbook workbook = new XSSFWorkbook(new FileInputStream(excelPath));
        Sheet sheet = workbook.getSheetAt(0);
        mergedRegions = sheet.getMergedRegions();
        int totalRows = sheet.getPhysicalNumberOfRows();
        int totalCols = getMaxColNum(sheet);
        validateQuestionColumns(sheet, totalCols);

        Document document = new Document(PageSize.A4,
                                        PAGE_MARGIN_LEFT,
                                        PAGE_MARGIN_RIGHT,
                                        PAGE_MARGIN_TOP,
                                        PAGE_MARGIN_BOTTOM);
        PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
        document.open();

        int headerRowCount = dynamicHeaderEndRow + 1;
        // 3. 分页计算：去掉-1的预留缓冲（原公式-1→0），最大化可用行数
        int contentRowsPerPage = (int) ((A4_AVAILABLE_HEIGHT - headerRowCount * HEADER_ROW_HEIGHT) / DATA_ROW_HEIGHT);
        contentRowsPerPage = Math.max(contentRowsPerPage, 5); // 最低保证5行，不影响兜底

        int contentColsPerPage = (int) ((A4_AVAILABLE_WIDTH - FIXED_COL_COUNT * FIXED_COL_WIDTH) / CONTENT_COL_WIDTH);
        contentColsPerPage = Math.max(contentColsPerPage, 1);

        int contentColStart = FIXED_COL_COUNT;
        while (contentColStart < totalCols) {
            int contentColEnd = Math.min(contentColStart + contentColsPerPage - 1, totalCols - 1);
            int currentPageTotalCols = FIXED_COL_COUNT + (contentColEnd - contentColStart + 1);

            int contentRowStart = dynamicHeaderEndRow + 1;
            while (contentRowStart < totalRows) {
                int contentRowEnd = Math.min(contentRowStart + contentRowsPerPage - 1, totalRows - 1);

                PdfPTable pdfTable = new PdfPTable(currentPageTotalCols);
                pdfTable.setWidthPercentage(TABLE_WIDTH_PERCENTAGE);
                // 4. 表格上下间距从5f→3f（减少冗余空白）
                pdfTable.setSpacingBefore(3f);
                pdfTable.setSpacingAfter(3f);

                drawHeader(pdfTable, sheet, contentColStart, contentColEnd);
                drawContent(pdfTable, sheet, contentRowStart, contentRowEnd, contentColStart, contentColEnd);
                document.add(pdfTable);

                boolean isLastPage = (contentColEnd == totalCols - 1) && (contentRowEnd == totalRows - 1);
                if (isLastPage) {
                    // 创建备注段落（右对齐，8号字体，与数据行一致）
                    Paragraph notePara = new Paragraph("√表示对，×表示错，△表示半对", getChineseFont(false));
                    notePara.setAlignment(Element.ALIGN_RIGHT); // 右对齐→实现右下角效果
                    notePara.setSpacingBefore(5f); // 与表格保持5f间距，避免重叠
                    document.add(notePara);
                } else {
                    document.newPage();
                }

                contentRowStart = contentRowEnd + 1;
            }

            contentColStart = contentColEnd + 1;
        }

        document.close();
        workbook.close();
        System.out.println("PDF生成成功！路径：" + pdfPath);
    }

    /**
     * 绘制表头
     *
     * @param pdfTable         pdf表格对象
     * @param sheet            表格对象
     * @param colStart         列起始索引
     * @param colEnd           列结束索引
     */
    private static void drawHeader(PdfPTable pdfTable, Sheet sheet, int colStart, int colEnd) {
        for (int rowIdx = 0; rowIdx <= dynamicHeaderEndRow; rowIdx++) {
            Row excelRow = sheet.getRow(rowIdx);
            if (excelRow == null) continue;

            for (int colIdx = 0; colIdx < FIXED_COL_COUNT; colIdx++) {
                addMergedCell(pdfTable, sheet, rowIdx, colIdx, colStart, colEnd);
            }

            for (int colIdx = colStart; colIdx <= colEnd; colIdx++) {
                if (rowIdx == 1) {
                    String cellValue = getCellValue(excelRow, colIdx);
                    PdfPCell cell = new PdfPCell(new Phrase(cellValue, getChineseFont(true)));
                    cell.setFixedHeight(ExcelToPdfWithIText.HEADER_ROW_HEIGHT);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setPadding(3f);
                    cell.setNoWrap(true);
                    pdfTable.addCell(cell);
                } else {
                    addMergedCell(pdfTable, sheet, rowIdx, colIdx, colStart, colEnd);
                }
            }
        }
    }

    /**
     * 绘制内容
     * @param pdfTable pdf表格对象
     * @param sheet 表格对象
     * @param rowStart 行起始索引
     * @param rowEnd 行结束索引
     * @param colStart 列起始索引
     * @param colEnd 列结束索引
     */
    private static void drawContent(PdfPTable pdfTable, Sheet sheet, int rowStart, int rowEnd,
                                    int colStart, int colEnd) {
        for (int rowIdx = rowStart; rowIdx <= rowEnd; rowIdx++) {
            Row excelRow = sheet.getRow(rowIdx);
            if (excelRow == null) {
                addEmptyRow(pdfTable, FIXED_COL_COUNT + (colEnd - colStart + 1));
                continue;
            }

            for (int colIdx = 0; colIdx < FIXED_COL_COUNT; colIdx++) {
                String cellValue = getCellValue(excelRow, colIdx);
                addCell(pdfTable, cellValue, false, ExcelToPdfWithIText.DATA_ROW_HEIGHT);
            }

            for (int colIdx = colStart; colIdx <= colEnd; colIdx++) {
                String cellValue = getCellValue(excelRow, colIdx);
                addCell(pdfTable, cellValue, false, ExcelToPdfWithIText.DATA_ROW_HEIGHT);
            }
        }
    }

    private static void addMergedCell(PdfPTable pdfTable, Sheet sheet, int rowIdx, int colIdx,
                                      int colStart, int colEnd) {
        CellRangeAddress mergeRegion = getMergeRegion(sheet, rowIdx, colIdx);
        if (mergeRegion == null) {
            String cellValue = getCellValue(sheet.getRow(rowIdx), colIdx);
            addCell(pdfTable, cellValue, true, ExcelToPdfWithIText.HEADER_ROW_HEIGHT);
            return;
        }

        if (mergeRegion.getFirstRow() != rowIdx || mergeRegion.getFirstColumn() != colIdx) {
            return;
        }

        int rowSpan = mergeRegion.getLastRow() - mergeRegion.getFirstRow() + 1;
        int colSpan = mergeRegion.getLastColumn() - mergeRegion.getFirstColumn() + 1;

        if (colIdx == 2) {
            colSpan = 1;
        } else if (mergeRegion.getFirstColumn() >= colStart) {
            int maxColInPage = colEnd - colStart + FIXED_COL_COUNT;
            int currentColInPage = FIXED_COL_COUNT + (mergeRegion.getFirstColumn() - colStart);
            colSpan = Math.min(colSpan, maxColInPage - currentColInPage + 1);
        }

        String cellValue = getCellValue(sheet.getRow(rowIdx), colIdx);
        PdfPCell cell = new PdfPCell(new Phrase(cellValue, getChineseFont(true)));
        cell.setRowspan(rowSpan);
        cell.setColspan(colSpan);
        cell.setFixedHeight(ExcelToPdfWithIText.HEADER_ROW_HEIGHT * rowSpan);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(3f);
        cell.setNoWrap(true);
        pdfTable.addCell(cell);
    }

    private static void addCell(PdfPTable pdfTable, String value, boolean isTitle, float rowHeight) {
        PdfPCell cell = new PdfPCell(new Phrase(value == null ? "" : value, getChineseFont(isTitle)));
        cell.setFixedHeight(rowHeight);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(3f);
        cell.setNoWrap(true);
        pdfTable.addCell(cell);
    }

    private static void addEmptyRow(PdfPTable pdfTable, int colCount) {
        for (int i = 0; i < colCount; i++) {
            addCell(pdfTable, "", false, ExcelToPdfWithIText.DATA_ROW_HEIGHT);
        }
    }

    private static CellRangeAddress getMergeRegion(Sheet sheet, int row, int col) {
        for (CellRangeAddress region : sheet.getMergedRegions()) {
            if (region.isInRange(row, col)) {
                return region;
            }
        }
        return null;
    }

    private static int getMaxColNum(Sheet sheet) {
        int maxCol = 0;
        for (int rowIdx = 0; rowIdx < sheet.getPhysicalNumberOfRows(); rowIdx++) {
            Row row = sheet.getRow(rowIdx);
            if (row == null) continue;
            maxCol = Math.max(maxCol, row.getLastCellNum());
        }
        return maxCol;
    }

    private static String getCellValue(Row row, int col) {
        if (row == null) return "";
        Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String cellValue = "";

        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getStringCellValue().trim();
                break;
            case NUMERIC:
                double numericValue = cell.getNumericCellValue();
                if (numericValue == (long) numericValue) {
                    cellValue = String.valueOf((long) numericValue);
                } else {
                    cellValue = String.valueOf(numericValue);
                }
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }

    /**
     * 获取中文字体
     * @param isTitle 是否为标题
     * @return 字体对象
     */
    private static Font getChineseFont(boolean isTitle) {
        try {
            BaseFont baseFont = BaseFont.createFont(CHINESE_FONT_PATH + ",0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            int fontSize = isTitle ? 9 : 8;
            int fontStyle = isTitle ? Font.BOLD : Font.NORMAL;
            return new Font(baseFont, fontSize, fontStyle);
        } catch (Exception e) {
            e.printStackTrace();
            // fallback字体也同步缩小
            return isTitle ? FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9) : FontFactory.getFont(FontFactory.HELVETICA, 8);
        }
    }

    private static void validateQuestionColumns(Sheet sheet, int totalCols) {
        if (totalCols <= FIXED_COL_COUNT) {
            throw new RuntimeException("Excel格式错误：固定列（序号/姓名/题号）之后无题目列！");
        }

        Row questionRow = sheet.getRow(1);
        if (questionRow == null) {
            throw new RuntimeException("Excel格式错误：未找到题号行（第2行）！");
        }

        int validQuestionCount = 0;
        for (int colIdx = FIXED_COL_COUNT; colIdx < totalCols; colIdx++) {
            String questionValue = getCellValue(questionRow, colIdx);
            if (!questionValue.isEmpty()) {
                validQuestionCount++;
            }
        }

        if (validQuestionCount == 0) {
            throw new RuntimeException("Excel格式错误：题号行（第2行）无有效题号！");
        }
    }

    public static void main(String[] args) {
        try {
            String excelPath = "C:\\Users\\lamroy\\Desktop\\物理作业202510141425_班级成绩单.xls";
            String pdfPath = "C:\\Users\\lamroy\\Desktop\\班级成绩单_优化版.pdf";
            convert(excelPath, pdfPath);
        } catch (Exception e) {
            System.err.println("PDF生成失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}