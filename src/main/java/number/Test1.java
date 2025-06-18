package number;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);
        BigDecimal num1 = BigDecimal.valueOf(0.1);
        BigDecimal num2 = BigDecimal.valueOf(0.2);
        System.out.println(num1.add(num2).floatValue());
        System.out.println(num1.add(num2));
        System.out.println(num1.subtract(num2));
        System.out.println(num1.multiply(num2));
        System.out.println(num1.divide(num2, RoundingMode.HALF_UP));
    }
}
