package spring_proxy;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-02 16:52
 */
public class ServiceProxy implements Service {
    private Service service;

    public ServiceProxy(Service service) {
        this.service = service;
    }

    @Override
    public void doSomethingWithTransactional() {
        System.out.println("开启事务");
        service.doSomethingWithTransactional();
        System.out.println("提交事务");
    }

    @Override
    public void doSomethingWithoutTransactional() {
        service.doSomethingWithoutTransactional();
    }
}
