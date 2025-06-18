package excel_test.poi.write;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Description: 将横向的表格变为纵向
 *
 * @author Baltan
 * @date 2020-06-17 14:06
 */
public class Test2 {
    /**
     * 创建Excel文件
     *
     * @param data
     * @param path
     * @param sheetName
     */
    public static void createExcel(String[][] data, String path, String sheetName) {
        /**
         * 创建一个文档
         */
        XSSFWorkbook doc = new XSSFWorkbook();
        /**
         * 创建一个sheet
         */
        XSSFSheet sheet = doc.createSheet(sheetName);
        /**
         * 行数
         */
        int rowNum = data.length;
        /**
         * 列数
         */
        int colNum = data[0].length;
        /**
         * 创建字体样式：11号、宋体、无边框、居中
         */
        XSSFCellStyle style = doc.createCellStyle();
        XSSFFont font = doc.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("宋体");
        font.setColor((short) 0);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFRow row;
        XSSFCell cell;
        /**
         * 填充内容
         */
        for (int i = 0; i < colNum; i++) {
            row = sheet.createRow(i);

            for (int j = 0; j < rowNum; j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                cell.setCellValue(data[j][i]);
            }
        }

        /**
         * 生成文件
         */
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(path);
            doc.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取Excel文件
     *
     * @param fileName
     * @param sheetNum
     * @return
     * @throws IOException
     */
    public static String[][] parseExcel(String fileName, int sheetNum) throws IOException {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook;

        try {
            workbook = new XSSFWorkbook(fis);
        } catch (Exception ex) {
            workbook = new HSSFWorkbook(fis);
        }

        Sheet sheet = workbook.getSheetAt(sheetNum);
        /**
         * 总行数
         */
        int rowNum = sheet.getLastRowNum();
        /**
         * 总列数
         */
        int columnNum = sheet.getRow(0).getLastCellNum();
        String[][] result = new String[rowNum + 1][columnNum];

        for (int j = 0; j <= rowNum; j++) {
            Row row = sheet.getRow(j);
            String[] rowData = new String[columnNum];

            for (int i = 0; i < columnNum; i++) {
                Cell cell = row.getCell(i);

                if (cell == null) {
                    rowData[i] = "";
                } else {
                    rowData[i] = getValue(cell);
                }
            }
            result[j] = rowData;
        }
        return result;
    }

    private static String getValue(Cell cell) {
        String obj;
        switch (cell.getCellType()) {
            case BOOLEAN:
                obj = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR:
                obj = String.valueOf(cell.getErrorCellValue());
                break;
            case NUMERIC:
                obj = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                obj = "";
                break;
        }
        return obj;
    }

    public static void main(String[] args) throws IOException {
        String[][] data = parseExcel("/Users/Baltan/Desktop/横着.xlsx", 0);
        createExcel(data, "/Users/Baltan/Desktop/竖着.xlsx", "xxx");
    }
}
