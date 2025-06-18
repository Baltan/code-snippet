package pattern_test.observer.customize;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 14:17
 */
public interface Observed {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
