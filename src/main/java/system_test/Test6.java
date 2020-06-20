package system_test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/13 09:43
 */
public class Test6 {
    public static void main(String[] args) {
        String gnomeOpenCommand = "gnome-open /Users/Baltan/Desktop/文档1.pdf";

        try {
            Runtime rt = Runtime.getRuntime();
            Process processObj = rt.exec(gnomeOpenCommand);

            InputStream stdin = processObj.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String myoutput;

            while ((myoutput = br.readLine()) != null) {
                sb.append(myoutput);
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
