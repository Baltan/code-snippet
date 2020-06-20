package number_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-01-18 19:39
 */
public class Test3 {
    public static void main(String[] args) {
        int num1 = Integer.MAX_VALUE;
        int num2 = num1 + 1;
        int num3 = Integer.MIN_VALUE;
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num2 == num3);
    }
}
