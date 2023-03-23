package excel_test.poi.write;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/24 00:41
 */
public class SubsidyExcel {
    public static void main(String[] args) {
        createExcel();
    }

    public static void createExcel() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        // 创建一个文档
        HSSFWorkbook doc = new HSSFWorkbook();
        // 创建一个sheet
        HSSFSheet sheet = doc.createSheet("年度调整文件格式_补贴性质");
        // 设置表格列宽
        for (int i = 0; i < 5; i++) {
            sheet.setColumnWidth(i, 5000);
        }
        sheet.setColumnWidth(5, 7200);

        // 设置打印纸张大小
        HSSFPrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
        // 设置打印方向
        printSetup.setLandscape(true);

        // 设置单元格格式
        HSSFDataFormat format = doc.createDataFormat();

        // 创建第1个样式：14号、宋体、无边框、居中
        HSSFCellStyle style1 = doc.createCellStyle();
        HSSFFont font1 = doc.createFont();
        font1.setFontHeightInPoints((short) 14);
        font1.setFontName("宋体");
        font1.setColor((short) 0);
        style1.setFont(font1);
        // 设置水平居中
        style1.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建第2个样式：12号、宋体、有边框、居中
        HSSFCellStyle style2 = doc.createCellStyle();
        HSSFFont font2 = doc.createFont();
        font2.setFontHeightInPoints((short) 12);
        font2.setFontName("宋体");
        font2.setColor((short) 0);
        style2.setFont(font2);
        // 设置水平居中
        style2.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style2.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置四周边框
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);

        // 创建第3个样式：12号、宋体、无边框、居左
        HSSFCellStyle style3 = doc.createCellStyle();
        HSSFFont font3 = doc.createFont();
        font3.setFontHeightInPoints((short) 12);
        font3.setFontName("宋体");
        font3.setColor((short) 0);
        style3.setFont(font3);
        // 设置水平居中
        style3.setAlignment(HorizontalAlignment.LEFT);
        // 设置垂直居中
        style3.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建第4个样式：11号、宋体、有边框、居中
        HSSFCellStyle style4 = doc.createCellStyle();
        HSSFFont font4 = doc.createFont();
        font4.setFontHeightInPoints((short) 11);
        font4.setFontName("宋体");
        font4.setColor((short) 0);
        style4.setFont(font4);
        // 设置水平居中
        style4.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style4.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置四周边框
        style4.setBorderBottom(BorderStyle.THIN);
        style4.setBorderTop(BorderStyle.THIN);
        style4.setBorderLeft(BorderStyle.THIN);
        style4.setBorderRight(BorderStyle.THIN);

        // 创建第5个样式：11号、宋体、无边框、居中
        HSSFCellStyle style5 = doc.createCellStyle();
        HSSFFont font5 = doc.createFont();
        font5.setFontHeightInPoints((short) 11);
        font5.setFontName("宋体");
        font5.setColor((short) 0);
        style5.setFont(font5);
        // 设置水平居中
        style5.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style5.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建第6个样式：12号、宋体、无边框、居中
        HSSFCellStyle style6 = doc.createCellStyle();
        HSSFFont font6 = doc.createFont();
        font6.setFontHeightInPoints((short) 12);
        font6.setFontName("宋体");
        font6.setColor((short) 0);
        style6.setFont(font6);
        // 设置水平居中
        style6.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style6.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建第7个样式：12号、宋体、无边框、居中、自动换行
        HSSFCellStyle style7 = doc.createCellStyle();
        HSSFFont font7 = doc.createFont();
        font7.setFontHeightInPoints((short) 12);
        font7.setFontName("宋体");
        font7.setColor((short) 0);
        style7.setWrapText(true);
        style7.setFont(font7);
        // 设置水平居中
        style7.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style7.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建第8个样式：12号、宋体、无边框、居左
        HSSFCellStyle style8 = doc.createCellStyle();
        HSSFFont font8 = doc.createFont();
        font8.setFontHeightInPoints((short) 12);
        font8.setFontName("宋体");
        font8.setColor((short) 0);
        style8.setFont(font8);
        // 设置水平居中
        style8.setAlignment(HorizontalAlignment.LEFT);
        // 设置垂直居中
        style8.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建第9个样式：11号、宋体、无边框、居中、自动换行、单元格数值类型保留两位小数
        HSSFCellStyle style9 = doc.createCellStyle();
        HSSFFont font9 = doc.createFont();
        font9.setFontHeightInPoints((short) 11);
        font9.setFontName("宋体");
        font9.setColor((short) 0);
        style9.setWrapText(true);
        style9.setFont(font9);
        style9.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        // 设置水平居中
        style9.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style9.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建第10个样式：11号、宋体、有边框、居中、背景黄色
        HSSFCellStyle style10 = doc.createCellStyle();
        HSSFFont font10 = doc.createFont();
        font10.setFontHeightInPoints((short) 11);
        font10.setFontName("宋体");
        font10.setColor((short) 0);
        style10.setFont(font10);
        // 设置背景色
        style10.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style10.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置水平居中
        style10.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style10.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置四周边框
        style10.setBorderBottom(BorderStyle.THIN);
        style10.setBorderTop(BorderStyle.THIN);
        style10.setBorderLeft(BorderStyle.THIN);
        style10.setBorderRight(BorderStyle.THIN);

        // 创建第11个样式：12号、宋体、有边框、居中、自动换行
        HSSFCellStyle style11 = doc.createCellStyle();
        HSSFFont font11 = doc.createFont();
        font11.setFontHeightInPoints((short) 12);
        font11.setFontName("宋体");
        font11.setColor((short) 0);
        style11.setWrapText(true);
        style11.setFont(font11);
        // 设置水平居中
        style11.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style11.setVerticalAlignment(VerticalAlignment.CENTER);

        // 创建第12个样式：10号、宋体、无边框、居中
        HSSFCellStyle style12 = doc.createCellStyle();
        HSSFFont font12 = doc.createFont();
        font12.setFontHeightInPoints((short) 10);
        font12.setFontName("宋体");
        font12.setColor((short) 0);
        style12.setFont(font12);
        // 设置水平居中
        style12.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        style12.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFRow row;
        HSSFCell cell;
        CellRangeAddress region;

        // 第1行
        row = sheet.createRow(0);
        // 设置行高
        row.setHeightInPoints(18);

        cell = row.createCell(0);
        cell.setCellStyle(style1);
        cell.setCellValue(year + "年度××市住房公积金缴存比例、缴存基数调整表");
        // 合并单元格，四个参数分别是：起始行，结束行，起始列，结束列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

        // 第2行
        row = sheet.createRow(1);
        row.setHeightInPoints(18);

        cell = row.createCell(0);
        cell.setCellStyle(style3);
        cell.setCellValue("导出机构号：");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));

        cell = row.createCell(3);
        cell.setCellStyle(style3);
        cell.setCellValue("导出机构名称：");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 5));

        // 第3行
        row = sheet.createRow(2);
        row.setHeightInPoints(18);

        cell = row.createCell(0);
        cell.setCellStyle(style3);
        cell.setCellValue("资金性质：11住房公积金补贴");
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));

        cell = row.createCell(3);
        cell.setCellStyle(style3);
        cell.setCellValue("缴存比例%：");

        cell = row.createCell(4);
        cell.setCellStyle(style3);

        cell = row.createCell(5);
        cell.setCellStyle(style12);
        cell.setCellValue("请在前面的E3单元格内录入缴存比例");

        // 第4行
        row = sheet.createRow(3);
        row.setHeightInPoints(18);

        cell = row.createCell(0);
        cell.setCellStyle(style2);
        cell.setCellValue("单位客户号");
        region = new CellRangeAddress(3, 3, 0, 1);
        sheet.addMergedRegion(region);
        setBorder(BorderStyle.THIN, region, sheet);

        cell = row.createCell(2);
        cell.setCellStyle(style2);
        cell.setCellValue("");

        cell = row.createCell(3);
        cell.setCellStyle(style2);
        cell.setCellValue("单位名称");

        cell = row.createCell(4);
        cell.setCellStyle(style2);
        cell.setCellValue("");
        region = new CellRangeAddress(3, 3, 4, 5);
        sheet.addMergedRegion(region);
        setBorder(BorderStyle.THIN, region, sheet);

        // 第5行
        row = sheet.createRow(4);
        row.setHeightInPoints(18);

        cell = row.createCell(0);
        cell.setCellStyle(style6);
        cell.setCellValue("序号");

        cell = row.createCell(1);
        cell.setCellStyle(style6);
        cell.setCellValue("个人客户号");


        cell = row.createCell(2);
        cell.setCellStyle(style6);
        cell.setCellValue("姓名");

        cell = row.createCell(3);
        cell.setCellStyle(style6);
        cell.setCellValue("身份证号码");

        cell = row.createCell(4);
        cell.setCellStyle(style11);
        cell.setCellValue("上一年度月平均工资额（元）");

        cell = row.createCell(5);
        cell.setCellStyle(style6);
        cell.setCellValue("月缴存额（元）");

        // 第6行
        row = sheet.createRow(5);
        row.setHeightInPoints(18);

        cell = row.createCell(0);
        cell.setCellStyle(style6);
        region = new CellRangeAddress(4, 5, 0, 0);
        sheet.addMergedRegion(region);
        setBorder(BorderStyle.THIN, region, sheet);

        cell = row.createCell(1);
        cell.setCellStyle(style6);
        region = new CellRangeAddress(4, 5, 1, 1);
        sheet.addMergedRegion(region);
        setBorder(BorderStyle.THIN, region, sheet);

        cell = row.createCell(2);
        cell.setCellStyle(style6);
        region = new CellRangeAddress(4, 5, 2, 2);
        sheet.addMergedRegion(region);
        setBorder(BorderStyle.THIN, region, sheet);

        cell = row.createCell(3);
        cell.setCellStyle(style6);
        region = new CellRangeAddress(4, 5, 3, 3);
        sheet.addMergedRegion(region);
        setBorder(BorderStyle.THIN, region, sheet);

        cell = row.createCell(4);
        cell.setCellStyle(style6);
        region = new CellRangeAddress(4, 5, 4, 4);
        sheet.addMergedRegion(region);
        setBorder(BorderStyle.THIN, region, sheet);

        cell = row.createCell(4);
        cell.setCellStyle(style6);
        region = new CellRangeAddress(4, 5, 5, 5);
        sheet.addMergedRegion(region);
        setBorder(BorderStyle.THIN, region, sheet);

        // 第7行
        row = sheet.createRow(6);
        row.setHeightInPoints(18);

        cell = row.createCell(0);
        cell.setCellStyle(style4);
        cell.setCellValue(1);

        cell = row.createCell(1);
        cell.setCellStyle(style4);

        cell = row.createCell(2);
        cell.setCellStyle(style4);

        cell = row.createCell(3);
        cell.setCellStyle(style4);

        cell = row.createCell(4);
        cell.setCellStyle(style4);

        cell = row.createCell(5);
        cell.setCellStyle(style4);
        cell.setCellFormula("ROUND(E7*($E$3/100),0)");

        // 第8行
        row = sheet.createRow(7);
        row.setHeightInPoints(18);

        cell = row.createCell(0);
        cell.setCellStyle(style10);
        cell.setCellValue(2);

        cell = row.createCell(1);
        cell.setCellStyle(style10);

        cell = row.createCell(2);
        cell.setCellStyle(style10);

        cell = row.createCell(3);
        cell.setCellStyle(style10);

        cell = row.createCell(4);
        cell.setCellStyle(style10);

        cell = row.createCell(5);
        cell.setCellStyle(style10);
        cell.setCellFormula("ROUND(E8*($E$3/100),0)");

        // 第9行
        row = sheet.createRow(8);
        row.setHeightInPoints(18);
        cell = row.createCell(0);
        cell.setCellStyle(style8);
        cell.setCellValue("注：加底色标记的人员为当年新增职工");
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 0, 5));

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("/Users/Baltan/Desktop/年度调整文件格式_补贴性质.xls");
            doc.write(fos);
        } catch (IOException e) {

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    // 为合并的单元格设置边框
    private static void setBorder(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        RegionUtil.setBorderBottom(border, region, sheet);
        RegionUtil.setBorderLeft(border, region, sheet);
        RegionUtil.setBorderRight(border, region, sheet);
        RegionUtil.setBorderTop(border, region, sheet);
    }
}
