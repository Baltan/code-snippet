package pattern.abstract_factory;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 11:11
 */
public interface BicycleFactory {
    Wheel createWheel();

    Brake createBrake();
}
