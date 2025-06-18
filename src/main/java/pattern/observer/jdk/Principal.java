package pattern.observer.jdk;

import java.util.Observable;

/**
 * Description: 观察者
 *
 * @author Baltan
 * @date 2019-04-03 14:34
 */
public class Principal extends Observable {
    public void command(String message) {
        setChanged();
        notifyObservers(message);
    }

    @Override
    public String toString() {
        return "Principal";
    }
}
