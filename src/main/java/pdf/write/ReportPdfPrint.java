package pdf_test.write;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-10-29 15:02
 */
public class ReportPdfPrint {
    public static void main(String[] args) throws Exception {
        printLqda();
    }

    public static void printLqda() throws Exception {
        final String PATH = "/Users/Baltan/Desktop/××××.pdf";
        OutputStream os = Files.newOutputStream(new File(PATH).toPath());

        Document document = new Document(PageSize.A4, -30, -30, 10, 10);
        PdfWriter writer = PdfWriter.getInstance(document, os);
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 12, Font.NORMAL);
        Font font1 = new Font(baseFont, 30, Font.BOLD);
        Font font2 = new Font(baseFont, 15, Font.NORMAL);
        Font font3 = new Font(baseFont, 7, Font.NORMAL);

        document.open();

        Paragraph blank1 = new Paragraph("           ", font1);
        blank1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blank1);

        Paragraph paragraph1 = new Paragraph("××××报告表", font1);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph1.setSpacingBefore(90);
        document.add(paragraph1);

        Paragraph paragraph2 = new Paragraph("报告人：____________________（签名）", font2);
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph2.setSpacingBefore(90);
        document.add(paragraph2);

        Paragraph paragraph3 = new Paragraph("单位：_____________________________", font2);
        paragraph3.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph3.setSpacingBefore(60);
        document.add(paragraph3);

        Paragraph paragraph4 = new Paragraph("报告日期：__________年_____月_____日", font2);
        paragraph4.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph4.setSpacingBefore(60);
        document.add(paragraph4);

        Paragraph paragraph5 = new Paragraph("审签人：____________________（签名）", font2);
        paragraph5.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph5.setSpacingBefore(60);
        document.add(paragraph5);

        Paragraph paragraph6 = new Paragraph("审签日期：__________年_____月_____日", font2);
        paragraph6.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph6.setSpacingBefore(60);
        document.add(paragraph6);

        Paragraph paragraph7 =
                new Paragraph("××××××机构", font2);
        paragraph7.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph7.setSpacingBefore(80);
        document.add(paragraph7);

        Paragraph paragraph8 = new Paragraph(
                "××××报告表   第1页 / 共3页", font3);
        paragraph8.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph8.setSpacingBefore(60);
        document.add(paragraph8);

        document.newPage();

        Paragraph blank2 = new Paragraph("           ", font1);
        blank2.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blank2);

        Paragraph title1 = new Paragraph("报告人基本情况", font2);
        title1.setAlignment(Paragraph.ALIGN_CENTER);
        title1.setSpacingBefore(20);
        document.add(title1);

        PdfPTable table1 = getTable1(font3);
        table1.setSpacingBefore(15);
        document.add(table1);

        document.close();
        writer.close();
    }

    /**
     * 创建表格中的单元格
     *
     * @param string
     * @param font
     * @return
     */
    private static PdfPCell getCell(String string, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(string, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    /**
     * 创建第一张表格
     *
     * @param font3
     * @return
     * @throws DocumentException
     */
    private static PdfPTable getTable1(Font font3) throws DocumentException {
        String imageUrl = "";
        PdfPTable table = new PdfPTable(8);
        int[] tableWidth1 = {1, 1, 1, 1, 1, 1, 1, 1};
        table.setWidths(tableWidth1);
        table.setTotalWidth(280f);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell cell;

        cell = getCell("姓名", font3);
        table.addCell(cell);

        cell = getCell("", font3);
        table.addCell(cell);

        cell = getCell("性别", font3);
        table.addCell(cell);

        cell = getCell("", font3);
        table.addCell(cell);

        cell = getCell("民族", font3);
        table.addCell(cell);

        cell = getCell("", font3);
        cell.setColspan(3);
        table.addCell(cell);

        if (StringUtils.isNotEmpty(imageUrl)) {
            cell = getCell("", font3);
            cell.setRowspan(6);

//            BASE64Decoder decoder = new BASE64Decoder();
//            byte[] bytes = decoder.decodeBuffer(imageUrl);
//            /**
//             * 调整异常数据
//             */
//            for (int i = 0; i < bytes.length; ++i) {
//                if (bytes[i] < 0) {
//                    bytes[i] += 256;
//                }
//
//                Image image = Image.getInstance(bytes);
//
//                cell = new PdfPCell();
//                cell.setRowspan(6);
//                cell.setImage(image);
//            }
        } else {
            cell = getCell("", font3);
            cell.setRowspan(6);
        }
        table.addCell(cell);
        return table;
    }
}
