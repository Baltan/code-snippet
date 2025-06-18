package pattern.proxy.static_proxy;


/**
 * Description: 静态代理
 * 缺点：一个代理类只能代理一个实现该接口的被代理类。假如要代理InterfaceAImpl1、InterfaceAImpl2、
 * InterfaceAImpl3等，必须创建多个代理类。
 *
 * @author Baltan
 * @date 2019-04-02 15:32
 */
public class Test1 {
    public static void main(String[] args) {
        InterfaceA proxy = new InterfaceAProxy();
        proxy.say();
    }
}
