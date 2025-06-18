package pattern_test.builder;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 14:05
 */
public class GiantBuilder implements BicycleBuilder {
    private Bicycle giant;

    public GiantBuilder() {
        this.giant = new Giant();
    }

    @Override
    public void buildWheel() {
        giant.setWheel("giant wheel");
    }

    @Override
    public void buildBrake() {
        giant.setBrake("giant brake");
    }

    @Override
    public void buildFrame() {
        giant.setFrame("giant frame");
    }

    @Override
    public Bicycle buildBicycle() {
        return giant;
    }
}
