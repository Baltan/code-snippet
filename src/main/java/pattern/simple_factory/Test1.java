package pattern.simple_factory;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 11:34
 */
public class Test1 {
    public static void main(String[] args) {
        BicycleFactory bicycleFactory = new BicycleFactory();
        System.out.println(bicycleFactory.createBicycle("forever").getPrice());
        System.out.println(bicycleFactory.createBicycle("giant").getPrice());
        System.out.println(bicycleFactory.createBicycle("merida").getPrice());
    }
}
