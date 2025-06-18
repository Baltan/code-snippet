package pattern.abstract_factory;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 11:12
 */
public class BicycleBFactory implements BicycleFactory {
    @Override
    public Wheel createWheel() {
        return new WheelB();
    }

    @Override
    public Brake createBrake() {
        return new BrakeB();
    }
}
