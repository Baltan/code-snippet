package pattern_test.simple_factory;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 11:29
 */
public class BicycleFactory {
    public Bicycle createBicycle(String name) {
        switch (name) {
            case "forever":
                return new Forever();
            case "giant":
                return new Giant();
            case "merida":
                return new Merida();
            default:
                return null;
        }
    }
}
