package spi;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/8/11 22:14
 */
public class OtherSubscribe implements Subscribe {
    @Override
    public void follow() {
        System.out.println("订阅了别人的公众号");
    }
}
