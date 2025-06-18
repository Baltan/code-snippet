package lambda_test;


/**
 * Description:
 *
 * @author Baltan
 * @date 2018/5/2 17:05
 */
public class Test5 {
    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//        Converter<String, Integer> converter = Integer::valueOf;
//        Consumer<String> consumer = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);
    }
}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
