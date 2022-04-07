package switch_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/7 17:00
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println(getEnglishNum(0));
        System.out.println(getEnglishNum(1));
        System.out.println(getEnglishNum(3));
        System.out.println(getEnglishNum(5));
        System.out.println(getEnglishNum(7));
    }

    /**
     * @param num
     * @return
     * @see
     * <a href="https://www.pdai.tech/md/java/java8up/java12.html#jep-325-switch-%E8%A1%A8%E8%BE%BE%E5%BC%8F-%E9%A2%84%E8%A7%88%E7%89%88%E6%9C%AC">JEP 325: Switch 表达式 (预览版本)</a>
     */
    private static String getEnglishNum(int num) {
        String englishNum = switch (num) {
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            case 6 -> "six";
            default -> "unknown";
        };
        return englishNum;
    }
}
