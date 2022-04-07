package class_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/7 15:56
 */
public class SealedTest {
    public static void main(String[] args) {
        Teacher t = new Teacher();
    }

    public static sealed class Adult permits Teacher {

    }

    public static non-sealed class Teacher extends Adult {

    }
}
