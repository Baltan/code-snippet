package pdf_test.write;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class DepositPdfPrint {
    public static void main(String[] args) throws Exception {
        Map<String, Object> input = new HashMap<>();
        depositOrganizationRegistrationPdf(input);
        submissionSpecificationPdf(input);
        collectionSpecificationPdf(input);
        entrustedDebitAuthorizationPdf(input);
    }

    /**
     * @param
     * @throws
     * @throws Exception
     * @author Baltan
     * @date 2019年7月24日 上午9:03:05
     * @Title: depositOrganizationRegistrationPdf
     * @Description: 住房公积金缴存单位登记通知书
     */
    public static String depositOrganizationRegistrationPdf(Map<String, Object> input) throws Exception {
        String priName = "";
        String priCertType = "";
        String priCertCode = "";
        String mobile = "";
        String addr = "";
        String salaryDay = "";
        String zipcode = "";
        String fax = "";
        String organCode = "";
        String cname = "";
        String indusCode = "";
        String corpType = "";
        String fundType = "";
        String payMode = "";
        String setBranCode = "";
        String sameFlag = "";
        String cscale = "";
        String pscale = "";
        String proxyName = "";
        String proxyCertCode = "";
        String proxyPhone = "";
        String proxyMobile = "";
        String proxyEmail = "";
        String proxyCertType = "";
        String status = "";
        String dist = "";
        String vtown = "";
        String certType = "";
        String certName = "";
        String certCode = "";
        String flag = "";
        String remark = "";
        String handleFlag = "";
        String comCustNo = "";
        String comAccountNo = "";

        // 文件生成路径
        String fileSite = "/Users/Baltan/Desktop/住房公积金缴存单位登记通知书.pdf";
        // 创建Document对象
        Document doc = new Document(PageSize.A4, -30, -30, 10, 10);
        // 获得书写器实例，通过书写器将文档写入磁盘
        PdfWriter.getInstance(doc, new FileOutputStream(fileSite));
        // 字体设置
        BaseFont bf = BaseFont.createFont();
        // 创建Font对象，将基础字体对象，字体大小，字体风格
        Font font = new Font(bf, 12, Font.NORMAL);
        Font font1 = new Font(bf, 15, Font.BOLD);
        // 打开文档
        doc.open();

        try {
            // 标题
            Paragraph title = new Paragraph("\n\n\n××市住房公积金缴存单位登记通知书", font1);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(title);

            Paragraph paragraph1 = new Paragraph();
            Chunk line1 = new Chunk("办理机构：" + String.format("%70s", "办理机构名称："), font);
            line1.setTextRise(-52);
            paragraph1.add(line1);
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            paragraph1.setIndentationLeft(80f);
            doc.add(paragraph1);

            Paragraph paragraph2 = new Paragraph();
            Chunk line2 = new Chunk("所属机构：" + String.format("%70s", "所属机构名称："), font);
            line2.setTextRise(-52);
            paragraph2.add(line2);
            paragraph2.setAlignment(Element.ALIGN_LEFT);
            paragraph2.setIndentationLeft(80f);
            doc.add(paragraph2);

            Paragraph paragraph3 = new Paragraph();
            Chunk line3 = new Chunk("业务流水号：" + String.format("%65s", "业务所属年月："), font);
            line3.setTextRise(-52);
            paragraph3.add(line3);
            paragraph3.setAlignment(Element.ALIGN_LEFT);
            paragraph3.setIndentationLeft(80f);
            doc.add(paragraph3);

            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);

            // 创建PdfTable对象
            PdfPTable table = new PdfPTable(6);
            // 设置各列的列宽
            table.setTotalWidth(new float[]{16, 22, 12, 17, 16, 17});
            PdfPCell cell;

            cell = getPDFLeftCell("单位名称", font);
            table.addCell(cell);

            cell = getPDFLeftCell(cname, font);
            cell.setColspan(5);
            table.addCell(cell);

            cell = getPDFLeftCell("组织机构代码（统一社会信用代码）", font);
            cell.setColspan(2);
            table.addCell(cell);

            cell = getPDFLeftCell(organCode, font);
            cell.setColspan(2);
            table.addCell(cell);

            cell = getPDFLeftCell("单位客户号", font);
            table.addCell(cell);

            cell = getPDFLeftCell(comCustNo, font);
            table.addCell(cell);

            cell = getPDFLeftCell("证件种类", font);
            table.addCell(cell);

            cell = getPDFLeftCell(certType, font);
            cell.setColspan(3);
            table.addCell(cell);

            cell = getPDFLeftCell("证件号码", font);
            table.addCell(cell);

            cell = getPDFLeftCell(certCode, font);
            table.addCell(cell);

            cell = getPDFLeftCell("单位性质代码", font);
            table.addCell(cell);

            cell = getPDFLeftCell(corpType, font);
            cell.setColspan(3);
            table.addCell(cell);

            cell = getPDFLeftCell("行业分类代码", font);
            table.addCell(cell);

            cell = getPDFLeftCell(indusCode, font);
            table.addCell(cell);

            cell = getPDFLeftCell("单位地址", font);
            table.addCell(cell);

            cell = getPDFLeftCell(addr, font);
            cell.setColspan(5);
            table.addCell(cell);

            cell = getPDFLeftCell("邮编", font);
            table.addCell(cell);

            cell = getPDFLeftCell(zipcode, font);
            cell.setColspan(3);
            table.addCell(cell);

            cell = getPDFLeftCell("发薪日期", font);
            table.addCell(cell);

            cell = getPDFLeftCell(String.format("每月（%s）日", salaryDay), font);
            table.addCell(cell);

            cell = getPDFLeftCell("缴存方式", font);
            table.addCell(cell);

            cell = getPDFLeftCell(payMode, font);
            cell.setColspan(3);
            table.addCell(cell);

            cell = getPDFLeftCell("归口管理", font);
            table.addCell(cell);

            cell = getPDFLeftCell(flag, font);
            table.addCell(cell);

            cell = getPDFLeftCell("资金性质", font);
            table.addCell(cell);

            cell = getPDFLeftCell(fundType, font);
            cell.setColspan(3);
            table.addCell(cell);

            cell = getPDFLeftCell("传真", font);
            table.addCell(cell);

            cell = getPDFLeftCell(fax, font);
            table.addCell(cell);

            cell = getPDFLeftCell("资金账号", font);
            table.addCell(cell);

            cell = getPDFLeftCell(comAccountNo, font);
            cell.setColspan(5);
            table.addCell(cell);

            cell = getPDFCell("单位负责人信息", font);
            cell.setColspan(6);
            table.addCell(cell);

            cell = getPDFLeftCell("姓名", font);
            table.addCell(cell);

            cell = getPDFLeftCell(priName, font);
            cell.setColspan(3);
            table.addCell(cell);

            cell = getPDFLeftCell("联系电话", font);
            table.addCell(cell);

            cell = getPDFLeftCell(mobile, font);
            table.addCell(cell);

            cell = getPDFLeftCell("证件类型", font);
            table.addCell(cell);

            cell = getPDFLeftCell(priCertType, font);
            cell.setColspan(3);
            table.addCell(cell);

            cell = getPDFLeftCell("证件号码", font);
            table.addCell(cell);

            cell = getPDFLeftCell(priCertCode, font);
            table.addCell(cell);

            if ("1".equals(payMode)) {
                cell = getPDFLeftCell("         计量对象代码：\n         托收协议号：\n         每月21日至25日，请在银行账户内存足月缴存总额。",
                        font);
            } else if ("2".equals(payMode)) {
                cell = getPDFLeftCell(
                        "         1、请在每月21日至25日到公积金中心办理送缴手续。\n         " +
                                "2、××住房公积金管理中心收款银行和账号：建设银行股份有限公司××××支行。银行账号：×××××××××××××××××××××",
                        font);
            }
            cell.setColspan(6);
            cell.setRowspan(3);
            table.addCell(cell);

            cell = getPDFCell("单位代理人信息", font);
            cell.setColspan(6);
            table.addCell(cell);

            cell = getPDFLeftCell("姓名", font);
            table.addCell(cell);

            cell = getPDFLeftCell(proxyName, font);
            table.addCell(cell);

            cell = getPDFLeftCell("证件类型", font);
            table.addCell(cell);

            cell = getPDFLeftCell(proxyCertType, font);
            table.addCell(cell);

            cell = getPDFLeftCell("证件号码", font);
            table.addCell(cell);

            cell = getPDFLeftCell(proxyCertCode, font);
            table.addCell(cell);

            cell = getPDFLeftCell("固定电话", font);
            table.addCell(cell);

            cell = getPDFLeftCell(proxyPhone, font);
            table.addCell(cell);

            cell = getPDFLeftCell("移动电话", font);
            table.addCell(cell);

            cell = getPDFLeftCell(proxyMobile, font);
            table.addCell(cell);

            cell = getPDFLeftCell("短信订阅", font);
            table.addCell(cell);

            cell = getPDFLeftCell("", font);
            table.addCell(cell);

            cell = getPDFLeftCell("开通网上业务", font);
            table.addCell(cell);

            cell = getPDFLeftCell(status, font);
            table.addCell(cell);

            cell = getPDFLeftCell("电子邮箱", font);
            table.addCell(cell);

            cell = getPDFLeftCell(proxyEmail, font);
            cell.setColspan(3);
            table.addCell(cell);

            cell = getPDFLeftCell(
                    String.format("%10s", "以上内容经核对无误。\n\n") + String.format("%20s", "单位代理人签名：\n\n") +
                            String.format("%90s", "年      月      日"),
                    font);
            cell.setColspan(3);
            cell.setRowspan(5);
            table.addCell(cell);

            cell = getPDFLeftCell("\n\n\n\n" + String.format("%90s", "年      月      日"), font);
            cell.setColspan(3);
            cell.setRowspan(5);
            table.addCell(cell);

            doc.add(table);

            Paragraph paragraph4 = new Paragraph();
            Chunk line4 = new Chunk("经办：" + String.format("%78s", "复核："), font);
            paragraph4.add(line4);
            paragraph4.setAlignment(Element.ALIGN_LEFT);
            paragraph4.setIndentationLeft(80f);
            doc.add(paragraph4);

            Paragraph paragraph5 = new Paragraph();
            Chunk line5 = new Chunk("办理时间：" + String.format("%70s", "打印日期："), font);
            paragraph5.add(line5);
            paragraph5.setAlignment(Element.ALIGN_LEFT);
            paragraph5.setIndentationLeft(80f);
            doc.add(paragraph5);
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
        return fileSite;
    }

    /**
     * @param
     * @author Baltan
     * @date 2019年7月25日 上午10:01:40
     * @Title: submissionSpecification
     * @Description: 送缴说明书
     */
    public static String submissionSpecificationPdf(Map<String, Object> input) throws Exception {
        // 文件生成路径
        String fileSite = "/Users/Baltan/Desktop/送缴说明书.pdf";
        // 创建Document对象
        Document doc = new Document(PageSize.A4, -30, -30, 10, 10);
        // 获得书写器实例，通过书写器将文档写入磁盘
        PdfWriter.getInstance(doc, new FileOutputStream(fileSite));
        // 字体设置
        BaseFont bf = BaseFont.createFont();
        // 创建Font对象，将基础字体对象，字体大小，字体风格
        Font font = new Font(bf, 12, Font.NORMAL);
        Font font1 = new Font(bf, 15, Font.BOLD);
        // 打开文档
        doc.open();

        try {
            // 标题
            Paragraph title = new Paragraph("\n\n\n住房公积金送缴结算说明", font1);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(title);

            Paragraph paragraph1 = new Paragraph();
            Chunk chunk1 = new Chunk("          一、贵单位已选择送缴方式缴存住房公积金（住房公积金补贴，系统输出）。", font);
            chunk1.setTextRise(-32);
            paragraph1.add(chunk1);
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            paragraph1.setIndentationLeft(80f);
            paragraph1.setIndentationRight(80f);
            doc.add(paragraph1);

            Paragraph paragraph2 = new Paragraph();
            Chunk chunk2 = new Chunk(
                    "          二、中心于每月20日（遇节假日顺延）生成当月应缴存住房公积金（住房公积金补贴，系统输出），自21日起可在中心网厅(××政务服务网)" +
                            "查询应缴存金额和结算状态。",
                    font);
            chunk2.setTextRise(-42);
            paragraph2.add(chunk2);
            paragraph2.setAlignment(Element.ALIGN_LEFT);
            paragraph2.setIndentationLeft(80f);
            paragraph2.setIndentationRight(80f);
            doc.add(paragraph2);

            Paragraph paragraph3 = new Paragraph();
            Chunk chunk3 = new Chunk(
                    "          三、为确保缴存连续性，当月应缴存资金请务必于每月21日至25日，通过登陆中心网厅办理自助缴款,或以网银汇划、转账支票、电汇等方式缴至中心银行账户：",
                    font);
            chunk3.setTextRise(-52);
            paragraph3.add(chunk3);
            paragraph3.setAlignment(Element.ALIGN_LEFT);
            paragraph3.setIndentationLeft(80f);
            paragraph3.setIndentationRight(80f);
            doc.add(paragraph3);

            Paragraph paragraph4 = new Paragraph();
            Chunk chunk4 = new Chunk("          收款单位：××住房公积金管理中心", font);
            chunk4.setTextRise(-62);
            paragraph4.add(chunk4);
            paragraph4.setAlignment(Element.ALIGN_LEFT);
            paragraph4.setIndentationLeft(80f);
            paragraph4.setIndentationRight(80f);
            doc.add(paragraph4);

            Paragraph paragraph5 = new Paragraph();
            Chunk chunk5 = new Chunk("          开户银行：××银行股份有限公司××××支行", font);
            chunk5.setTextRise(-72);
            paragraph5.add(chunk5);
            paragraph5.setAlignment(Element.ALIGN_LEFT);
            paragraph5.setIndentationLeft(80f);
            paragraph5.setIndentationRight(80f);
            doc.add(paragraph5);

            Paragraph paragraph6 = new Paragraph();
            Chunk chunk6 = new Chunk("          账号：××××××××××××××××××××", font);
            chunk6.setTextRise(-82);
            paragraph6.add(chunk6);
            paragraph6.setAlignment(Element.ALIGN_LEFT);
            paragraph6.setIndentationLeft(80f);
            paragraph6.setIndentationRight(80f);
            doc.add(paragraph6);

            Paragraph paragraph7 = new Paragraph();
            Chunk chunk7 = new Chunk("          四、如需变更单位的结算方式为托收的，可在中心网厅(××政务服务网)自助办理变更，或到中心网点办理。", font);
            chunk7.setTextRise(-92);
            paragraph7.add(chunk7);
            paragraph7.setAlignment(Element.ALIGN_LEFT);
            paragraph7.setIndentationLeft(80f);
            paragraph7.setIndentationRight(80f);
            doc.add(paragraph7);
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
        return fileSite;
    }

    /**
     * @param
     * @author Baltan
     * @date 2019年7月25日 上午11:42:22
     * @Title: collectionSpecification
     * @Description: 托收说明书
     */
    public static String collectionSpecificationPdf(Map<String, Object> input) throws Exception {
        // 文件生成路径
        String fileSite = "/Users/Baltan/Desktop/托收说明书.pdf";
        // 创建Document对象
        Document doc = new Document(PageSize.A4, -30, -30, 10, 10);
        // 获得书写器实例，通过书写器将文档写入磁盘
        PdfWriter.getInstance(doc, new FileOutputStream(fileSite));
        // 字体设置
        BaseFont bf = BaseFont.createFont();
        // 创建Font对象，将基础字体对象，字体大小，字体风格
        Font font = new Font(bf, 12, Font.NORMAL);
        Font font1 = new Font(bf, 15, Font.BOLD);
        // 打开文档
        doc.open();

        try {
            // 标题
            Paragraph title = new Paragraph("\n\n\n住房公积金托收结算说明", font1);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(title);

            Paragraph paragraph1 = new Paragraph();
            Chunk chunk1 = new Chunk(
                    "          " +
                            "一、贵单位已选择托收方式缴存住房公积金（住房公积金补贴，系统输出），请于三个工作日内，持《××一户通系统特约委托收款业务委托付款授权书》（开户办结后输出），向约定的委托付款银行办妥委托付款手续，以确保托收结算生效。",
                    font);
            chunk1.setTextRise(-32);
            paragraph1.add(chunk1);
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            paragraph1.setIndentationLeft(80f);
            paragraph1.setIndentationRight(80f);
            doc.add(paragraph1);

            Paragraph paragraph2 = new Paragraph();
            Chunk chunk2 = new Chunk(
                    "          二、中心于每月20日（遇节假日顺延）生成当月应缴存住房公积金（住房公积金补贴，系统输出），自21" +
                            "日起可在中心网厅（××政务服务网，下同）查询应缴存金额和结算状态。",
                    font);
            chunk2.setTextRise(-42);
            paragraph2.add(chunk2);
            paragraph2.setAlignment(Element.ALIGN_LEFT);
            paragraph2.setIndentationLeft(80f);
            paragraph2.setIndentationRight(80f);
            doc.add(paragraph2);

            Paragraph paragraph3 = new Paragraph();
            Chunk chunk3 = new Chunk(
                    "          三、中心自21日（遇节假日顺延）至25日，向委托付款银行发起当月应缴存资金托收申请。为确保缴存连续性，请务必在此期间存足金额，以确保扣款成功。",
                    font);
            chunk3.setTextRise(-52);
            paragraph3.add(chunk3);
            paragraph3.setAlignment(Element.ALIGN_LEFT);
            paragraph3.setIndentationLeft(80f);
            paragraph3.setIndentationRight(80f);
            doc.add(paragraph3);

            Paragraph paragraph4 = new Paragraph();
            Chunk chunk4 = new Chunk(
                    "          四、因未及时办妥委托付款手续、账户余额不足等原因，导致托收失败的，可在原因消除后，通过中心网厅自助发起托收结算，或拨打×××××联系中心告知重新托收。",
                    font);
            chunk4.setTextRise(-62);
            paragraph4.add(chunk4);
            paragraph4.setAlignment(Element.ALIGN_LEFT);
            paragraph4.setIndentationLeft(80f);
            paragraph4.setIndentationRight(80f);
            doc.add(paragraph4);

            Paragraph paragraph5 = new Paragraph();
            Chunk chunk5 = new Chunk(
                    "          " +
                            "五、如需变更结算方式为送缴，或委托付款银行、托收银行账户户名发生变化的，可通过中心网厅自助办理变更，或到中心网点办理。仅变更委托付款银行账号的，按委托付款银行要求办理即可。",
                    font);
            chunk5.setTextRise(-72);
            paragraph5.add(chunk5);
            paragraph5.setAlignment(Element.ALIGN_LEFT);
            paragraph5.setIndentationLeft(80f);
            paragraph5.setIndentationRight(80f);
            doc.add(paragraph5);
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
        return fileSite;
    }

    /**
     * @param
     * @author Baltan
     * @date 2019年7月25日 上午11:58:03
     * @Title: entrustedDebitAuthorization
     * @Description: 委托扣款授权书
     */
    public static String entrustedDebitAuthorizationPdf(Map<String, Object> input) throws Exception {
        // 文件生成路径
        String fileSite = "/Users/Baltan/Desktop/委托扣款授权书.pdf";
        // 创建Document对象
        Document doc = new Document(PageSize.A4, -30, -30, 10, 10);
        // 获得书写器实例，通过书写器将文档写入磁盘
        PdfWriter.getInstance(doc, new FileOutputStream(fileSite));
        // 字体设置
        BaseFont bf = BaseFont.createFont();
        // 创建Font对象，将基础字体对象，字体大小，字体风格
        Font font = new Font(bf, 12, Font.NORMAL);
        Font font1 = new Font(bf, 15, Font.BOLD);
        // 打开文档
        doc.open();

        try {
            Paragraph paragraph1 = new Paragraph();
            Chunk line1 =
                    new Chunk("\n编号：" + String.format("%60s", "计量对象代码：") + String.format("%60s", "资金性质："),
                            font);
            paragraph1.add(line1);
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            paragraph1.setIndentationLeft(80f);
            doc.add(paragraph1);

            Paragraph paragraph2 = new Paragraph();
            Chunk line2 = new Chunk("缴存单位名称：", font);
            paragraph2.add(line2);
            paragraph2.setAlignment(Element.ALIGN_LEFT);
            paragraph2.setIndentationLeft(80f);
            doc.add(paragraph2);

            // 标题
            Paragraph title = new Paragraph("\n\n××一户通系统特约委托收款业务委托付款授权书", font1);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(title);

            Paragraph paragraph3 = new Paragraph();
            Chunk line3 = new Chunk("________________________银行(信用社)： ", font);
            line3.setTextRise(-52);
            paragraph3.add(line3);
            paragraph3.setAlignment(Element.ALIGN_LEFT);
            paragraph3.setIndentationLeft(80f);
            doc.add(paragraph3);

            Paragraph paragraph4 = new Paragraph();
            Chunk line4 = new Chunk("          根据《××省“一户通”系统业务处理办法》，现就有关委托付款的事宜授权如下：", font);
            line4.setTextRise(-62);
            paragraph4.add(line4);
            paragraph4.setAlignment(Element.ALIGN_LEFT);
            paragraph4.setIndentationLeft(80f);
            paragraph4.setIndentationRight(80f);
            doc.add(paragraph4);

            Paragraph paragraph5 = new Paragraph();
            Chunk line5 = new Chunk(
                    "          一、授权你行(社)对××住房公积金管理中心(一户通机构代码：××××××××××)提交的结算约定款项委托收款凭证(或电子支付信息)" +
                            "，按照《××省“一户通”系统业务处理办法》审查后按委托收款金额直接在本存款人账户内" +
                            "(户名：________________________账号：________________________)" +
                            "划付，不需本单位确认。若本单位银行账号变更，在贵行(社)变更后的银行账号仍受本授权书及协议书所有条款约束。",
                    font);
            line5.setTextRise(-72);
            paragraph5.add(line5);
            paragraph5.setAlignment(Element.ALIGN_LEFT);
            paragraph5.setIndentationLeft(80f);
            paragraph5.setIndentationRight(80f);
            doc.add(paragraph5);

            Paragraph paragraph6 = new Paragraph();
            Chunk line6 = new Chunk("          二、本存款人账户内同时发生多项费用支付时，授权你行(社)按委托收款人送达委托收款凭证(或电子支付信息)的先后顺序予以支付。",
                    font);
            line6.setTextRise(-82);
            paragraph6.add(line6);
            paragraph6.setAlignment(Element.ALIGN_LEFT);
            paragraph6.setIndentationLeft(80f);
            paragraph6.setIndentationRight(80f);
            doc.add(paragraph6);

            Paragraph paragraph7 = new Paragraph();
            Chunk line7 = new Chunk("          三、本授权书必须经本存款人签章后有效。", font);
            line7.setTextRise(-92);
            paragraph7.add(line7);
            paragraph7.setAlignment(Element.ALIGN_LEFT);
            paragraph7.setIndentationLeft(80f);
            paragraph7.setIndentationRight(80f);
            doc.add(paragraph7);

            Paragraph paragraph8 = new Paragraph();
            Chunk line8 =
                    new Chunk("委托付款授权人（盖章）：                                                            ",
                            font);
            line8.setTextRise(-172);
            paragraph8.add(line8);
            paragraph8.setAlignment(Element.ALIGN_RIGHT);
            paragraph8.setIndentationLeft(80f);
            paragraph8.setIndentationRight(80f);
            doc.add(paragraph8);

            Paragraph paragraph9 = new Paragraph();
            Chunk line9 =
                    new Chunk("法定代表人（盖章）：                         年               月               日", font);
            line9.setTextRise(-192);
            paragraph9.add(line9);
            paragraph9.setAlignment(Element.ALIGN_RIGHT);
            paragraph9.setIndentationLeft(80f);
            paragraph9.setIndentationRight(80f);
            doc.add(paragraph9);

            Paragraph paragraph10 = new Paragraph();
            Chunk line10 = new Chunk("开户银行审查意见：\n我行(社)同意(不同意)接受该存款人委托付款之授权。", font);
            line10.setTextRise(-272);
            paragraph10.add(line10);
            paragraph10.setAlignment(Element.ALIGN_LEFT);
            paragraph10.setIndentationLeft(80f);
            paragraph10.setIndentationRight(80f);
            doc.add(paragraph10);

            Paragraph paragraph11 = new Paragraph();
            Chunk line11 = new Chunk("开户银行签章：", font);
            line11.setTextRise(-292);
            paragraph11.add(line11);
            paragraph11.setAlignment(Element.ALIGN_LEFT);
            paragraph11.setIndentationLeft(80f);
            paragraph11.setIndentationRight(80f);
            doc.add(paragraph11);
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
        return fileSite;
    }

    // 合并行的静态函数
    public static PdfPCell mergeRow(String str, Font font, int i) {
        // 创建单元格对象，将内容及字体传入
        PdfPCell cell = new PdfPCell(new Paragraph(str, font));
        // 设置单元格内容居中
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        // 将该单元格所在列包括该单元格在内的i行单元格合并为一个单元格
        cell.setRowspan(i);
        return cell;
    }

    // 合并列的静态函数
    public static PdfPCell mergeCol(String str, Font font, int i) {
        PdfPCell cell = new PdfPCell(new Paragraph(str, font));
        // cell.setMinimumHeight(25);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        // 将该单元格所在行包括该单元格在内的i列单元格合并为一个单元格
        cell.setColspan(i);
        return cell;
    }

    // 获取指定内容与字体的单元格
    public static PdfPCell getPDFCell(String string, Font font) {
        // 创建单元格对象，将内容与字体放入段落中作为单元格内容
        PdfPCell cell = new PdfPCell(new Paragraph(string, font));
        // 居中
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        // 设置最小单元格高度
        // cell.setMinimumHeight(15);
        return cell;
    }

    // 获取指定内容与字体的单元格
    public static PdfPCell getPDFLeftCell(String string, Font font) {
        // 创建单元格对象，将内容与字体放入段落中作为单元格内容
        PdfPCell cell = new PdfPCell(new Paragraph(string, font));
        // 左对齐
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        // 设置最小单元格高度
        // cell.setMinimumHeight(15);
        return cell;
    }
}
