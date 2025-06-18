package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * Description: reference:http://www.codeceo.com/article/essential-java-io-streams.html
 *
 * @author Baltan
 * @date 2019-03-18 09:22
 */
public class ScanTest2 {
    public static void main(String[] args) {
        Scanner scanner = null;
        double sum = 0;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("/Users/Baltan/Desktop/1.txt")));
            scanner.useLocale(Locale.CHINA); // 设置语言环境

            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    sum += scanner.nextDouble();
                } else {
                    System.out.println(scanner.next());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        System.out.println(sum);
    }
}
