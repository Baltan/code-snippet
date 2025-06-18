package pattern.observer.customize;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 14:21
 */
public class Lover implements Observer {
    @Override
    public void action() {
        System.out.println("情人大战三百回合发泄");
    }
}
