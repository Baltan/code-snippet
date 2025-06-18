package pattern_test.proxy.dynamic_proxy;

/**
 * Description: 被代理类
 *
 * @author Baltan
 * @date 2019-04-02 15:31
 */
public class InterfaceAImpl1 implements InterfaceA {

    @Override
    public void say() {
        System.out.println("InterfaceAImpl1是InterfaceA的实现类。");
    }
}
