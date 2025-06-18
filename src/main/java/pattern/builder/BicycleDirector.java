package pattern.builder;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 14:08
 */
public class BicycleDirector {
    public Bicycle createBicycle(BicycleBuilder builder) {
        builder.buildWheel();
        builder.buildBrake();
        builder.buildFrame();
        return builder.buildBicycle();
    }
}
