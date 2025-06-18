package excel.poi.write;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: Reference: https://blog.csdn.net/wwd0501/article/details/78780646
 *
 * @author Baltan
 * @date 2019-04-21 16:22
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        final String fileName = "/Users/Baltan/Desktop/资源.xlsx";
        final String path = "/Users/Baltan/Desktop/FuckOff.xlsx";
        final String sheetName = "FuckOff";
        Map<String, String[]> map1 = parseExcel(fileName, 5);
        Map<String, String[]> map2 = parseExcel(fileName, 6);
        Map<String, String[]> map = mergeSheet(map1, map2);
        createExcel(map, path, sheetName, new String[]{});
    }

    /**
     * 创建Excel文件
     *
     * @param map
     * @param path
     * @param sheetName
     * @param titles
     */
    public static void createExcel(Map<String, String[]> map, String path, String sheetName,
                                   String... titles) {
        int columnNum = titles.length;
        /**
         * 创建一个文档
         */
        XSSFWorkbook doc = new XSSFWorkbook();
        /**
         * 创建一个sheet
         */
        XSSFSheet sheet = doc.createSheet(sheetName);
        /**
         * 行对象
         */
        XSSFRow row;
        /**
         * 单元格对象
         */
        XSSFCell cell;
        /**
         *  设置表格列宽
         */
        for (int i = 0; i < columnNum; i++) {
            sheet.setColumnWidth(i, 3000);
        }
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
        /**
         * 第1行，表头行
         */
        row = sheet.createRow(0);

        for (int i = 0; i < columnNum; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(titles[i]);
        }
        /**
         * 填充内容
         */
        int count = 1;
        for (String key : map.keySet()) {
            String[] value = map.get(key);
            row = sheet.createRow(count++);

            for (int i = 0; i < columnNum; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(value[i]);
            }
        }
        /**
         * 生成文件
         */
        try (FileOutputStream fos = new FileOutputStream(path)) {
            doc.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 合并Excel文件的两个Sheet
     *
     * @param map1
     * @param map2
     * @return
     */
    public static Map<String, String[]> mergeSheet(Map<String, String[]> map1, Map<String, String[]> map2) {
        Map<String, String[]> map = new HashMap<>(map1.size());

        for (String url : map1.keySet()) {
            String[] oldArray = map1.get(url);
            String[] newArray = new String[oldArray.length + 1];

            for (int i = 0; i < oldArray.length + 1; i++) {
                if (i <= 4) {
                    newArray[i] = oldArray[i];
                } else if (i == 5) {
                    newArray[i] = "";
                } else {
                    newArray[i] = oldArray[i - 1];
                }
            }
            map.put(url, newArray);
        }

        for (String url : map2.keySet()) {
            if (map.containsKey(url)) {
                map.get(url)[5] = "√";
            }
        }
        return map;
    }

    /**
     * 读取Excel文件
     *
     * @param fileName
     * @param sheetNum
     * @return
     * @throws IOException
     */
    public static Map<String, String[]> parseExcel(String fileName, int sheetNum) throws IOException {
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
        Map<String, String[]> map = new HashMap<>(rowNum);

        for (int j = 1; j <= rowNum; j++) {
            Row row = sheet.getRow(j);
            String[] arr = new String[columnNum];
            String url = getValue(row.getCell(1));

            for (int i = 0; i < columnNum; i++) {
                Cell cell = row.getCell(i);
                if (cell == null) {
                    arr[i] = "";
                } else {
                    arr[i] = getValue(cell);
                }
            }
            map.put(url, arr);
        }
        return map;
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
}
