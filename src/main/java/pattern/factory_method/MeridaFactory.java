package pattern.factory_method;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 11:02
 */
public class MeridaFactory implements BicycleFactory {
    @Override
    public Bicycle createBicycle() {
        return new Merida();
    }
}
