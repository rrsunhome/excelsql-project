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
 * @keywords java excel poi 导入 导出
 */
public class ExcelUtils {


    /**
     * 对外提供读取excel的方法， 当且仅当只有一个sheet， 默认从第一个sheet读取数据
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(File file) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        if (file.exists() && file.isFile()) {
            Workbook wb = getWorkbook(file);
            if (wb != null) {
                Sheet sheet = getSheet(wb, 0);
                list = getSheetData(wb, sheet);
            }
        }
        return list;
    }

    /**
     * 对外提供读取excel的方法， 根据sheet下标索引读取sheet数据
     *
     * @param file
     * @param sheetIndex
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(File file, int sheetIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        if (file.exists() && file.isFile()) {
            Workbook wb = getWorkbook(file);
            if (wb != null) {
                Sheet sheet = getSheet(wb, sheetIndex);
                list = getSheetData(wb, sheet);
            }
        }
        return list;
    }

    /**
     * 对外提供读取excel的方法， 根据sheet下标索引读取sheet对象， 并指定行列区间获取数据[startRowIndex, endRowIndex), [startColumnIndex, endColumnIndex)
     *
     * @param file
     * @param sheetIndex
     * @param startRowIndex
     * @param endRowIndex
     * @param startColumnIndex
     * @param endColumnIndex
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(File file, int sheetIndex, int startRowIndex, int endRowIndex,
                                               int startColumnIndex, int endColumnIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Workbook wb = getWorkbook(file);
        if (wb != null) {
            Sheet sheet = getSheet(wb, sheetIndex);
            list = getSheetData(wb, sheet, startRowIndex, endRowIndex, startColumnIndex, endColumnIndex);
        }
        return list;
    }

    public static List<List<Object>> readExcel(File file, int sheetIndex, int startRowIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Workbook wb = getWorkbook(file);
        if (wb != null) {
            Sheet sheet = getSheet(wb, sheetIndex);
            list = getSheetData(wb, sheet, startRowIndex);
        }
        return list;
    }

    public static List<List<Object>> readExcel(InputStream is, int sheetIndex, int startRowIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Workbook wb = getWorkbook(is);
        if (wb != null) {
            Sheet sheet = getSheet(wb, sheetIndex);
            list = getSheetData(wb, sheet, startRowIndex);
        }
        return list;
    }

    public static List<List<Object>> readExcel(File file, String sheetName, int startRowIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Workbook wb = getWorkbook(file);
        if (wb != null) {
            Sheet sheet = getSheet(wb, sheetName);
            list = getSheetData(wb, sheet, startRowIndex);
        }
        return list;
    }

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
     * 对外提供读取excel的方法， 根据sheet名称读取sheet数据
     *
     * @param file
     * @param sheetName
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(File file, String sheetName) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Workbook wb = getWorkbook(file);
        if (wb != null) {
            Sheet sheet = getSheet(wb, sheetName);
            list = getSheetData(wb, sheet);
        }
        return list;
    }

    public static List<List<Object>> readExcel(InputStream is, String sheetName) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Workbook wb = getWorkbook(is);
        if (wb != null) {
            Sheet sheet = getSheet(wb, sheetName);
            list = getSheetData(wb, sheet);
        }
        return list;
    }

    /**
     * 对外提供读取excel的方法， 根据sheet名称读取sheet对象， 并指定行列区间获取数据[startRowIndex, endRowIndex), [startColumnIndex, endColumnIndex)
     *
     * @param file
     * @param sheetName
     * @param startRowIndex
     * @param endRowIndex
     * @param startColumnIndex
     * @param endColumnIndex
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(File file, String sheetName, int startRowIndex, int endRowIndex,
                                               int startColumnIndex, int endColumnIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Workbook wb = getWorkbook(file);
        if (wb != null) {
            Sheet sheet = getSheet(wb, sheetName);
            list = getSheetData(wb, sheet, startRowIndex, endRowIndex, startColumnIndex, endColumnIndex);
        }
        return list;
    }

    /**
     * 读取excel的正文内容
     *
     * @param file
     * @param sheetIndex sheet的下标索引值
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcelBody(File file, int sheetIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        if (file.exists() && file.isFile()) {
            Workbook wb = getWorkbook(file);
            if (wb != null) {
                Sheet sheet = getSheet(wb, sheetIndex);
                list = getSheetBodyData(wb, sheet);
            }
        }
        return list;
    }

    /**
     * 读取excel的正文内容
     *
     * @param file
     * @param sheetName sheet的名称
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcelBody(File file, String sheetName) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        if (file.exists() && file.isFile()) {
            Workbook wb = getWorkbook(file);
            if (wb != null) {
                Sheet sheet = getSheet(wb, sheetName);
                list = getSheetBodyData(wb, sheet);
            }
        }
        return list;
    }

    /**
     * 对外提供读取excel的方法， 当且仅当只有一个sheet， 默认从第一个sheet读取数据
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(String filePath) throws Exception {
        File file = new File(filePath);
        return readExcel(file);
    }

    /**
     * 对外提供读取excel的方法， 根据sheet下标索引读取sheet数据
     *
     * @param filePath
     * @param sheetIndex
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(String filePath, int sheetIndex) throws Exception {
        File file = new File(filePath);
        return readExcel(file, sheetIndex);
    }

    /**
     * 对外提供读取excel的方法， 根据sheet下标索引读取sheet对象， 并指定行列区间获取数据[startRowIndex, endRowIndex), [startColumnIndex, endColumnIndex)
     *
     * @param filePath
     * @param sheetIndex
     * @param startRowIndex
     * @param endRowIndex
     * @param startColumnIndex
     * @param endColumnIndex
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(String filePath, int sheetIndex, int startRowIndex, int endRowIndex,
                                               int startColumnIndex, int endColumnIndex) throws Exception {
        File file = new File(filePath);
        return readExcel(file, sheetIndex, startRowIndex, endRowIndex, startColumnIndex, endColumnIndex);
    }

    /**
     * 对外提供读取excel的方法， 根据sheet名称读取sheet数据
     *
     * @param filePath
     * @param sheetName
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(String filePath, String sheetName) throws Exception {
        File file = new File(filePath);
        return readExcel(file, sheetName);
    }

    /**
     * 对外提供读取excel的方法， 根据sheet名称读取sheet对象， 并指定行列区间获取数据[startRowIndex, endRowIndex), [startColumnIndex, endColumnIndex)
     *
     * @param filePath
     * @param sheetName
     * @param startRowIndex
     * @param endRowIndex
     * @param startColumnIndex
     * @param endColumnIndex
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(String filePath, String sheetName, int startRowIndex, int endRowIndex,
                                               int startColumnIndex, int endColumnIndex) throws Exception {
        File file = new File(filePath);
        return readExcel(file, sheetName, startRowIndex, endRowIndex, startColumnIndex, endColumnIndex);
    }

    /**
     * 读取excel的正文内容
     *
     * @param filePath
     * @param sheetIndex sheet的下标索引值
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcelBody(String filePath, int sheetIndex) throws Exception {
        File file = new File(filePath);
        return readExcelBody(file, sheetIndex);
    }

    /**
     * 读取excel的正文内容
     *
     * @param filePath
     * @param sheetName sheet的名称
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcelBody(String filePath, String sheetName) throws Exception {
        File file = new File(filePath);
        return readExcelBody(file, sheetName);
    }

    /**
     * 根据workbook获取该workbook的所有sheet
     *
     * @param wb
     * @return List<Sheet>
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
     * @param file
     * @return workbook
     * @throws Exception
     */
    public static Workbook getWorkbook(File file) throws Exception {
        return getWorkbook(new FileInputStream(file));
    }

    public static Workbook getWorkbook(InputStream is) throws Exception {
        Workbook wb = WorkbookFactory.create(is);
        return wb;
    }


    public static Workbook getWorkbook(InputStream is, String fileName) throws Exception {
        Workbook wb = null;
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1); // 获取文件后缀
        if ("xls".equals(extension)) { // for office2003
            wb = new HSSFWorkbook(is);
        } else if ("xlsx".equals(extension)) { // for office2007
            wb = new XSSFWorkbook(is);
        } else {
            throw new Exception("不支持的文件类型");
        }

        return wb;
    }

    /**
     * 根据excel文件来获取workbook
     *
     * @param filePath
     * @return workbook
     * @throws Exception
     */
    public static Workbook getWorkbook(String filePath) throws Exception {
        File file = new File(filePath);
        return getWorkbook(file);
    }

    /**
     * 获取xls
     *
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbookV1() throws Exception {
        return getExportWorkbookV1("xls");
    }

    /**
     * 根据excel文件输出路径来获取对应的workbook
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static Workbook getExportWorkbook(String filePath) throws Exception {
        Workbook wb = null;
        File file = new File(filePath);

        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1); // 获取文件后缀

        if ("xls".equals(extension)) { // for 少量数据
            wb = new HSSFWorkbook();
        } else if ("xlsx".equals(extension)) { // for 大量数据
            wb = new SXSSFWorkbook(5000); // 定义内存里一次只留5000行
        } else {
            throw new Exception("不支持的文件类型");
        }
        return wb;
    }

    /**
     * 根据excel文件扩展名来获取对应的workbook
     *
     * @param extension
     * @return
     * @throws Exception
     */
    public static Workbook getExportWorkbookV1(String extension) throws Exception {
        Workbook wb = null;
        if ("xls".equals(extension)) { // for 少量数据
            wb = new HSSFWorkbook();
        } else if ("xlsx".equals(extension)) { // for 大量数据
            wb = new SXSSFWorkbook(5000); // 定义内存里一次只留5000行
        } else {
            throw new Exception("不支持的文件类型");
        }
        return wb;
    }

    /**
     * 根据workbook和sheet的下标索引值来获取sheet
     *
     * @param wb
     * @param sheetIndex
     * @return sheet
     */
    public static Sheet getSheet(Workbook wb, int sheetIndex) {
        return wb.getSheetAt(sheetIndex);
    }

    /**
     * 根据workbook和sheet的名称来获取sheet
     *
     * @param wb
     * @param sheetName
     * @return sheet
     */
    public static Sheet getSheet(Workbook wb, String sheetName) {
        return wb.getSheet(sheetName);
    }

    /**
     * 根据sheet获取该sheet的所有数据
     *
     * @param sheet
     * @return
     */
    public static List<List<Object>> getSheetData(Workbook wb, Sheet sheet) {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        short lastCellNum = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            lastCellNum = row.getLastCellNum();

//            if (row.getRowNum() == 0) {
//                continue;
//            }
            boolean allRowIsBlank = isBlankRow(wb, row);
            if (allRowIsBlank) { // 整行都空，就跳过
                continue;
            }
            List<Object> rowData = getRowData(wb, row, lastCellNum);
            list.add(rowData);
        }
        return list;
    }

    /**
     * 读取正文数据， 从第二行起
     *
     * @param wb
     * @param sheet
     * @return
     * @throws Exception
     */
    public static List<List<Object>> getSheetBodyData(Workbook wb, Sheet sheet) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        // 获取总行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        // 获取标题行
        Row headerRow = sheet.getRow(0);
        // 标题总列数
        int colNum = headerRow.getPhysicalNumberOfCells();
        // 获取正文内容， 正文内容应该从第二行开始,第一行为表头的标题
        list.addAll(getSheetData(wb, sheet, 1, rowNum, 0, colNum));
        return list;
    }

    /**
     * 根据sheet获取该sheet的指定行列的数据[startRowIndex, endRowIndex), [startColumnIndex, endColumnIndex)
     *
     * @param wb
     * @param sheet
     * @param startRowIndex    开始行索引值
     * @param endRowIndex      结束行索引值
     * @param startColumnIndex 开始列索引值
     * @param endColumnIndex   结束列索引值
     * @return
     * @throws Exception
     */
    public static List<List<Object>> getSheetData(Workbook wb, Sheet sheet, int startRowIndex, int endRowIndex,
                                                  int startColumnIndex, int endColumnIndex) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();
        if (startRowIndex > endRowIndex || startColumnIndex > endColumnIndex) {
            return list;
        }

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

        if (endRowIndex > rowNum) {
            throw new Exception("行的最大下标索引超过了该sheet实际总行数(包括标题行)" + rowNum);
        }
        if (endColumnIndex > colNum) {
            throw new Exception("列的最大下标索引超过了实际标题总列数" + colNum);
        }
        for (int i = startRowIndex; i < endRowIndex; i++) {
            Row row = sheet.getRow(i);
            boolean allRowIsBlank = isBlankRow(wb, row);
            if (allRowIsBlank) { // 整行都空，就跳过
                continue;
            }
            List<Object> rowData = getRowData(wb, row, startColumnIndex, endColumnIndex);
            list.add(rowData);
        }
        return list;
    }

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
     * 根据指定列区间获取行的数据
     *
     * @param wb
     * @param row
     * @param startColumnIndex 开始列索引值
     * @param endColumnIndex   结束列索引值
     * @return
     */
    public static List<Object> getRowData(Workbook wb, Row row, int startColumnIndex, int endColumnIndex) {
        List<Object> rowData = new ArrayList<Object>();
        for (int j = startColumnIndex; j < endColumnIndex; j++) {
            Cell cell = row.getCell(j);
            rowData.add(getCellValue(wb, cell));
        }
        return rowData;
    }

    /**
     * 判断整行是不是都为空
     *
     * @param row
     * @return
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
     * @param wb
     * @param row
     * @param lastCellNum
     * @return
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

    private static String getCellValue(Workbook wb, Cell cell) {
        //判断是否为null或空串
        if (cell==null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        int cellType=cell.getCellType();
        if(cellType==Cell.CELL_TYPE_FORMULA){ //表达式类型
            cellType=cell.getCellType();
        }

        switch (cellType) {
            case Cell.CELL_TYPE_STRING: //字符串类型
                cellValue= cell.getStringCellValue().trim();
                cellValue= StringUtils.isEmpty(cellValue) ? "" : cellValue;
                break;
            case Cell.CELL_TYPE_BOOLEAN:  //布尔类型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC: //数值类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue =   sdf.format(cell.getDateCellValue());
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

    public static Object getCellValue(Cell cell) {
        if (cell == null
                || (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().isEmpty())) {
            return null;
        }
        /*if (cell == null) {
            return "";
        }*/
        // 如果该单元格为数字， 则设置该单元格类型为文本格式
        /*CellStyle cellStyle = wb.createCellStyle();
        DataFormat dataFormat = wb.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("@"));
        cell.setCellStyle(cellStyle);
        cell.setCellType(Cell.CELL_TYPE_STRING);*/

        DecimalFormat df = new DecimalFormat("0");// 格式化 number String字符
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化日期字符串
        DecimalFormat nf = new DecimalFormat("0");// 格式化数字

        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_BLANK:
                // return "";
                return null;
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                    String value = df.format(cell.getNumericCellValue());

                    return value;
                } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    String value = nf.format(cell.getNumericCellValue());

                    return value;
                } else {
                    return cell.getNumericCellValue();
                /*double doubleValue = cell.getNumericCellValue();
                long longValue = (long) doubleValue;
                if (doubleValue - longValue > 0) {
                	return String.valueOf(doubleValue);
                } else {
                	return longValue;
                }*/
                /*DecimalFormat df = new DecimalFormat("#");
                String value = df.format(cell.getNumericCellValue()).toString();
                return value;*/
                }
            case Cell.CELL_TYPE_STRING:
                String value = cell.getStringCellValue();
                return value;
            // return cell.getRichStringCellValue();
            default:
                // return "";
                return null;
        }
    }

    /**
     * 根据sheet返回该sheet的物理总行数
     * sheet.getPhysicalNumberOfRows方法能够正确返回物理的行数
     *
     * @param sheet
     * @return
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
     * @return
     */
    public static int getSheetDataPhysicalRowNum(int startRowIndex, int endRowIndex) {
        int rowNum = -1;
        if (startRowIndex >= 0 && endRowIndex >= 0 && startRowIndex <= endRowIndex) {
            rowNum = endRowIndex - startRowIndex + 1;
        }
        return rowNum;
    }


}
