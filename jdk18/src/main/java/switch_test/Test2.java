package switch_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/7 17:06
 */
public class Test2 {
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
     * <a href="https://www.pdai.tech/md/java/java8up/java13.html#jep354-switch-%E8%A1%A8%E8%BE%BE%E5%BC%8F%E6%89%A9%E5%B1%95%E9%A2%84%E8%A7%88%E5%8A%9F%E8%83%BD">JEP354: Switch 表达式扩展（预览功能）</a>
     */
    private static String getEnglishNum(int num) {
        String englishNum = switch (num) {
            case 1:
                yield "one";
            case 2:
                yield "two";
            case 3:
                yield "three";
            case 4:
                yield "four";
            case 5:
                yield "five";
            case 6:
                yield "six";
            default:
                yield "unknown";
        };
        return englishNum;
    }
}
