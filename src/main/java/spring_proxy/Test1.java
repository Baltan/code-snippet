package spring_proxy_test;

/**
 * Description: 假如在一个未开启事务的方法中调用一个开启事务的方法，不会添加事务
 * 参考：
 * <a href="https://note.youdao.com/ynoteshare1/index.html?id=52a65e560ea75ab285fd856cd2d339ee&type=note"></a>
 *
 * @author Baltan
 * @date 2019-12-02 16:56
 */
public class Test1 {
    public static void main(String[] args) {
        Service serviceImpl = new ServiceImpl();
        Service serviceProxy = new ServiceProxy(serviceImpl);
        serviceProxy.doSomethingWithoutTransactional();
    }
}
