package system;

import java.util.Properties;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/18 10:00
 */
public class Test1 {
    public static void main(String[] args) {
        Properties props = System.getProperties();
        System.out.println(props.stringPropertyNames());
        System.out.println("---------------------------------------------------");
        System.out.println(props.getProperty("user.home"));
        System.out.println(props.getProperty("user.dir"));
        System.out.println(props.getProperty("user.language"));
        System.out.println(props.getProperty("user.language.format"));
        System.out.println("---------------------------------------------------");
        System.out.println(props.getProperty("java.vendor"));
        System.out.println(props.getProperty("java.version"));
        System.out.println(props.getProperty("java.runtime.version"));
        System.out.println("---------------------------------------------------");
        System.out.println(props.getProperty("os.version"));
        System.out.println("---------------------------------------------------");
        System.out.println(props.getProperty("file.encoding"));
        System.out.println(props.getProperty("file.separator"));
    }
}
