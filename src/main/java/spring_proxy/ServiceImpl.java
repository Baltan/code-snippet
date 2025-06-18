package spring_proxy;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-02 16:51
 */
public class ServiceImpl implements Service {
    @Override
    /**
     * 假如该方法上添加了事务注解
     */
//    @Transactional
    public void doSomethingWithTransactional() {
        System.out.println("doSomethingWithTransactional开始");
        System.out.println("doSomethingWithTransactional结束");
    }

    @Override
    public void doSomethingWithoutTransactional() {
        System.out.println("doSomethingWithoutTransactional开始");
        /**
         * 调用添加事务注解的方法
         */
        doSomethingWithTransactional();
        System.out.println("doSomethingWithoutTransactional结束");
    }
}
