package number;

import java.text.DecimalFormat;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/1/29 15:21
 */
public class Test2 {
    public static void main(String[] args) {
        DecimalFormat format1 = new DecimalFormat("##.##");
        System.out.println(format1.format(3.1));
        System.out.println(format1.format(3.1415926));
        System.out.println(format1.format(3141.5926));
        DecimalFormat format2 = new DecimalFormat("#0.00");
        System.out.println(format2.format(3.1));
        System.out.println(format2.format(3.1415926));
        System.out.println(format2.format(3141.5926));
    }
}
