package pattern_test.observer.customize;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 14:21
 */
public class Parent implements Observer {
    @Override
    public void action() {
        System.out.println("家长暴打一顿");
    }
}
