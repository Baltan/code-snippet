package pattern_test.factory_method;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 11:03
 */
public class ForeverFactory implements BicycleFactory {
    @Override
    public Bicycle createBicycle() {
        return new Forever();
    }
}
