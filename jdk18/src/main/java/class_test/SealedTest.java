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

    /**
     * @see
     * <a href="https://www.pdai.tech/md/java/java8up/java15.html#jep-360-%E5%AF%86%E5%B0%81%E7%9A%84%E7%B1%BB%E5%92%8C%E6%8E%A5%E5%8F%A3%E9%A2%84%E8%A7%88">JEP 360: 密封的类和接口（预览）</a>
     */
    public static sealed class Adult permits Teacher {

    }

    public static non-sealed class Teacher extends Adult {

    }
}
