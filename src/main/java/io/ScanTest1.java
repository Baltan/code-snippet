package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Description: reference:http://www.codeceo.com/article/essential-java-io-streams.html
 *
 * @author Baltan
 * @date 2019-03-18 09:11
 */
public class ScanTest1 {
    public static void main(String[] args) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("/Users/Baltan/Desktop/1.txt")));
//            scanner.useDelimiter("-"); // 默认使用空格字符作为分隔标记（包括空格，制表符和行终止符）

            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
