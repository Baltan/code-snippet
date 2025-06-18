package io;

import java.io.PrintStream;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/2/22 15:42
 */
public class PrintStreamTest1 {
    public static void main(String[] args) {
        PrintStream ps = new PrintStream(System.out);

        ps.write(97);
        ps.println();
        ps.write(353);//整型有四个字节，write方法一次只输出一个字节，353二进制表示为"000……101100001"，只打印后八位"01100001"
        ps.flush();

        ps.println();
        ps.println();

        ps.print(97);//print(i)方法底层调用write(String.valueOf(i));
        ps.println();
        ps.print(353);
        ps.flush();
    }
}
