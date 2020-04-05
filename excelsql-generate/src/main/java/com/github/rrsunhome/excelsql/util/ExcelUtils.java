package com.github.rrsunhome.excelsql.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * POI实现excel文件读写(导入/导出)操作工具类
 *
 * @author qijia.wang
 */
public class ExcelUtils {


    /**
     * @param is 输入流
     * @param sheetIndex sheet index
     * @param startRowIndex 开始行
     * @return 返回数据
     * @throws Exception 异常
     */
    public static List<List<Object>> readExcel(InputStream is, int sheetIndex, int startRowIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Workbook wb = getWorkbook(is);
        if (wb != null) {
            Sheet sheet = getSheet(wb, sheetIndex);
            list = getSheetData(wb, sheet, startRowIndex);
        }
        return list;
    }

    /**
     * @param is 输入流
     * @param sheetName sheet name
     * @param startRowIndex 开始行
     * @return  返回数据
     * @throws Exception 异常
     */
    public static List<List<Object>> readExcel(InputStream is, String sheetName, int startRowIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Workbook wb = getWorkbook(is);
        if (wb != null) {
            Sheet sheet = getSheet(wb, sheetName);
            list = getSheetData(wb, sheet, startRowIndex);
        }
        return list;
    }


    /**
     * 根据workbook获取该workbook的所有sheet
     *
     * @param wb Workbook
     * @return  返回数据
     */
    public static List<Sheet> getAllSheets(Workbook wb) {
        int numOfSheets = wb.getNumberOfSheets();
        List<Sheet> sheets = new ArrayList<Sheet>();
        for (int i = 0; i < numOfSheets; i++) {
            sheets.add(wb.getSheetAt(i));
        }
        return sheets;
    }

    /**
     * 根据excel文件来获取workbook
     *
     * @param file 文件
     * @return workbook
     * @throws Exception 异常
     */
    public static Workbook getWorkbook(File file) throws Exception {
        return getWorkbook(new FileInputStream(file));
    }

    /**
     * @param is 输入流
     * @return workbook
     * @throws Exception 异常
     */
    public static Workbook getWorkbook(InputStream is) throws Exception {
        Workbook wb = WorkbookFactory.create(is);
        return wb;
    }


    /**
     * 根据excel文件来获取workbook
     *
     * @param filePath 文件地址
     * @return workbook
     * @throws Exception 异常
     */
    public static Workbook getWorkbook(String filePath) throws Exception {
        File file = new File(filePath);
        return getWorkbook(file);
    }



    /**
     * 根据workbook和sheet的下标索引值来获取sheet
     *
     * @param wb Workbook
     * @param sheetIndex 下标
     * @return sheet
     */
    public static Sheet getSheet(Workbook wb, int sheetIndex) {
        return wb.getSheetAt(sheetIndex);
    }

    /**
     * 根据workbook和sheet的名称来获取sheet
     *
     * @param wb Workbook
     * @param sheetName sheetName
     * @return sheet
     */
    public static Sheet getSheet(Workbook wb, String sheetName) {
        return wb.getSheet(sheetName);
    }

    /**
     *
     * @param wb Workbook
     * @param sheet Sheet
     * @param startRowIndex startRowIndex
     * @return 嵌套数据
     * @throws Exception 异常
     */
    public static List<List<Object>> getSheetData(Workbook wb, Sheet sheet, int startRowIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();

        /**
         * getLastRowNum方法能够正确返回最后一行的位置；
         * getPhysicalNumberOfRows方法能够正确返回物理的行数；
         */
        // 获取总行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        // int rowNum = sheet.getLastRowNum();
        // 获取标题行
        Row headerRow = sheet.getRow(0);
        // 标题总列数
        int colNum = headerRow.getPhysicalNumberOfCells();

        for (int i = startRowIndex; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            boolean allRowIsBlank = isBlankRow(wb, row);
            if (allRowIsBlank) { // 整行都空，就跳过
                continue;
            }
            List<Object> rowData = getRowData(wb, row, (short) colNum);
            list.add(rowData);
        }
        return list;
    }


    /**
     *
     * @param wb Workbook
     * @param row Row
     * @return boolean
     */
    public static boolean isBlankRow(Workbook wb, Row row) {
        boolean allRowIsBlank = true;
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Object cellValue = getCellValue(wb, cellIterator.next());
            if (cellValue != null && !"".equals(cellValue)) {
                allRowIsBlank = false;
                break;
            }
        }
        return allRowIsBlank;
    }

    /**
     * 获取行的数据
     *
     * @param wb Workbook
     * @param row Row
     * @param lastCellNum lastCellNum
     * @return  封装的数据
     */
    public static List<Object> getRowData(Workbook wb, Row row, short lastCellNum) {
        List<Object> rowData = new ArrayList<Object>();
        /**
         * 不建议用row.cellIterator(), 因为空列会被跳过， 后面的列会前移， 建议用for循环， row.getLastCellNum()是获取最后一个不为空的列是第几个
         * 结论：空行可以跳过， 空列最好不要跳过
         */
        /*Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
        	Cell cell = cellIterator.next();
            Object cellValue = getCellValue(wb, cell);
            rowData.add(cellValue);
        }*/
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = row.getCell(i);
            Object cellValue = getCellValue(wb, cell);
            rowData.add(cellValue);
        }
        return rowData;
    }

    /**
     *
     * @param wb Workbook
     * @param cell Cell
     * @return String
     */
    private static String getCellValue(Workbook wb, Cell cell) {
        //判断是否为null或空串
        if (cell == null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_FORMULA) { //表达式类型
            cellType = cell.getCellType();
        }

        switch (cellType) {
            case Cell.CELL_TYPE_STRING: //字符串类型
                cellValue = cell.getStringCellValue().trim();
                cellValue = StringUtils.isEmpty(cellValue) ? "" : cellValue;
                break;
            case Cell.CELL_TYPE_BOOLEAN:  //布尔类型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC: //数值类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(cell.getDateCellValue());
                } else {  //否
                    cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            default: //其它类型，取空串吧
                cellValue = "";
                break;
        }
        return cellValue;
    }

    /**
     * 根据sheet返回该sheet的物理总行数
     * sheet.getPhysicalNumberOfRows方法能够正确返回物理的行数
     *
     * @param sheet Sheet
     * @return int
     */
    public static int getSheetPhysicalRowNum(Sheet sheet) {
        // 获取总行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        return rowNum;
    }

    /**
     * 获取操作的行数
     *
     * @param startRowIndex sheet的开始行位置索引
     * @param endRowIndex   sheet的结束行位置索引
     * @return 总行数
     */
    public static int getSheetDataPhysicalRowNum(int startRowIndex, int endRowIndex) {
        int rowNum = -1;
        if (startRowIndex >= 0 && endRowIndex >= 0 && startRowIndex <= endRowIndex) {
            rowNum = endRowIndex - startRowIndex + 1;
        }
        return rowNum;
    }

}
