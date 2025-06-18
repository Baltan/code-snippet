package pattern_test.proxy.dynamic_proxy;

/**
 * Description: JDK动态代理
 *
 * @author Baltan
 * @date 2019-04-02 15:38
 */
public class Test1 {
    public static void main(String[] args) {
        CustomizeHandler handler = new CustomizeHandler(InterfaceAImpl1.class);
        InterfaceA proxy = (InterfaceA) handler.getProxy();
        proxy.say();
    }
}
