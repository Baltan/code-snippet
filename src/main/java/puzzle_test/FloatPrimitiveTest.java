package puzzle_test;

/**
 * Description: https://developer.aliyun.com/article/705658
 *
 * @author Baltan
 * @date 2019-07-09 20:45
 */
public class FloatPrimitiveTest {
    public static void main(String[] args) {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;

        System.out.println("a: " + a);
        System.out.println("b: " + b);

        if (a == b) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
