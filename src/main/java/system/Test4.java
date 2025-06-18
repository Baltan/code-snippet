package system;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/20 16:10
 */
public class Test4 {
    public static void main(String[] args) throws Exception {
        Scanner in1 = new Scanner(Paths.get("/Users/Baltan/Desktop/a.txt"), "utf-8");
        Scanner in2 = new Scanner(new File("/Users/Baltan/Desktop/a.txt"));
        Scanner in3 = new Scanner("qwertyuiop");
        while (in1.hasNext()) {
            System.out.println(in1.next());
        }
        System.out.println();
        while (in2.hasNext()) {
            System.out.println(in2.next());
        }
        System.out.println();
        while (in3.hasNext()) {
            System.out.println(in3.next());
        }
    }
}
