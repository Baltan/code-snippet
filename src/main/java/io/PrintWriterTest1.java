package io_test;

import java.io.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/2/22 15:21
 */
public class PrintWriterTest1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = null;

        while ((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
//        bw.close();//标准输出流是静态的，关闭之后"666"无法输出
        System.out.println("666");
    }
}
