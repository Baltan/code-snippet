package string;

import com.itextpdf.text.pdf.BaseFont;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/17 16:53
 */
public class Test5 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "☑";
        byte[] arr1 = str.getBytes("UTF8");
        byte[] arr2 = str.getBytes("Unicode");
        byte[] arr3 = str.getBytes("GBK");
        byte[] arr4 = str.getBytes("GB2312");
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr4));
    }
}
