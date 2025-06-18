package pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 17:12
 */
public class VehicleFactory {
    private Map<String, Vehicle> map = new HashMap<>();

    public Vehicle getVehicle(String name) {
        Vehicle vehicle = map.get(name);

        if (vehicle == null) {
            switch (name) {
                case "bicycle":
                    vehicle = new Bicycle();
                    break;
                case "car":
                    vehicle = new Car();
                    break;
                case "train":
                    vehicle = new Train();
                    break;
                case "plane":
                    vehicle = new Plane();
                    break;
                default:
                    vehicle = null;
            }
            map.put(name, vehicle);
        }
        return vehicle;
    }

    public int getVehicleCount() {
        return map.size();
    }
}
