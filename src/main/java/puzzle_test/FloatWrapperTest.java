package puzzle_test;

/**
 * Description: https://developer.aliyun.com/article/705658
 *
 * @author Baltan
 * @date 2019-07-09 20:47
 */
public class FloatWrapperTest {
    public static void main(String[] args) {
        Float a = Float.valueOf(1.0f - 0.9f);
        Float b = Float.valueOf(0.9f - 0.8f);

        System.out.println("a: " + a);
        System.out.println("b: " + b);

        if (a.equals(b)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
