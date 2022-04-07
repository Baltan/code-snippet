package text_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/7 17:09
 */
public class BlockTextTest {
    /**
     * @param args
     * @see
     * <a href="https://www.pdai.tech/md/java/java8up/java13.html#jep354-switch-%E8%A1%A8%E8%BE%BE%E5%BC%8F%E6%89%A9%E5%B1%95%E9%A2%84%E8%A7%88%E5%8A%9F%E8%83%BD">JEP355: 文本块（预览功能）</a>
     */
    public static void main(String[] args) {
        String info = """
                {
                    "name": "Baltan",
                    "age": 18,
                    "nationality": "China",
                    "hobbies": ["doze", "sleep", "deep sleep"]
                }             
                """;
        System.out.println(info);
    }
}
