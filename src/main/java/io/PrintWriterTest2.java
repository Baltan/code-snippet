package io;

import java.io.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/2/22 15:30
 */
public class PrintWriterTest2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter pw = new PrintWriter(System.out, true);//对println、printf、format方法自动flush
        pw = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File("/Users/Baltan/Desktop/PrintWriterTest2.txt")))), true);

        String line = null;

        while ((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            pw.println(line);
//            pw.flush();
        }
    }
}
