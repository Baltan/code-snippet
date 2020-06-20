package pattern_test.proxy.static_proxy;

/**
 * Description: 代理类
 *
 * @author Baltan
 * @date 2019-04-02 15:31
 */
public class InterfaceAProxy implements InterfaceA {

    private InterfaceA interfaceA;

    public InterfaceAProxy() {
        this.interfaceA = new InterfaceAImpl1();
    }

    @Override
    public void say() {
        System.out.println("代理前操作……");
        interfaceA.say();
        System.out.println("代理后操作……");
    }
}
