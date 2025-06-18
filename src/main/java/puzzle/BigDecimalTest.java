package puzzle;

import java.math.BigDecimal;

/**
 * Description: https://developer.aliyun.com/article/705658
 *
 * @author Baltan
 * @date 2019-07-09 20:48
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(0.1);
        System.out.println("a: " + a);

        BigDecimal b = new BigDecimal("0.1");
        System.out.println("b: " + b);
    }
}
