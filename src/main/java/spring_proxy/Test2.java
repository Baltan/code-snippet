package spring_proxy;

/**
 * Description:
 * 1、public方法可以被代理类添加事务
 * 2、package方法可以被同包下的代理类添加事务，Spring中package方法不支持事务
 * 3、protected方法可以被代理类添加事务，Spring中protected方法不支持事务
 * 4、private方法无法被子类调用，无法被代理类添加事务
 * 5、final方法无法被重写，无法被代理类添加事务
 * 6、static方法是类方法，和实例无关，无法被代理类添加事务
 * 参考：
 * <a href="https://note.youdao.com/ynoteshare1/index.html?id=52a65e560ea75ab285fd856cd2d339ee&type=note"></a>
 *
 * @author Baltan
 * @date 2019-12-02 17:16
 */
public class Test2 {
    public static void main(String[] args) {
        Store store = new StoreProxy();
        store.publicMethod();
        store.packageMathod();
        store.protectedMethod();
        store.finalMethod();
    }
}
