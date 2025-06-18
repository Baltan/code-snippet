package spring_proxy;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-02 16:50
 */
public interface Service {
    void doSomethingWithTransactional();

    void doSomethingWithoutTransactional();
}
