package format;

/**
 * Description: 字符串右侧填充0
 *
 * @author Baltan
 * @date 2019-08-28 11:09
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(String.format("%-20s", "a") + "fuck");
        System.out.println(String.format("%-20s", "aa") + "fuck");
        System.out.println(String.format("%-20s", "abc") + "fuck");
        System.out.println(String.format("%-20s", "abcd") + "fuck");
        System.out.println(String.format("%-20s", "abcde") + "fuck");
        System.out.println(String.format("%-20s", "abcdef") + "fuck");
        System.out.println(String.format("%-20s", "abcdefg") + "fuck");
        System.out.println(String.format("%-20s", "423534") + "fuck");
        System.out.println(String.format("%-20s", "000") + "fuck");
        System.out.println(String.format("%-20s", "00042hg342") + "fuck");
        System.out.println(String.format("%-20s", "00042hgg35hj3535") + "fuck");
    }
}
