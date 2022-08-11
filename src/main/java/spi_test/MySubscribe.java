package spi_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/8/11 22:14
 */
public class MySubscribe implements Subscribe {
    @Override
    public void follow() {
        System.out.println("订阅了我的公众号");
    }
}
