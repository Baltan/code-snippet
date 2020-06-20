package pattern_test.builder;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 13:58
 */
public class ForeverBuilder implements BicycleBuilder {
    private Bicycle forever;

    public ForeverBuilder() {
        this.forever = new Forever();
    }

    @Override
    public void buildWheel() {
        forever.setWheel("forever wheel");
    }

    @Override
    public void buildBrake() {
        forever.setBrake("forever brake");
    }

    @Override
    public void buildFrame() {
        forever.setFrame("forever frame");
    }

    @Override
    public Bicycle buildBicycle() {
        return forever;
    }
}
