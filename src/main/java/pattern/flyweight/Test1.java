package pattern.flyweight;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 17:21
 */
public class Test1 {
    public static void main(String[] args) {
        VehicleFactory factory = new VehicleFactory();

        Vehicle v1 = factory.getVehicle("car");
        Vehicle v2 = factory.getVehicle("car");
        Vehicle v3 = factory.getVehicle("car");
        Vehicle v4 = factory.getVehicle("plane");
        Vehicle v5 = factory.getVehicle("train");

        v1.name();
        v2.name();
        v3.name();
        v4.name();
        v5.name();

        System.out.println(factory.getVehicleCount());

        System.out.println(v1 == v2);
        System.out.println(v2 == v3);
        System.out.println(v3 == v4);
    }
}
