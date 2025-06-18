package jvm_test;

import sun.misc.Launcher;

import java.net.URL;

/**
 * Description: 查看Bootstrap ClassLoader加载的jar或class文件
 *
 * @author Baltan
 * @date 2019-05-30 17:16
 */
public class Test2 {
    public static void main(String[] args) {
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

        System.out.println("---------------------------------------------");

        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
