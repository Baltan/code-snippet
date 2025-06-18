package pattern.builder;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 13:53
 */
public interface BicycleBuilder {
    void buildWheel();

    void buildBrake();

    void buildFrame();

    Bicycle buildBicycle();
}
