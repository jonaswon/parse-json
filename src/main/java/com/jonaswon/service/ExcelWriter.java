package com.jonaswon.service;

import com.jonaswon.utils.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ExcelWriter {

    private static Logger logger = Logger.getLogger(ExcelWriter.class.getName()); // 日志打印类

    /**
     * 生成Excel并写入数据信息
     * @param rows 数据列表
     * @return 写入数据后的工作簿对象
     */
    public static void exportData(Workbook workbook, List<Map<String, Object>> rows, String sheetName){
        if (null == rows || rows.isEmpty()) {
            return;
        }

        // 列头
        List<String> cellHeads = new ArrayList<>();
        cellHeads.add("sn");
        Map<String, Object> firstMap = rows.get(0);
        for (String colName : firstMap.keySet()) {
            cellHeads.add(colName);
        }

        // 生成Sheet表，写入第一行的列头
        Sheet sheet = buildDataSheet(workbook, cellHeads, sheetName);
        //构建每行的数据内容
        int rowNum = 1;
        Map<String, Object> data = null;
        for (Iterator<Map<String, Object>> it = rows.iterator(); it.hasNext(); ) {
            data = it.next();
            if (data == null) {
                continue;
            }
            //输出行数据
            Row row = sheet.createRow(rowNum);
            convertDataToRow(data, row, rowNum++, workbook);
        }
    }

    /**
     * 生成sheet表，并写入第一行数据（列头）
     * @param workbook 工作簿对象
     * @return 已经写入列头的Sheet
     */
    private static Sheet buildDataSheet(Workbook workbook, List<String> cellHeads, String sheetName) {
        Sheet sheet = workbook.createSheet(sheetName);
        // 设置列头宽度
        int defaultWidth = 4000;
        int width = 0;

        int headSize = cellHeads.size();
        for (int i=0; i<headSize; i++) {
            width = defaultWidth;
            sheet.setColumnWidth(i, width);
        }
        // 设置默认行高
        sheet.setDefaultRowHeight((short) 400);
        // 构建头单元格样式
        CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
        // 写入第一行各列的数据
        Row head = sheet.createRow(0);
        for (int i = 0; i < headSize; i++) {
            Cell cell = head.createCell(i);
            cell.setCellValue(cellHeads.get(i));
            cell.setCellStyle(cellStyle);
        }
        return sheet;
    }

    /**
     * 设置第一行列头的样式
     * @param workbook 工作簿对象
     * @return 单元格样式对象
     */
    private static CellStyle buildHeadCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //对齐方式设置
        style.setAlignment(HorizontalAlignment.CENTER);
        //边框颜色和宽度设置
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
        //设置背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //粗体字设置
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * 将数据转换成行
     * @param data 源数据
     * @param row 行对象
     * @return
     */
    private static void convertDataToRow(Map<String, Object> data, Row row, int sn, Workbook workbook){
        int cellNum = 0;
        Cell cell;

        // 序号
        cell = row.createCell(cellNum++);
        cell.setCellValue(sn);

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String curCompareKey = entry.getKey();
            Object curCompareVal = entry.getValue();

            cell = row.createCell(cellNum++);
            if (curCompareVal instanceof BigDecimal) {
                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
                cell.setCellStyle(cellStyle);
            }
            cell.setCellValue(curCompareVal+"");
        }
    }

    /**
     * 保存到excel文件中并存储到硬盘中
     * @param workbook
     * @param excelFileName
     */
    public static void flushToExcel(Workbook workbook, String excelFileName) throws IOException {
        // 以文件的形式输出工作簿对象
        FileOutputStream fileOut = null;
        try {
            // 创建文件
            FileUtils.createFile(excelFileName);

            fileOut = new FileOutputStream(excelFileName);
            workbook.write(fileOut);
            fileOut.flush();
        } catch (Exception e) {
            logger.warning("输出Excel时发生错误，错误原因：" + e.getMessage());
            throw e;
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                logger.warning("关闭输出流时发生错误，错误原因：" + e.getMessage());
                throw e;
            }
        }
    }
}
