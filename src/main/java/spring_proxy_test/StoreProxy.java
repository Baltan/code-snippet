package spring_proxy_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-02 17:08
 */
public class StoreProxy extends Store {
    public void publicMethod() {
        System.out.println("开启事务");
        super.publicMethod();
        System.out.println("提交事务");
    }

    void packageMathod() {
        System.out.println("开启事务");
        super.packageMathod();
        System.out.println("提交事务");
    }

    protected void protectedMethod() {
        System.out.println("开启事务");
        super.protectedMethod();
        System.out.println("提交事务");
    }
}
