package pattern.abstract_factory;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 11:14
 */
public class Test1 {
    public static void main(String[] args) {
        BicycleAFactory bicycleAFactory = new BicycleAFactory();
        bicycleAFactory.createWheel();
        bicycleAFactory.createBrake();

        BicycleBFactory bicycleBFactory = new BicycleBFactory();
        bicycleBFactory.createWheel();
        bicycleAFactory.createBrake();
    }
}
