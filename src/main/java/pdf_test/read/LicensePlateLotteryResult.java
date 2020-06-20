package pdf_test.read;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-08-27 14:20
 */
public class LicensePlateLotteryResult {
    public static void main(String[] args) throws IOException {
        statistics(getPdfFileText("201907.pdf"), "201907");
        statistics(getPdfFileText("201908.pdf"), "201908");
    }

    public static Set<String> getPdfFileText(String fileName) throws IOException {
        final String PATH = "/Users/Baltan/Desktop/";
        PdfReader reader = new PdfReader(PATH + fileName);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        Set<String> codes = new HashSet<>();
        TextExtractionStrategy strategy;
        int pageNum = reader.getNumberOfPages();
        Pattern regExp = Pattern.compile("\\d{13}");

        for (int i = 1; i <= pageNum; i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            String lineContent = strategy.getResultantText();
            String[] array = lineContent.split("\\s");

            for (String s : array) {
                if (regExp.matcher(s.trim()).matches()) {
                    String code = s.trim();
                    codes.add(code);
                }
            }
        }

        System.out.println("9667100912347: " + codes.contains("9667100912347"));
        System.out.println("8529100852566: " + codes.contains("8529100852566"));

        return codes;
    }

    public static void statistics(Set<String> codes, String date) {
        int[] startCount = new int[10];
        int[] endCount = new int[10];

        for (String code : codes) {
            startCount[code.charAt(0) - '0']++;
            endCount[code.charAt(12) - '0']++;
        }
        System.out.println(date);
        System.out.println(Arrays.toString(startCount));
        System.out.println(Arrays.toString(endCount));
    }
}