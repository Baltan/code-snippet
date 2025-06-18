package spring_proxy_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-02 17:05
 */
public class Store {
    /**
     * 假如该方法上添加了事务注解
     */
//    @Transactional
    public void publicMethod() {
        System.out.println("publicMethod");
    }

    /**
     * 假如该方法上添加了事务注解
     */
//    @Transactional
    void packageMathod() {
        System.out.println("packageMathod");
    }

    /**
     * 假如该方法上添加了事务注解
     */
//    @Transactional
    protected void protectedMethod() {
        System.out.println("protectedMethod");
    }

    /**
     * 假如该方法上添加了事务注解
     */
//    @Transactional
    private void privateMethod() {
        System.out.println("privateMethod");
    }

    /**
     * 假如该方法上添加了事务注解
     */
//    @Transactional
    public final void finalMethod() {
        System.out.println("finalMethod");
    }

    /**
     * 假如该方法上添加了事务注解
     */
//    @Transactional
    public static void staticMethod() {
        System.out.println("staticMethod");
    }
}
