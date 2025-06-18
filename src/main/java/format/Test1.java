package format;

import java.text.NumberFormat;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/27 17:15
 */
public class Test1 {
    public static void main(String[] args) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        NumberFormat integerFormatter = NumberFormat.getIntegerInstance();

        int value = 5;
        System.out.println(currencyFormatter.format(value));
        System.out.println(percentFormatter.format(value));
        System.out.println(integerFormatter.format(value));
    }
}
