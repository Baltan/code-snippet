package charset_test;

import java.io.UnsupportedEncodingException;

public class Test1 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "中文";
		System.out.println(new String(str.getBytes("ISO-8859-1"), "UTF-8"));
		System.out.println(new String(str.getBytes("UTF-8"), "UTF-8"));// 中文
		System.out.println(new String(str.getBytes("GBK"), "UTF-8"));
		System.out.println(new String(str.getBytes("ISO-8859-1"), "GBK"));
		System.out.println(new String(str.getBytes("UTF-8"), "ISO-8859-1"));
		System.out.println(new String(str.getBytes("UTF-8"), "GBK"));
		System.out.println(new String(str.getBytes("GBK"), "GBK"));// 中文
		System.out.println(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"));
	}
}
