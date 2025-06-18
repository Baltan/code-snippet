package system;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/19 15:42
 */
public class Test3 {
    public static void main(String[] args) {
        String name = "Zhang San";
        int age = 20;
        System.out.printf("Hello, %s! Next year you'll be %d", name, age);
        System.out.println();
        String str = String.format("Hello, %s! Next year you'll be %d", name, age);
        System.out.println(str);
        System.out.printf("%,8.5f", 1000000 / 3.0);
        System.out.println();
        System.out.printf("%8.5f", 1000000 / 3.0);
    }
}
