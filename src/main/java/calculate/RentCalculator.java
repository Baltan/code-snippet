package calculate_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/7/9 11:26
 */
public class RentCalculator {
    public static void main(String[] args) {
        calc(10000, 0.03, 3, 5);
    }

    public static void calc(double base, double rate, int separator, int limit) {
        System.out.println("起始租金：" + base + "，每" + separator + "年增长百分之" + rate * 100 + "，租期" + limit + "年");
        double sum = 0d;

        for (int i = 0; i < limit; i++) {
            if (i != 0 && i % separator == 0) {
                base *= (1 + rate);
            }
            System.out.println("第" + (i + 1) + "年租金：" + base);
            sum += base;
        }
        System.out.println("总租金：" + sum);
        System.out.println("----------------------------------------");
    }
}
