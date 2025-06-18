package pattern.observer.customize;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 14:19
 */
public class Teacher implements Observer {
    @Override
    public void action() {
        System.out.println("老师批评教育");
    }
}
