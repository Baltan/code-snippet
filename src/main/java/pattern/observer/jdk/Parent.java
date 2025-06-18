package pattern.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * Description: 被观察者
 *
 * @author Baltan
 * @date 2019-04-03 14:45
 */
public class Parent implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("收到" + o + "的命令：" + arg + "一定努力配合！");
    }
}
